package org.example.strategy;

import org.example.models.Cell;

import java.util.List;

public interface WiningStrategy {

    public boolean checkWinner(Cell currentMove, int boardSize);

    public void updateStrategy(List<Cell> movesUndone);
}
