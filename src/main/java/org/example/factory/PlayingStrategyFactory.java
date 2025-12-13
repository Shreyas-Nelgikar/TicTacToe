package org.example.factory;

import org.example.models.DifficultyLevel;
import org.example.strategy.FirstEmptyPlayingStrategy;
import org.example.strategy.PlayingStrategy;

public class PlayingStrategyFactory {

    public static PlayingStrategy getPlayingStrategy(DifficultyLevel level) {
        return switch (level) {
            case EASY -> new FirstEmptyPlayingStrategy();
            case MEDIUM -> null;
            case HARD -> null;
        };
    }
}