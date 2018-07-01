package com.yong.game.service;

import com.yong.game.domain.Cell;

import java.util.List;

/**
 * CellService interface
 * Created by yongliu on 7/2/2018.
 */
public interface CellService {

    List<Cell> getNeighbourCells(boolean [][] board, Cell cell);

    void calculateStatusBaseOnNeighbours(List<Cell> neighbourCoordinates, Cell cell);
}
