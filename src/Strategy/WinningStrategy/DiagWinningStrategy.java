package Strategy.WinningStrategy;

import java.util.HashMap;

import Model.Board;
import Model.Cell;
import Model.Move;
import Model.Player;

public class DiagWinningStrategy implements WinningStrategy {
    public final HashMap<Character, Integer> leftdiagHashMap = new HashMap<>();
    public final HashMap<Character, Integer> rightdiagHashMap = new HashMap<>();

    @Override
    public boolean strategy(Board board, Move move) {
        int dimensions = board.getDimensions();
        char playerMark = move.getPlayer().getSymbol().getaChar();

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row == col) {

            leftdiagHashMap.put(playerMark, leftdiagHashMap.getOrDefault(playerMark, 0) + 1);

            if (leftdiagHashMap.get(playerMark) == dimensions)
                return true;
        }
        if (row + col == dimensions - 1) {
            rightdiagHashMap.put(playerMark, rightdiagHashMap.getOrDefault(playerMark, 0) + 1);

            if (rightdiagHashMap.get(playerMark) == dimensions)
                return true;
        }
        return false;
    }

    @Override
    public void decrementCount(Board board,Cell c) {

        Player p1=c.getPlayer();
        int dimensions=board.getDimensions();
        if (c.getRow() == c.getCol()) {
            leftdiagHashMap.put(p1.getSymbol().getaChar(), leftdiagHashMap.getOrDefault(p1.getSymbol().getaChar(), 0) - 1);

            if (leftdiagHashMap.get(p1.getSymbol().getaChar()) == 0)
            leftdiagHashMap.remove(p1.getSymbol().getaChar());
        }
        if(c.getRow()+c.getCol()==dimensions-1)
        {

            rightdiagHashMap.put(p1.getSymbol().getaChar(), rightdiagHashMap.getOrDefault(p1.getSymbol().getaChar(), 0) - 1);

            if (rightdiagHashMap.get(p1.getSymbol().getaChar()) == 0)
            rightdiagHashMap.remove(p1.getSymbol().getaChar());
        }
    }

}
