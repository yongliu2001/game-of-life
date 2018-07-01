package com.yong.game;

import com.yong.game.domain.Cell;
import com.yong.game.domain.GameBoard;
import com.yong.game.service.CellServiceImpl;
import com.yong.game.service.GameBoardService;
import com.yong.game.service.GameBoardServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GameBoardService gameBoardService = new GameBoardServiceImpl(new CellServiceImpl());
        GameBoard initGameBoard = gameBoardService.initialGameBoardWithCoordinates(getInputCoordinates());
        gameBoardService.executeStages(100, initGameBoard);

    }

    //intput: [[5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6]]
    private static List<Cell> getInputCoordinates() {
        List<Cell> coordinates = new ArrayList<>();
        coordinates.add(new Cell(5,5));
        coordinates.add(new Cell(6,5));
        coordinates.add(new Cell(7,5));
        coordinates.add(new Cell(5,6));
        coordinates.add(new Cell(6,6));
        coordinates.add(new Cell(7,6));

        return coordinates;
    }

}
