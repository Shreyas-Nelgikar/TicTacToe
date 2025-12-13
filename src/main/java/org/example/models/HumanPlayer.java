package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;

@Getter
public class HumanPlayer extends Player {

    private Symbol symbol;
    private Scanner scanner;

    public HumanPlayer(User user, Symbol symbol) {
        super(user, PlayerType.HUMAN);
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public Cell makeMove(Board board) {
        String playerName = user.getName();

        System.out.println(playerName + "'s turn : Please enter the row number : ");
        int row = scanner.nextInt();
        System.out.println(playerName + "'s turn : Please enter the column number : ");
        int column = scanner.nextInt();

        return new Cell(row, column, symbol);
    }
}
