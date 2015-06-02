package com.fejero.snake.engine;

import java.util.ArrayList;
import java.util.List;

import com.fejero.snake.Game;

public class World {

    private int rows, cols;
    private List<Wall> wallList = new ArrayList<Wall>();

    public World(int width, int height, int headSize, Game game) {
        // TODO Auto-generated constructor stub
        cols = width / headSize;
        rows = height / headSize;

        wallList.add(new Wall(5, 7, headSize, game));
        wallList.add(new Wall(5, 8, headSize, game));
        wallList.add(new Wall(5, 9, headSize, game));
        wallList.add(new Wall(5, 12, headSize, game));
        wallList.add(new Wall(5, 13, headSize, game));
        wallList.add(new Wall(5, 14, headSize, game));
        wallList.add(new Wall(16, 7, headSize, game));
        wallList.add(new Wall(16, 8, headSize, game));
        wallList.add(new Wall(16, 9, headSize, game));
        wallList.add(new Wall(16, 12, headSize, game));
        wallList.add(new Wall(16, 13, headSize, game));
        wallList.add(new Wall(16, 14, headSize, game));

    }

    public List<Wall> getWallList() {
        return wallList;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

}
