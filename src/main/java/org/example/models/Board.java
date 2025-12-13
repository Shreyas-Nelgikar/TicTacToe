package org.example.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Board {

    private int size;
    private List<List<Cell>> cells = new ArrayList<>();

    public Board(int size) {
        this.size = size;
        this.cells = createBoard(size);
    }

    private List<List<Cell>> createBoard(int size) {
        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(new Cell());
            }
            board.add(row);
        }
        return board;
    }

    public boolean validateMove(Cell currentMove) {
        int row = currentMove.getRow();
        int column = currentMove.getColumn();

        if (row < 0 || row >= size || column < 0 || column >= size) {
            return false;
        } else if (cells.get(row).get(column).getSymbol() != null) {
            return false;
        }

        return true;
    }

    public void addMove(Cell currentMove) {
        int row = currentMove.getRow();
        int column = currentMove.getColumn();

        cells.get(row).set(column, currentMove);
    }

    public void displayBoard() {
        System.out.println("------------------------");
        for (List<Cell> cellsList: cells) {
            for (Cell cell: cellsList) {
                if (cell.getSymbol() == null) {
                    System.out.print("-");
                } else {
                    System.out.print(cell.getSymbol().getAlphabet());
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    public List<Cell> updateBoard(int undoMoves, List<Cell> moves) {
        int totalMoves = moves.size();
        List<Cell> movesUndone = new ArrayList<>();

        for (int i=0; i<undoMoves; i++) {
            Cell lastMove = moves.get(totalMoves - 1 - i);
            int row = lastMove.getRow();
            int column = lastMove.getColumn();

            moves.remove(totalMoves - 1 - i);
            movesUndone.add(lastMove);
            cells.get(row).set(column, new Cell());
        }

        return movesUndone;
    }
}
