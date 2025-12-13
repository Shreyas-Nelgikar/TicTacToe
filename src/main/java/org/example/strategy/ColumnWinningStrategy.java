package org.example.strategy;

import org.example.models.Cell;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WiningStrategy {

    private Map<Integer, Map<String, Integer>> columnSymbolCount = new HashMap<>();

    @Override
    public boolean checkWinner(Cell currentMove, int boardSize) {
        int column = currentMove.getColumn();
        Symbol symbol = currentMove.getSymbol();

        columnSymbolCount.putIfAbsent(column, new HashMap<>());
        Map<String, Integer> symbolCountMap = columnSymbolCount.get(column);
        int symbolCount = symbolCountMap.getOrDefault(symbol.getAlphabet(), 0) + 1;
        symbolCountMap.put(symbol.getAlphabet(),symbolCount);

        return symbolCount == boardSize;
    }

    @Override
    public void updateStrategy(List<Cell> movesUndone) {
        for (Cell cell: movesUndone) {
            int column = cell.getColumn();
            Symbol symbol = cell.getSymbol();

            columnSymbolCount.get(column)
                    .put(symbol.getAlphabet(), columnSymbolCount.get(column).get(symbol.getAlphabet()) - 1);
        }
    }

}
