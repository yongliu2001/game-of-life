package com.yong.game.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * GameBoard Object to hold game board grid
 * Created by yongliu on 7/2/2018.
 */
public class GameBoard implements Serializable {
    private static final long serialVersionUID = 7970970071603832095L;

    private boolean[][] board = new boolean[200][200];

    public GameBoard() {
    }

    public boolean[][] getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameBoard gameBoard = (GameBoard) o;

        return Arrays.deepEquals(board, gameBoard.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}