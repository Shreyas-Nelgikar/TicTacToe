package org.example.models;

import lombok.Getter;
import org.example.factory.PlayingStrategyFactory;
import org.example.strategy.PlayingStrategy;

@Getter
public class Bot extends Player {

    private Symbol symbol;
    private DifficultyLevel level;
    private PlayingStrategy botPlayingStrategy;

    public Bot(DifficultyLevel level) {
        super(new User("Bot", 22, "bot@gmail.com"), PlayerType.BOT);
        this.symbol = new Symbol("O");
        this.level = level;
        this.botPlayingStrategy = PlayingStrategyFactory.getPlayingStrategy(level);
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println("Bot is making a move");
        Cell currentMove = botPlayingStrategy.makeMove(symbol, board);
        System.out.println("Bot made a move at row " + currentMove.getRow() + " and column " + currentMove.getColumn());
        return currentMove;
    }
}
