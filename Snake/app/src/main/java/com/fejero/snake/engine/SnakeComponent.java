package com.fejero.snake.engine;

import com.fejero.snake.Game;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class SnakeComponent {

    private int size;
    private int col;
    private int row;
    private Direction direction;

    public SnakeComponent(int size, int row, int col, Game game) {
        // TODO Auto-generated constructor stub
        this.size = size;
        this.row = row;
        this.col = col;
        direction = Direction.EAST;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSize() {
        return size;
    }

    abstract public void draw(Canvas canvas, Paint paint);
}
