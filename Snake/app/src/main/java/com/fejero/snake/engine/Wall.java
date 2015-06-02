package com.fejero.snake.engine;

import com.fejero.snake.Game;
import com.fejero.snake.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Wall {

    private int col;
    private int row;
    public int size;
    private Bitmap wallBitmap;


    public Wall(int row, int col, int size, Game game) {

        this.row = row;
        this.col = col;
        this.size = size;

        Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.wall);
        wallBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void drawWall(Canvas canvas, Paint paint) {


        canvas.drawBitmap(wallBitmap, col * size, row * size, paint);
    }

}
