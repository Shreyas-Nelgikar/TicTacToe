package org.example.strategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.Symbol;

public class FirstEmptyPlayingStrategy implements PlayingStrategy {

    @Override
    public Cell makeMove(Symbol symbol, Board board) {
        int size = board.getSize();

        for (int row=0; row<size; row++) {
            for (int column=0; column<size; column++) {
                if (board.getCells().get(row).get(column).getSymbol() == null) {
                    return new Cell(row, column, symbol);
                }
            }
        }

        return null;
    }
}
