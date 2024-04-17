import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Controller.GameController;
import Model.Bot;
import Model.BotDifficultyLevel;
import Model.Game;
import Model.GameState;
import Model.Player;
import Model.PlayerType;
import Model.Symbol;
import Strategy.WinningStrategy.ColWinningStrategy;
import Strategy.WinningStrategy.DiagWinningStrategy;
import Strategy.WinningStrategy.RowWinningStrategy;
import Strategy.WinningStrategy.WinningStrategy;

public class Main {
    public static void main(String[] args) throws Exception {
        
        List<Player>players=new ArrayList<>();

        players.add(new Player("Rishav", new Symbol('x'), PlayerType.HUMAN));
        players.add(new Bot("Ai", new Symbol('o'), PlayerType.BOT, BotDifficultyLevel.EASY));
        // players.add(new Player("Gaurav", new Symbol('#'), PlayerType.HUMAN));


        List<WinningStrategy>winningStrategies=List.of(
            new RowWinningStrategy(),
            new ColWinningStrategy(),
            new DiagWinningStrategy()
        );
        Collections.shuffle(players);
        GameController gameController=new GameController();
        Game game=gameController.makeGame(3, players, winningStrategies);

        

        while(game.getGameState().equals(GameState.IN_PROGRESS))
        {
            System.out.println("This is "+game.getPlayers().get(game.getNextPlayerId()).getName()+" turn ");
            game.printBoard();

            if(gameController.undoCheck(game))
           { 
                
                System.out.println("DO you want to undo your move");

                Scanner sc=new Scanner(System.in);
                String isUndo=sc.next();
                

                if(isUndo.equals("yes"))
                {
                    gameController.undo(game);
                    continue;
                }
            }
            gameController.makeMove(game);

            if(game.getGameState().equals(GameState.ENDED)||game.getGameState().equals(GameState.DRAW))
            break;
        }

        if(game.getGameState().equals(GameState.DRAW))
        System.out.println("Game ends in a draw");
        else
        System.out.println("Winner of the game is "+game.getWinner().getName());

        game.printBoard();
    }
}
