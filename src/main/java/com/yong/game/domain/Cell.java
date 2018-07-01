package com.yong.game.domain;

import java.io.Serializable;

/**
 * Cell Object to hold game board cell coordinate and status (live or dead)
 * Created by yongliu on 7/2/2018.
 */
public class Cell implements Serializable {
    private static final long serialVersionUID = 5371390649862767920L;

    // coordinate X
    private final int x;
    // coordinate y
    private final int y;
    // cell status (live or dead)
    private boolean status;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, boolean status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;
        return status == cell.status;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }
}
