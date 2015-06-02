package com.fejero.snake.engine;

import com.fejero.snake.Game;
import com.fejero.snake.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SnakeHead extends SnakeComponent {

    private Bitmap snakeHeadImage;


    public SnakeHead(int size, int row, int col, Game game, int player) {
        super(size, row, col, game);
        if (player == 1) {
            Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.snakehead);
            snakeHeadImage = Bitmap.createScaledBitmap(bitmap, size, size, true);
        }// TODO Auto-generated constructor stub
        else {
            Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.snakehead2);
            snakeHeadImage = Bitmap.createScaledBitmap(bitmap, size, size, true);
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(getCol()*getSize(),getRow()*getSize(),getCol()*getSize()+getSize(),getRow()*getSize()+getSize(),paint);
        canvas.drawBitmap(snakeHeadImage, getCol() * getSize(), getRow() * getSize(), paint);
    }


}
