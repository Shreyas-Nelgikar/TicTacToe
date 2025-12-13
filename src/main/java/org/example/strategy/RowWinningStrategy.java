package org.example.strategy;

import org.example.models.Cell;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WiningStrategy {

    private Map<Integer, Map<String, Integer>> rowSymbolCount = new HashMap<>();

    @Override
    public boolean checkWinner(Cell currentMove, int boardSize) {
        int row = currentMove.getRow();
        Symbol symbol = currentMove.getSymbol();

        rowSymbolCount.putIfAbsent(row, new HashMap<>());
        Map<String, Integer> symbolCountMap = rowSymbolCount.get(row);
        int symbolCount = symbolCountMap.getOrDefault(symbol.getAlphabet(), 0) + 1;
        symbolCountMap.put(symbol.getAlphabet(), symbolCount);

        return symbolCount == boardSize;
    }

    @Override
    public void updateStrategy(List<Cell> movesUndone) {
        for (Cell cell: movesUndone) {
            int row = cell.getRow();
            Symbol symbol = cell.getSymbol();

            rowSymbolCount.get(row)
                    .put(symbol.getAlphabet(), rowSymbolCount.get(row).get(symbol.getAlphabet()) - 1);
        }
    }
}
