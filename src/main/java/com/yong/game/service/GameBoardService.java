package com.yong.game.service;

import com.yong.game.domain.Cell;
import com.yong.game.domain.GameBoard;

import java.util.List;

/**
 * GameBoardService interface
 * Created by yongliu on 7/2/2018.
 */
public interface GameBoardService {

    GameBoard initialGameBoardWithCoordinates(List<Cell> coordinates);

    GameBoard executeStages(int stages, GameBoard initGameBoard);
}
