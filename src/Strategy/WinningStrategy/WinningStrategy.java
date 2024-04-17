package Strategy.WinningStrategy;

import Model.Board;
import Model.Cell;
import Model.Move;

public interface WinningStrategy {
    
    boolean strategy(Board board,Move move);
    void decrementCount(Board board,Cell c);
}
