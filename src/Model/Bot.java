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

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingDifficulty getBotPlayingDifficulty() {
        return botPlayingDifficulty;
    }

    public void setBotPlayingDifficulty(BotPlayingDifficulty botPlayingDifficulty) {
        this.botPlayingDifficulty = botPlayingDifficulty;
    }
    public Move playerMove(Game game)
    {
        return botPlayingDifficulty.playinStrategy(game);
    } 
    
}
