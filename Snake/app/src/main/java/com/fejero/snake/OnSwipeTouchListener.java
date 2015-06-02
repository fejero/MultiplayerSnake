package com.fejero.snake;

import com.fejero.snake.engine.Direction;
import com.fejero.snake.engine.Snake;

import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private Game game;
    private Snake snake;
    private Vibrator vibrator;
    private int playHeight;
    private int buttonSize;
    private int screenWidth;

    public OnSwipeTouchListener(Game game) {
        this.game = game;
        snake = game.getSnake();
        vibrator = game.getVibrator();
        buttonSize = game.getButtonSize();
        playHeight = game.getPlayHeight();
        screenWidth = game.getScreenWidth();
        // TODO Auto-generated constructor stub
    }

    private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

    public boolean onTouch(final View v, final MotionEvent event) {
        int playHeight = (int) (this.playHeight - 2.5 * buttonSize);
        snake = game.getSnake2();

        if (event.getY() >= playHeight && event.getY() <= playHeight + buttonSize && event.getX() >= screenWidth / 2 - buttonSize / 2 && event.getX() <= screenWidth / 2 + buttonSize / 2) {
            vibrator.vibrate(20);
            if (snake.getSnakeHead().getDirection() != Direction.SOUTH) {
                snake.getSnakeBody().get(0).setDirection(Direction.NORTH);
                return true;
            }
        } else if (event.getY() >= playHeight + (int) (buttonSize * 1.5) && event.getY() <= playHeight + (int) (buttonSize * 1.5) + buttonSize && event.getX() >= screenWidth / 2 - buttonSize / 2 && event.getX() <= screenWidth / 2 + buttonSize / 2) {
            vibrator.vibrate(20);
            if (snake.getSnakeHead().getDirection() != Direction.NORTH) {
                snake.getSnakeBody().get(0).setDirection(Direction.SOUTH);
                return true;
            }
        } else if (event.getY() >= playHeight + (int) (buttonSize * 0.7) && event.getY() <= playHeight + (int) (buttonSize * 0.7) + buttonSize && event.getX() >= screenWidth / 2 - buttonSize * 2 && event.getX() <= screenWidth / 2 - buttonSize * 2 + buttonSize) {
            vibrator.vibrate(20);
            if (snake.getSnakeHead().getDirection() != Direction.EAST) {
                snake.getSnakeBody().get(0).setDirection(Direction.WEST);
                return true;
            }
        } else if (event.getY() >= playHeight + (int) (buttonSize * 0.7) && event.getY() <= playHeight + (int) (buttonSize * 0.7) + buttonSize && event.getX() >= screenWidth / 2 + buttonSize && event.getX() <= screenWidth / 2 + buttonSize + buttonSize) {
            vibrator.vibrate(20);
            if (snake.getSnakeHead().getDirection() != Direction.WEST) {
                snake.getSnakeBody().get(0).setDirection(Direction.EAST);
                return true;
            }

        } else {

        }


        return gestureDetector.onTouchEvent(event);
    }


    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            result = onSwipeRight();
                        } else {
                            result = onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            result = onSwipeBottom();
                        } else {
                            result = onSwipeTop();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }


    public boolean onSwipeRight() {
        return false;
    }

    public boolean onSwipeLeft() {
        return false;
    }

    public boolean onSwipeTop() {
        return false;
    }

    public boolean onSwipeBottom() {
        return false;
    }

}
