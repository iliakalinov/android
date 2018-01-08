package com.example.twist.labyrinth;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by twist on 08.01.2018.
 */


public class Maze implements Drawable {

    private Paint wallPaint;
    private boolean[][] array;

    public int getSize() {
        return size;
    }

    private int size;
    private int bestScore = 0;
    private Point start;
    private Point end = new Point(1, 1);

    // общие настройки конструктора
    private void initMaze() {
        wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wallPaint.setColor(Color.BLUE);
    }

    public Maze(int size) {
        initMaze();
        this.size = size;
        array = new boolean[size][size];
        // генерация
        generateMaze();
    }




    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    private void generateMaze() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                array[i][j] = i % 2 != 0 && j % 2 != 0
                        && i < size - 1 && j < size - 1;
            }
        }
        Random random = new Random();
        Stack<Point> stack = new Stack<>();
        stack.push(end);
        while (stack.size() > 0) {
            Point current = stack.peek();
            List<Point> unusedNeighbors = new LinkedList<>();
            //left
            if (current.x > 2) {
                if (!isUsedCell(current.x - 2, current.y)) {
                    unusedNeighbors.add(new Point(current.x - 2, current.y));
                }
            }
            //verh
            if (current.y > 2) {
                if (!isUsedCell(current.x, current.y - 2)) {
                    unusedNeighbors.add(new Point(current.x, current.y - 2));
                }
            }
            //right
            if (current.x < size - 2) {
                if (!isUsedCell(current.x + 2, current.y)) {
                    unusedNeighbors.add(new Point(current.x + 2, current.y));
                }
            }
            //niz
            if (current.y < size - 2) {
                if (!isUsedCell(current.x, current.y + 2)) {
                    unusedNeighbors.add(new Point(current.x, current.y + 2));
                }
            }
            if (unusedNeighbors.size() > 0) {
                int rnd = random.nextInt(unusedNeighbors.size());
                Point direction = unusedNeighbors.get(rnd);
                int diffX = (direction.x - current.x) / 2;
                int diffY = (direction.y - current.y) / 2;
                array[current.y + diffY][current.x + diffX] = true;
                stack.push(direction);
            } else {
                if (bestScore < stack.size()) {
                    bestScore = stack.size();
                    start = current;
                }
                stack.pop();
            }
        }
    }

    public boolean isCrossroad(int x, int y) {
        return isUsedCell(x, y);
    }

    public boolean canPlayerGoTo(int x, int y) {
        return array[y][x];
    }

    private boolean isUsedCell(int x, int y) {
        if (x < 0 || y < 0 || x >= size - 1 || y >= size - 1) {
            return true;
        }
        return array[y - 1][x] //left
                || array[y][x - 1] //verh
                || array[y + 1][x] //right
                || array[y][x + 1]; //niz
    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize = (float) (rect.right - rect.left) / size;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (!array[i][j]) {
                    float left = j * cellSize + rect.left;
                    float top = i * cellSize + rect.top;
                    canvas.drawRect(
                            left,
                            top,
                            left + cellSize,
                            top + cellSize,
                            wallPaint
                    );
                }
            }
        }
    }





}
