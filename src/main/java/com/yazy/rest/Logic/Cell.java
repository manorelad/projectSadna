package com.yazy.rest.Logic;

public class Cell {
    private int value;
    private boolean canClick;

    public Cell() {
    }

    public Cell(int value, boolean canClick) {
        this.value = value;
        this.canClick = canClick;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", canClick=" + canClick +
                '}';
    }
}
