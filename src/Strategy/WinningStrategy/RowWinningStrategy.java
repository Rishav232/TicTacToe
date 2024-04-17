package Strategy.WinningStrategy;

import java.util.HashMap;

import Model.Board;
import Model.Cell;
import Model.Move;
import Model.Player;

public class RowWinningStrategy implements WinningStrategy {
    public final static HashMap<Integer, HashMap<Character, Integer>> rowHashMap = new HashMap<>();

    @Override
    public boolean strategy(Board board, Move move) {

        int dimensions = board.getDimensions();
        char playerMark = move.getPlayer().getSymbol().getaChar();

        int row = move.getCell().getRow();

        if (!rowHashMap.containsKey(row))
            rowHashMap.put(row, new HashMap<Character, Integer>());

        HashMap<Character, Integer> currRow = rowHashMap.get(row);

        currRow.put(playerMark, currRow.getOrDefault(playerMark, 0) + 1);

        if (currRow.get(playerMark) == dimensions)
            return true;

        return false;
    }

    @Override
    public void decrementCount(Board board,Cell c) {

        Player p1=c.getPlayer();
        HashMap<Character, Integer> currRow = rowHashMap.get(c.getRow());
        currRow.put(p1.getSymbol().getaChar(), currRow.getOrDefault(p1.getSymbol().getaChar(), 0) - 1);
        if (currRow.get(p1.getSymbol().getaChar()) == 0)
        currRow.remove(p1.getSymbol().getaChar());
    }

}
