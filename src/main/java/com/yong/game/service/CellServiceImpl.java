package com.yong.game.service;

import com.yong.game.domain.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongliu on 2/7/18.
 */
public class CellServiceImpl implements CellService {

    @Override
    public List<Cell> getNeighbourCells(boolean[][] board, Cell cell) {

        List<Cell> neighbourCoordinates = new ArrayList<>();
        int size = board.length;
        int x = cell.getX();
        int y = cell.getY();

        // get top, topLeft, topRight cells status
        if (y - 1 >= 0) {
            neighbourCoordinates.add(new Cell(x, y - 1, board[x][y - 1]));
            if (x - 1 >= 0) {
                neighbourCoordinates.add(new Cell(x - 1, y - 1, board[x - 1][y - 1]));
            }
            if (x + 1 < size) {
                neighbourCoordinates.add(new Cell(x + 1, y - 1, board[x + 1][y - 1]));
            }
        }

        // get left cell status
        if (x - 1 >= 0) {
            neighbourCoordinates.add(new Cell(x - 1, y, board[x - 1][y]));
        }

        // get right cell status
        if (x + 1 < size) {
            neighbourCoordinates.add(new Cell(x + 1, y, board[x + 1][y]));
        }

        // get bottom, bottomLeft, bottomRight cells status
        if (y + 1 < size) {
            neighbourCoordinates.add(new Cell(x, y + 1, board[x][y + 1]));
            if (x - 1 >= 0) {
                neighbourCoordinates.add(new Cell(x - 1, y + 1, board[x - 1][y + 1]));
            }
            if (x + 1 < size) {
                neighbourCoordinates.add(new Cell(x + 1, y + 1, board[x + 1][y + 1]));
            }
        }
        return neighbourCoordinates;
    }

    /**
     * Calculate cell status base on following rules.
     *  rule 1 - Any live cell with fewer than two live neighbours dies as if caused by underpopulation.
     *  rule 2 - Any live cell with two or three live neighbours lives on to the next generation.
     *  rule 3 - Any live cell with more than three live neighbours dies, as if by overpopulation.
     *  rule 4 - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     * @param neighbourCoordinates neighbourCoordinates
     * @param cell cell
     */

    @Override
    public void calculateStatusBaseOnNeighbours(List<Cell> neighbourCoordinates, Cell cell) {
        // count "Live" cells in the neighbourCoordinates list
        int count = (int) neighbourCoordinates.stream().filter(Cell::isStatus).count();
        if (count < 2 && cell.isStatus()) {
            // rule 1 - Any live cell with fewer than two live neighbours dies as if caused by underpopulation.
            cell.setStatus(false);
        } else if (count > 3 && cell.isStatus()) {
            //rule 3 - Any live cell with more than three live neighbours dies, as if by overpopulation.
            cell.setStatus(false);
        } else if (count == 3 && !cell.isStatus()) {
            //rule 4 - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            cell.setStatus(true);
        }
    }
}
