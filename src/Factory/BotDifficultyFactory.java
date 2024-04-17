package Factory;

import Model.BotDifficultyLevel;
import Strategy.BotPlayingStrategy.BotPlayingDifficulty;
import Strategy.BotPlayingStrategy.EasyPlayingStrategy;
import Strategy.BotPlayingStrategy.HardPlayingStrategy;
import Strategy.BotPlayingStrategy.MediumPlayingStrategy;

public class BotDifficultyFactory {
    
    public static BotPlayingDifficulty getDifficulty(BotDifficultyLevel botDifficultyLevel)
    {
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY))
        return new EasyPlayingStrategy();
        if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM))
        return new MediumPlayingStrategy();
        if(botDifficultyLevel.equals(BotDifficultyLevel.HARD))
        return new HardPlayingStrategy();

        return null;

    }
}
