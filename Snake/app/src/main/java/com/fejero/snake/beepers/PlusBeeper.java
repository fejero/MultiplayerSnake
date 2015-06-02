package com.fejero.snake.beepers;

import com.fejero.snake.Game;
import com.fejero.snake.R;
import com.fejero.snake.engine.Snake;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlusBeeper extends Beeper {


    private Bitmap redApple;

    public PlusBeeper(int size, int col, int row, Game game) {
        super(size, col, row, game);

        Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.drawable.redapple);
        redApple = Bitmap.createScaledBitmap(bitmap, size, size, true);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //paint.setColor(Color.BLACK);
        //canvas.drawRect(getCol()*getSize(),getRow()*getSize(),getCol()*getSize()+getSize(),getRow()*getSize()+getSize(),paint);
        canvas.drawBitmap(redApple, getCol() * getSize(), getRow() * getSize(), paint);
    }

    @Override
    public void execute(Snake snake) {
        snake.addTailToEnd();
    }

}
