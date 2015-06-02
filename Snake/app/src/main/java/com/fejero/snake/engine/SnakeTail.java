package com.fejero.snake.engine;

import com.fejero.snake.Game;
import com.fejero.snake.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class SnakeTail extends SnakeComponent {

    private Bitmap snakeTailImage;

    public SnakeTail(int size, int row, int col, Game game, int player) {
        super(size, row, col, game);
        if (player == 1) {
            Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.snaketail);
            snakeTailImage = Bitmap.createScaledBitmap(bitmap, size, size, true);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.snaketail2);
            snakeTailImage = Bitmap.createScaledBitmap(bitmap, size, size, true);
        }
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //paint.setStyle(Style.FILL_AND_STROKE);
        //paint.setColor(Color.YELLOW);
        //canvas.drawRect(getCol()*getSize(),getRow()*getSize(),getCol()*getSize()+getSize(),getRow()*getSize()+getSize(),paint);
        //paint.setStyle(Style.FILL);

        canvas.drawBitmap(snakeTailImage, getCol() * getSize(), getRow() * getSize(), paint);

    }


}
