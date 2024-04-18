package Model;

// import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.management.RuntimeErrorException;

import Strategy.WinningStrategy.WinningStrategy;

public class Game {
    
    private int dimensions;
    private Board board;
    private List<Player>players;
    private List<Move>moves;
    private GameState gameState;
    private List<WinningStrategy> winningStrategy;
    private Player winner;
    private int nextPlayerId;

    public static Builder getBuilder()
    {
        return new Builder();
    }
    private Game(int dimensions,List<Player>players,List<WinningStrategy> winningStrategy)
    {
        this.board=new Board(dimensions);
        this.gameState=GameState.IN_PROGRESS;
        this.players=players;
        nextPlayerId=0;
        this.winningStrategy=winningStrategy;
        this.dimensions=dimensions;
        this.moves=new ArrayList<>();

    }
    public int getDimensions() {
        return dimensions;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public int getNextPlayerId() {
        return nextPlayerId;
    }
    
    public Player getWinner() {
        return winner;
    }
    

    public void printBoard()
    {
        board.printBoard();
    }

    public boolean validation(Move move)
    {
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        if(row<0||row>=dimensions||col<0||col>=dimensions
        ||board.getCells().get(row).get(col).getCellState().equals(CellState.FILLED))
        return false;

        return true;
    }
    public void makeMove() {

        Player p1 = players.get(nextPlayerId);

        Move makeMove=p1.playerMove(this);

        if(!validation(makeMove))
        {
            System.out.println("Wrong move , please retry");
            this.makeMove();
            return;
            
        }
        int row=makeMove.getCell().getRow();
        int col=makeMove.getCell().getCol();

        nextPlayerId=(nextPlayerId + 1) % players.size();
        Cell c=board.getCells().get(row).get(col);
        c.setCellState(CellState.FILLED);
        c.setPlayer(p1);
        
        Move finaMove=new Move(p1, c);
        moves.add(finaMove);
        if(checkWinner(this, finaMove))
        {
            gameState=GameState.ENDED;
            winner=p1;
        }
        else if(moves.size()==dimensions*dimensions)
        {
            gameState=GameState.DRAW;
        }
        
    }
    public boolean undoCheck()
    {
        Player p1 = players.get(nextPlayerId);

        if(moves.size()==0||!p1.getPlayerType().equals(PlayerType.HUMAN))
        return false;

        int i;
        for (i = moves.size() - 1; i >= 0; i--) {
            if (moves.get(i).getPlayer().getSymbol().getaChar() == p1.getSymbol().getaChar())
                return true;
        }
        System.out.println("You have no moves to undo , Please make atleast 1 move");
        return false;
    }
    public void undo() {
        Player p1 = players.get(nextPlayerId);

        int i;
        for (i = moves.size() - 1; i >= 0; i--) {
            if (moves.get(i).getPlayer().getSymbol().getaChar() == p1.getSymbol().getaChar())
                break;
        }

        Move move = moves.get(i);

        moves.remove(i);

        Cell c = move.getCell();
        for(WinningStrategy winningStrategyobj:winningStrategy)
        {
            winningStrategyobj.decrementCount(board, c);
        }
        c.setPlayer(null);
        c.setCellState(CellState.EMPTY);
    }

    public boolean checkWinner(Game game,Move finaMove)
    {
        for (WinningStrategy obj : winningStrategy) {
            if (obj.strategy(board, finaMove)) {
                return true;
            }
        }
        return false;
    }

    //builder
    public static class Builder{
        private int dimensions;
        private List<Player>players;
        private List<WinningStrategy> winningStrategy;


        public int getDimensions() {
            return dimensions;
        }
        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }
        public List<Player> getPlayers() {
            return players;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public List<WinningStrategy> getWinningStrategy() {
            return winningStrategy;
        }
        public Builder setWinningStrategy(List<WinningStrategy> winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void performValidations(int dimensions,List<Player>players)
        {
            botCheck(players);
            dimensionCheck(dimensions);
            symbolCheck(players);
            countOfPlayersandDimesions(dimensions,players);
        }
        private void countOfPlayersandDimesions(int dimensions2, List<Player> players2) {
            
            if(dimensions2-1!=players2.size())
            throw new RuntimeErrorException(null,"No of Players should be dimensions-1");
        }
        private void symbolCheck(List<Player> players2) {

            HashSet<Character>st=new HashSet<>();
            for(Player player:players2)
            {
                st.add(player.getSymbol().getaChar());
            }

            if(st.size()!=players2.size())
            throw new RuntimeErrorException(null,"Each player should have a unique symbol");
        }
        private void dimensionCheck(int dimensions2) {
            
            if(dimensions2<=2)
            throw new RuntimeErrorException(null,"Number should be greater than 2");
        }
        private void botCheck(List<Player> players2) {

            int count=0;

            for(Player player:players2)
            {
                if(player.getPlayerType().equals(PlayerType.BOT))
                count++;

                if(count>1)
                throw new RuntimeErrorException(null,"Only one Bot is Allowed");

            }
           
        }
        public Game build()
        {
            performValidations(dimensions, players);

            return new Game(dimensions, players, winningStrategy);
        }
       
    }
}
