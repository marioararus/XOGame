package com.mr.app.android.xogame.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

/**
 * Created by Marioara Rus on 6/30/2018.
 */
public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    public Player currentPlayer = player1;
    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player("playerOne", "x");
        player2 = new Player("playerTwo", "o");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() ||
                hasThreeSameVerticalCells() ||
                hasThreeSameDiagonalCells()) {
            winner.setValue(currentPlayer);
            return true;
        }
        if (isBoardFull()) {
            winner.setValue(null);
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == null || cell.isEmpty()) {
                    return true;
                }
            }
        }
        return true;
    }

    private boolean hasThreeSameDiagonalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(cells[i][i], cells[i + 1][i + 1], cells[i + 2][i + 2]) ||
                        areEqual(cells[i][2], cells[i + 1][1], cells[2][i])) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(cells[0][i], cells[1][i], cells[2][i])) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (areEqual(cells[i][0], cells[i][1], cells[i][2])) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }
        for (Cell cell : cells) {
            if (cell == null || cell.getPlayer().getValue() == null ||
                    cell.getPlayer().getValue().length() == 0) {
                return false;
            }
        }
        Cell baseCompare = cells[0];
        for (int i = 1; i < cells.length; i++) {
            if (baseCompare.getPlayer().getValue().equals(cells[i].getPlayer().getValue())) {
                return true;
            }
        }
        return false;
    }
    public void reset(){
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }

}
