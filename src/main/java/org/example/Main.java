package org.example;

import org.example.controller.GameController;
import org.example.exceptions.InvalidBoardSizeException;
import org.example.exceptions.InvalidPlayersSizeException;
import org.example.exceptions.NoStrategyException;
import org.example.models.*;
import org.example.strategy.ColumnWinningStrategy;
import org.example.strategy.RowWinningStrategy;
import org.example.strategy.WiningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            System.out.println("*****WELCOME TO TICTACTOE GAME*****");
            GameController gameController = new GameController();

            System.out.println("Please enter the size of the board : ");
            int boardSize = scanner.nextInt();

            System.out.println("If you wish to play with bot type bot else human : ");
            String playerType = scanner.next();

            List<Player> players = new ArrayList<>();
            int noOfPlayers = PlayerType.HUMAN.name().equalsIgnoreCase(playerType) ? boardSize - 1 : boardSize - 2;

            for (int i = 0; i < noOfPlayers; i++) {
                System.out.println("Please enter the name of the player : ");
                String name = scanner.next();

                System.out.println("Please enter the age of the player : ");
                int age = scanner.nextInt();

                System.out.println("Please enter the email of the player : ");
                String email = scanner.next();

                System.out.println("Please enter the symbol for the player : ");
                String symbol = scanner.next();
                players.add(new HumanPlayer(new User(name, age, email), new Symbol(symbol)));
            }

            if (PlayerType.BOT.name().equalsIgnoreCase(playerType)) {
                players.add(new Bot(DifficultyLevel.EASY));
            }


            List<WiningStrategy> strategies = new ArrayList<>();
            System.out.println("Please select the winning strategies from the options below:");
            System.out.println("1. Row Winning Strategy");
            System.out.println("2. Column Winning Strategy");
            System.out.println("For ex : 1|2");
            String strategy = scanner.next();

            String[] strat = strategy.split("|");
            for (int i=0; i<strat.length; i++) {
                String s = strat[i].trim();
                if (("1").equalsIgnoreCase(s)) {
                    strategies.add(new RowWinningStrategy());
                } else if (("2").equalsIgnoreCase(s)) {
                    strategies.add(new ColumnWinningStrategy());
                }
            }

            Game game = gameController.createNewGame(boardSize, players, strategies);
            while (game.getStatus() == GameStatus.IN_PROGRESS) {
                gameController.displayBoard(game);

                if (PlayerType.HUMAN.name().equalsIgnoreCase(game.getCurrentPlayer().getPlayerType().name())){
                    System.out.println("If you want to undo y/n");
                    String undo = scanner.next();
                    if ("y".equalsIgnoreCase(undo)) {
                        gameController.undoMove(game);
                    }
                }

                gameController.makeMove(game);
                gameController.checkWinner(game);
            }

            gameController.displayBoard(game);

            if (game.getStatus() == GameStatus.FINISHED) {
                String winner = game.getCurrentPlayer().getUser().getName();
                System.out.println("WINNER IS : " + winner);
            } else {
                System.out.println("GAME DRAW");
            }


            System.out.println("*****GAME FINISHED*****");

        } catch (Exception | InvalidPlayersSizeException | NoStrategyException | InvalidBoardSizeException e) {
            System.out.println(e.getMessage());
        }

    }
}