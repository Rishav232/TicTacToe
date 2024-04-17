package Controller;

import java.util.List;
import Model.Game;
import Model.Player;
import Strategy.WinningStrategy.WinningStrategy;

public class GameController {

    public Game makeGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies) {
        return Game.getBuilder()
                .setDimensions(dimensions)
                .setWinningStrategy(winningStrategies)
                .setPlayers(players)
                .build();
    }

    public void undo(Game game) {

        game.undo(game);
    }

    public void makeMove(Game game) {

        game.makeMove(game);
    }
    public boolean undoCheck(Game game)
    {
        return game.undoCheck(game);
    }
}
