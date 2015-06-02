package com.fejero.snake.beepers;

import com.fejero.snake.Game;
import com.fejero.snake.engine.Snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


abstract public class Beeper {

    int col;
    int row;
    int size;
    Game game;


    public Beeper(int size, int col, int row, Game game) {

        this.game = game;
        this.row = row;
        this.col = col;
        this.size = size;

        // TODO Auto-generated constructor stub
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getSize() {
        return size;
    }


    abstract public void draw(Canvas canvas, Paint paint);

    abstract public void execute(Snake snake);
}
