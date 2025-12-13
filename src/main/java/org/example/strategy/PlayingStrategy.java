package org.example.strategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.Symbol;

public interface PlayingStrategy {

    public Cell makeMove(Symbol symbol, Board board);

}
