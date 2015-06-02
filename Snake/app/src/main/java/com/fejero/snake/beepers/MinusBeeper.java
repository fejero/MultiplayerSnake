package com.fejero.snake.beepers;

import com.fejero.snake.Game;
import com.fejero.snake.R;
import com.fejero.snake.engine.Snake;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MinusBeeper extends Beeper {


    private Bitmap greenApple;

    public MinusBeeper(int size, int col, int row, Game game) {
        super(size, col, row, game);
        // TODO Auto-generated constructor stub

        Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.greenapple);
        greenApple = Bitmap.createScaledBitmap(bitmap, size, size, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //paint.setColor(Color.RED);
        //canvas.drawRect(getCol()*getSize(),getRow()*getSize(),getCol()*getSize()+getSize(),getRow()*getSize()+getSize(),paint);

        canvas.drawBitmap(greenApple, getCol() * getSize(), getRow() * getSize(), paint);
    }

    @Override
    public void execute(Snake snake) {
        // TODO Auto-generated method stub
        if (snake.getSnakeBody().size() > 2) {
            snake.removeTailFromEnd();
        }

    }

}
