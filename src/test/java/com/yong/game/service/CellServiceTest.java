package com.yong.game.service;

import com.yong.game.domain.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yongliu on 2/7/18.
 */
public class CellServiceTest {

    private CellService cellService;

    @Before
    public void setUp() throws Exception {
        cellService = new CellServiceImpl();
    }

    @Test
    public void getNeighbourCells() throws Exception {

        boolean[][] board = {{false, false, true},{true, true, false},{false, false, false}};

        Cell cell = new Cell(1, 1, true);
        List<Cell> neighbourCells = cellService.getNeighbourCells(board, cell);
        Assert.assertEquals(8, neighbourCells.size());

        Cell cellCorner = new Cell(0, 0, true);
        List<Cell> neighbourCellsCorner = cellService.getNeighbourCells(board, cellCorner);
        Assert.assertEquals(3, neighbourCellsCorner.size());

        Cell cellEdge = new Cell(1, 0, true);
        List<Cell> neighbourCellsEdge = cellService.getNeighbourCells(board, cellEdge);
        Assert.assertEquals(5, neighbourCellsEdge.size());

    }

    @Test
    public void calculateStatusBaseOnNeighboursRule1() throws Exception {

        //*  rule 1 - Any live cell with fewer than two live neighbours dies as if caused by underpopulation.
        boolean[] statues = {true, false, false, false, false};
        List<Cell> neighbourCells = getCells(statues);
        Cell cellRule1 = new Cell(0,1, true);
        cellService.calculateStatusBaseOnNeighbours(neighbourCells, cellRule1);

        Assert.assertFalse(cellRule1.isStatus());
    }

    @Test
    public void calculateStatusBaseOnNeighboursRule2() throws Exception {

        //*  rule 2 - Any live cell with two or three live neighbours lives on to the next generation.
        boolean[] statues = {true, true, false, false, false};
        List<Cell> neighbourCells = getCells(statues);
        Cell cellRule2 = new Cell(0,1, true);
        cellService.calculateStatusBaseOnNeighbours(neighbourCells, cellRule2);

        Assert.assertTrue(cellRule2.isStatus());
    }

    @Test
    public void calculateStatusBaseOnNeighboursRule3() throws Exception {

        //*  rule 3 - Any live cell with more than three live neighbours dies, as if by overpopulation.
        boolean[] statues = {true, true, true, true, false};
        List<Cell> neighbourCells = getCells(statues);
        Cell cellRule3 = new Cell(0,1, true);
        cellService.calculateStatusBaseOnNeighbours(neighbourCells, cellRule3);

        Assert.assertFalse(cellRule3.isStatus());

    }

    @Test
    public void calculateStatusBaseOnNeighboursRule4() throws Exception {

        //*  rule 4 - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        boolean[] statues = {true, true, true, false, false};
        List<Cell> neighbourCells = getCells(statues);
        Cell cellRule4 = new Cell(0,1, false);
        cellService.calculateStatusBaseOnNeighbours(neighbourCells, cellRule4);

        Assert.assertTrue(cellRule4.isStatus());
    }

    private List<Cell> getCells(boolean[] statuses) {
        List<Cell> neighbourCells = new ArrayList<>();

        Cell a = new Cell(0,0);
        Cell b = new Cell(0,1);
        Cell c = new Cell(1,1);
        Cell d = new Cell(0,2);
        Cell e = new Cell(1,2);

        neighbourCells.add(a);
        neighbourCells.add(b);
        neighbourCells.add(c);
        neighbourCells.add(d);
        neighbourCells.add(e);

        for (int i = 0; i <statuses.length; i++ ) {
            neighbourCells.get(i).setStatus(statuses[i]);
        }

        return neighbourCells;
    }

}