package org.example.controller;

import org.example.exceptions.InvalidBoardSizeException;
import org.example.exceptions.InvalidPlayersSizeException;
import org.example.exceptions.NoStrategyException;
import org.example.models.Board;
import org.example.models.Game;
import org.example.models.Player;
import org.example.strategy.WiningStrategy;

import java.util.List;

public class GameController {

    public Game createNewGame(int boardSize, List<Player> players, List<WiningStrategy> strategies) throws InvalidPlayersSizeException, NoStrategyException, InvalidBoardSizeException {
        return Game.createGame()
                .ofBoardSize(boardSize)
                .ofPlayers(players)
                .ofStrategies(strategies)
                .build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undoMove(Game game) {
        game.undoMove();
    }

    public boolean checkWinner(Game game) {
        return game.checkWinner();
    }
}
