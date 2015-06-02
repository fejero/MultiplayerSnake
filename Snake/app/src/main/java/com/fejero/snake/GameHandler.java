package com.fejero.snake;

import com.fejero.snake.R;
import com.fejero.snake.engine.Direction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class GameHandler extends Activity {


    private Game game;
    private Button buyButton;
    private GameLayout layout;
    private Thread runTime;
    private Vibrator vibrator;
    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        game = new Game(this);
        setContentView(game);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //mDetector = new GestureDetectorCompat(this, new MyGestureListener(vibrator));
        View view = getWindow().getDecorView().getRootView();


        view.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                //toggleSomething();
            }
        });


        view.setOnTouchListener(new OnSwipeTouchListener(game) {
            public boolean onSwipeTop() {
                vibrator.vibrate(20);
                if (game.getSnake().getSnakeHead().getDirection() != Direction.SOUTH)
                    game.getSnake().getSnakeBody().get(0).setDirection(Direction.NORTH);
                return true;
            }

            public boolean onSwipeRight() {
                vibrator.vibrate(20);
                if (game.getSnake().getSnakeHead().getDirection() != Direction.WEST)
                    game.getSnake().getSnakeBody().get(0).setDirection(Direction.EAST);
                return true;
            }

            public boolean onSwipeLeft() {
                vibrator.vibrate(20);
                if (game.getSnake().getSnakeHead().getDirection() != Direction.EAST)
                    game.getSnake().getSnakeBody().get(0).setDirection(Direction.WEST);
                return true;
            }

            public boolean onSwipeBottom() {
                vibrator.vibrate(20);
                if (game.getSnake().getSnakeHead().getDirection() != Direction.NORTH)
                    game.getSnake().getSnakeBody().get(0).setDirection(Direction.SOUTH);
                return true;
            }
        });


        runTime = new Thread() {
            @Override
            public void run() {
                while (game.getState() != GameState.QUIT) {
                    game.update();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
                    }
                    //game.invalidate();
                }

                if (game.getState() == GameState.QUIT) {


                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
                    }
                }


                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                startActivity(new Intent("com.fejero.snake.MENU"));
                //setContentView(R.layout.menu);


            }
        };

        runTime.start();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public Thread getRunTime() {
        return runTime;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //setContentView(R.layout.menu);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //setContentView(R.layout.menu);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        finish();
    }

    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }


}
