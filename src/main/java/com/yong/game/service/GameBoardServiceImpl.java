package com.yong.game.service;

import com.yong.game.domain.Cell;
import com.yong.game.domain.GameBoard;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * GameBoardService implementation object
 * Created by yongliu on 7/2/2018.
 */
public class GameBoardServiceImpl implements GameBoardService {

    private CellService cellService;

    public GameBoardServiceImpl(CellService cellService) {
        this.cellService = cellService;
    }

    @Override
    public GameBoard initialGameBoardWithCoordinates(List<Cell> coordinates) {
        GameBoard gameBoard = new GameBoard();
        for (Cell cell:coordinates) {
            gameBoard.getBoard()[cell.getX()][cell.getY()] = true;
        }
        return gameBoard;
    }

    @Override
    public GameBoard executeStages(int stages, GameBoard initGameBoard) {
        GameBoard currentGameBoard = initGameBoard;
        for (int stage = 0; stage < stages; stage++) {
            System.out.print(String.format("Stage %d: ", stage + 1));
            currentGameBoard = getNextStageGameBoard(currentGameBoard.getBoard());
        }
        return currentGameBoard;
    }

    protected GameBoard getNextStageGameBoard(boolean[][] board) {

        GameBoard nextGameBoard = new GameBoard();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                Cell cell = new Cell(x, y, board[x][y]);
                List<Cell> neighbourCoordinates = cellService.getNeighbourCells(board, cell);
                cellService.calculateStatusBaseOnNeighbours(neighbourCoordinates, cell);
                nextGameBoard.getBoard()[x][y] = cell.isStatus();
            }
        }
        System.out.println(printLiveCellCoordinates(nextGameBoard.getBoard()));
        return nextGameBoard;
    }

    protected String printLiveCellCoordinates(boolean[][] board) {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[x][y]) {
                    result.append(String.format("[%d, %d],", x, y));
                }
            }
        }
        return StringUtils.removeEnd(result.toString(), ",");
    }
}

