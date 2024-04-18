package Model;

import Factory.BotDifficultyFactory;
import Strategy.BotPlayingStrategy.BotPlayingDifficulty;

public class Bot extends Player{

    private BotDifficultyLevel botDifficultyLevel; 
    private BotPlayingDifficulty botPlayingDifficulty;
    
    public Bot(String name, Symbol symbol, PlayerType playerType,BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel=botDifficultyLevel;
        this.botPlayingDifficulty=BotDifficultyFactory.getDifficulty(botDifficultyLevel);
    }
    public Move playerMove(Game game)
    {
        return botPlayingDifficulty.playinStrategy(game);
    } 
    
}
