package com.fejero.snake.engine;

import java.util.ArrayList;

import com.fejero.snake.Game;
import com.fejero.snake.GameState;
import com.fejero.snake.R;
import com.fejero.snake.beepers.Beeper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Snake {

    private int actualRow;
    private int actualCol;
    private ArrayList<SnakeComponent> snakeBody = new ArrayList<SnakeComponent>();
    private World world;
    private Game game;
    private int score;
    private int player;


    public Snake(int headSize, int row, int col, World world, Game game, int player) {
        // TODO Auto-generated constructor stub
        actualCol = col;
        actualRow = row;
        this.world = world;
        this.game = game;
        score = 0;
        this.player = player;

        snakeBody.add(new SnakeHead(headSize, row, col, game, player));
        addTail(snakeBody.get(snakeBody.size() - 1));
        addTail(snakeBody.get(snakeBody.size() - 1));
        addTail(snakeBody.get(snakeBody.size() - 1));

    }

    public void addTailToEnd() {
        addTail(snakeBody.get(snakeBody.size() - 1));
    }

    public void removeTailFromEnd() {
        snakeBody.remove(snakeBody.size() - 1);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    private void addTail(SnakeComponent component) {
        switch (component.getDirection()) {
            case NORTH:
                snakeBody.add(new SnakeTail(component.getSize(), component.getRow() + 1, component.getCol(), game, player));
                break;
            case SOUTH:
                snakeBody.add(new SnakeTail(component.getSize(), component.getRow() - 1, component.getCol(), game, player));
                break;
            case EAST:
                snakeBody.add(new SnakeTail(component.getSize(), component.getRow(), component.getCol() - 1, game, player));
                break;
            case WEST:
                snakeBody.add(new SnakeTail(component.getSize(), component.getRow(), component.getCol() + 1, game, player));
                break;
            default:
                break;
        }

    }

    public SnakeComponent getSnakeHead() {
        return snakeBody.get(0);
    }

    public int getActualCol() {
        return actualCol;
    }

    public int getActualRow() {
        return actualRow;
    }

    public ArrayList<SnakeComponent> getSnakeBody() {
        return snakeBody;
    }


    public void move() {
        boolean processed = false;
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).setCol(snakeBody.get(i - 1).getCol());
            snakeBody.get(i).setRow(snakeBody.get(i - 1).getRow());
        }

        switch (snakeBody.get(0).getDirection()) {
            case EAST:
                if (snakeBody.get(0).getCol() + 1 != world.getCols()) {
                    snakeBody.get(0).setCol(snakeBody.get(0).getCol() + 1);
                    processed = true;
                } else {
                    snakeBody.get(0).setCol(0);
                    processed = true;
                }
                break;
            case WEST:
                if (snakeBody.get(0).getCol() - 1 != -1) {
                    snakeBody.get(0).setCol(snakeBody.get(0).getCol() - 1);
                    processed = true;
                } else {
                    snakeBody.get(0).setCol(world.getCols() - 1);
                    processed = true;
                }
                break;
            case NORTH:
                if (snakeBody.get(0).getRow() - 1 != -1) {
                    snakeBody.get(0).setRow(snakeBody.get(0).getRow() - 1);
                    processed = true;
                } else {
                    snakeBody.get(0).setRow(world.getRows() - 1);
                    processed = true;
                }
                break;
            case SOUTH:
                if (snakeBody.get(0).getRow() + 1 != world.getRows()) {

                    snakeBody.get(0).setRow(snakeBody.get(0).getRow() + 1);
                    processed = true;
                } else {
                    snakeBody.get(0).setRow(0);
                    processed = true;
                }
                break;
            default:
                break;
        }

        for (Wall wall : world.getWallList()) {
            if (wall.getCol() == snakeBody.get(0).getCol() && wall.getRow() == snakeBody.get(0).getRow()) {
                processed = false;
            }
        }

        for (SnakeComponent component : game.getSnake().getSnakeBody()) {
            if (component.getCol() == snakeBody.get(0).getCol() && component.getRow() == snakeBody.get(0).getRow()) {
                if (component != snakeBody.get(0))
                    processed = false;
            }
        }

        for (SnakeComponent component : game.getSnake2().getSnakeBody()) {
            if (component.getCol() == snakeBody.get(0).getCol() && component.getRow() == snakeBody.get(0).getRow()) {
                if (component != snakeBody.get(0))
                    processed = false;
            }
        }


        if (processed == false) {
            game.setState(GameState.QUIT);
        }

    }


}
