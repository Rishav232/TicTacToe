package Strategy.WinningStrategy;

import java.util.HashMap;

import Model.Board;
import Model.Cell;
import Model.Move;
import Model.Player;

public class ColWinningStrategy implements WinningStrategy {
    public final static HashMap<Integer, HashMap<Character, Integer>> colHashMap = new HashMap<>();

    @Override
    public boolean strategy(Board board, Move move) {
        int dimensions = board.getDimensions();
        char playerMark = move.getPlayer().getSymbol().getaChar();

        int col = move.getCell().getCol();

        if (!colHashMap.containsKey(col))
            colHashMap.put(col, new HashMap<Character, Integer>());

        HashMap<Character, Integer> currCol = colHashMap.get(col);

        currCol.put(playerMark, currCol.getOrDefault(playerMark, 0) + 1);

        if (currCol.get(playerMark) == dimensions)
            return true;
        return false;
    }

    @Override
    public void decrementCount(Board board,Cell cell) {
        Player p1 = cell.getPlayer();
        HashMap<Character, Integer> currCol = colHashMap.get(cell.getCol());
        currCol.put(p1.getSymbol().getaChar(), currCol.getOrDefault(p1.getSymbol().getaChar(), 0) - 1);
        if (currCol.get(p1.getSymbol().getaChar()) == 0)
        currCol.remove(p1.getSymbol().getaChar());
    }
}
