package org.example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exceptions.InvalidBoardSizeException;
import org.example.exceptions.InvalidPlayersSizeException;
import org.example.exceptions.NoStrategyException;
import org.example.strategy.WiningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Getter
public class Game {

    private Board board;
    private List<Player> players = new ArrayList<>();
    private List<WiningStrategy> strategies = new ArrayList<>();
    private List<Cell> moves = new ArrayList<>();
    private GameStatus status;
    private int nextPlayerIndex;
    private Player currentPlayer;
    private Scanner scanner = new Scanner(System.in);
    private boolean isValidMove;

    private Game (Board board, List<Player> players, List<WiningStrategy> strategies) {
        this.board = board;
        this.players = players;
        this.strategies = strategies;
        this.status = GameStatus.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.currentPlayer = players.get(nextPlayerIndex);
        this.isValidMove = false;
    }

    public static GameBuilder createGame() {
        return new GameBuilder();
    }

    public static class GameBuilder {

        private Game game;

        private GameBuilder() {
            game = new Game();
        }

        public GameBuilder ofBoardSize(int size) {
            this.game.board = new Board(size);
            return this;
        }

        public GameBuilder ofPlayers(List<Player> players) {
            this.game.players = players;
            return this;
        }

        public  GameBuilder ofStrategies(List<WiningStrategy> strategies) {
            this.game.strategies = strategies;
            return this;
        }

        public Game build() throws InvalidBoardSizeException, InvalidPlayersSizeException, NoStrategyException {
            validateGameDetails();
            return new Game(this.game.board, this.game.players, this.game.strategies);
        }

        private void validateGameDetails() throws InvalidBoardSizeException, InvalidPlayersSizeException, NoStrategyException {
            validateBoardSize();
            validatePlayers();
            validateWinningStrategy();
        }

        private void validateBoardSize() throws InvalidBoardSizeException {
            int boardSize = this.game.board.getSize();
            if (boardSize <= 1)
                throw new InvalidBoardSizeException("Board size should be greater than 1");
        }

        private void validatePlayers() throws InvalidPlayersSizeException {
            int playersSize = this.game.players.size();
            if (playersSize <= 1)
                throw new InvalidPlayersSizeException("There must be more than 1 player to play");
        }

        private void validateWinningStrategy() throws NoStrategyException {
            int winningStrategySize = this.game.strategies.size();
            if (winningStrategySize <= 0)
                throw new NoStrategyException("There must be atleast 1 strategy present to win");
        }

    }

    public void displayBoard() {
        board.displayBoard();
    }

    public void makeMove() {
        Cell currentMove = currentPlayer.makeMove(board);
        isValidMove = board.validateMove(currentMove);
        if (isValidMove) {
            moves.add(currentMove);
            board.addMove(currentMove);
            nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
        }
    }

    public void undoMove() {
        if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
            if (moves.size() == 0) {
                System.out.println("No moves to Undo");
                return;
            }

            System.out.println("You can undo at max " + moves.size() + " moves");
            System.out.println("Please enter the number of moves you want to undo : ");
            int undoMoves = scanner.nextInt();
            undoMoves = Math.min(undoMoves, moves.size());

            List<Cell> movesUndone = board.updateBoard(undoMoves, moves);
            updateStrategy(movesUndone);
            nextPlayerIndex = (nextPlayerIndex - undoMoves % players.size() + players.size()) % players.size();
        }
        currentPlayer = players.get(nextPlayerIndex);
    }

    private void updateStrategy(List<Cell> movesUndone) {
        for (WiningStrategy strategy: strategies) {
            strategy.updateStrategy(movesUndone);
        }
    }

    public boolean checkWinner() {
        if (isValidMove) {
            int boardSize = board.getSize();
            int totalMoves = moves.size();
            Cell lastMove = moves.get(totalMoves - 1);

            for (WiningStrategy strategy : strategies) {
                boolean winner = strategy.checkWinner(lastMove, boardSize);
                if (winner) {
                    status = GameStatus.FINISHED;
                    return true;
                }
            }

            checkDraw(boardSize, totalMoves);
        }

        currentPlayer = players.get(nextPlayerIndex);
        return false;
    }

    private void checkDraw(int boardSize, int totalMoves) {
        if (totalMoves == boardSize * boardSize) {
            status = GameStatus.DRAW;
        }
    }

}
