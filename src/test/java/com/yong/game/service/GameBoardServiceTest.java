package com.yong.game.service;

import com.yong.game.domain.Cell;
import com.yong.game.domain.GameBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for GameBordService
 * Created by yongliu on 2/7/18.
 */
public class GameBoardServiceTest {

    private CellService cellService = new CellServiceImpl();

    private GameBoardServiceImpl gameBoardService;

    @Before
    public void setUp() throws Exception {
        gameBoardService = new GameBoardServiceImpl(cellService);
    }

    @Test
    public void initialGameBoardWithCoordinates() throws Exception {

        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0,0));
        cells.add(new Cell(3,2));
        GameBoard gameBoard = gameBoardService.initialGameBoardWithCoordinates(cells);

        Assert.assertEquals(true, gameBoard.getBoard()[0][0]);
        Assert.assertEquals(true, gameBoard.getBoard()[3][2]);
    }

    @Test
    public void executeStages() throws Exception {

        GameBoard initStage = new GameBoard();
        initStage.getBoard()[0][0] = true;
        initStage.getBoard()[0][1] = true;
        initStage.getBoard()[1][1] = true;

        GameBoard gameBoard = gameBoardService.executeStages(2, initStage);
        Assert.assertTrue(gameBoard.getBoard()[0][0]);
        Assert.assertTrue(gameBoard.getBoard()[1][0]);
        Assert.assertTrue(gameBoard.getBoard()[0][1]);
        Assert.assertTrue(gameBoard.getBoard()[1][1]);
    }


    /*
        Example initGameBoard
        1, 0, 0, 0
        1, 1, 0, 0

        ->
        nextGameBoard
        1, 1, 0, 0
        1, 1, 0, 0
     */
    @Test
    public void getNextStageGameBoard() throws Exception {

        GameBoard initStage = new GameBoard();
        initStage.getBoard()[0][0] = true;
        initStage.getBoard()[0][1] = true;
        initStage.getBoard()[1][1] = true;
        GameBoard nextStage = gameBoardService.getNextStageGameBoard(initStage.getBoard());

        Assert.assertTrue(nextStage.getBoard()[0][0]);
        Assert.assertTrue(nextStage.getBoard()[1][0]);
        Assert.assertTrue(nextStage.getBoard()[0][1]);
        Assert.assertTrue(nextStage.getBoard()[1][1]);


    }

    @Test
    public void printLiveCellCoordinates() throws Exception {

        String expectedPrint = "[1, 1],[1, 2]";
        GameBoard gameBoard = new GameBoard();
        gameBoard.getBoard()[1][1] = true;
        gameBoard.getBoard()[1][2] = true;

        String print = gameBoardService.printLiveCellCoordinates(gameBoard.getBoard());

        Assert.assertEquals(expectedPrint, print);
    }
}