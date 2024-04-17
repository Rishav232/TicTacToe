package Strategy.BotPlayingStrategy;

import Model.Cell;
import Model.CellState;
import Model.Game;
import Model.Move;
import java.util.ArrayList;
import java.util.List;

public class HardPlayingStrategy implements BotPlayingDifficulty{

    @Override
    public Move playinStrategy(Game game) {
        List<Integer> emptyCells = new ArrayList<>();
            for (int i = 0; i < game.getDimensions(); i++) {
                for (int j = 0; j < game.getDimensions(); j++) {
                    if (game.getBoard().getCells().get(i).get(j).getCellState().equals(CellState.EMPTY))
                        emptyCells.add(i * game.getDimensions() + j);
                }
            }
            if (!emptyCells.isEmpty()) {
                int random = (int) Math.floor(Math.random() * emptyCells.size());
                int getIndex = emptyCells.get(random);

                int rowInput = getIndex / game.getDimensions();
                int colInput = getIndex % game.getDimensions();

                Move move=new Move(null, new Cell(rowInput, colInput));
                return move;
            }
            return null;
    }
    
}
