package com.example.twist.labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twist on 08.01.2018.
 */
//логика игры

public class GameManager extends GestureDetector.SimpleOnGestureListener {
    private List<Drawable> drawables = new ArrayList<>();
    private View view;
    private Exit exit;
    private Player player;
    private Maze maze;
    private Rect rect = new Rect();
    private int screenSize;
    private int lvl=1;

    // конструктор для нового лабиринта
    public GameManager() {
        create(5);
    }



    private void create(int size) {

        drawables.clear();
        maze = new Maze(size);
        drawables.add(maze);
        exit = new Exit(maze.getStart(), size);
        drawables.add(exit);
        player = new Player(maze.getEnd(), size);
        drawables.add(player);

        
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffX, diffY;
        diffX = Math.round(e2.getX() - e1.getX());
        diffY = Math.round(e2.getY() - e1.getY());
        if (Math.abs(diffX) > Math.abs(diffY)) {
            diffX = diffX > 0 ? 1 : -1;
            diffY = 0;
        } else {
            diffX = 0;
            diffY = diffY > 0 ? 1 : -1;
        }
        int stepX = player.getX();
        int stepY = player.getY();

        while (maze.canPlayerGoTo(stepX + diffX, stepY + diffY)) {
            stepX += diffX;
            stepY += diffY;
            if (diffX != 0) {
                if (maze.canPlayerGoTo(stepX, stepY + 1)
                        || maze.canPlayerGoTo(stepX, stepY - 1)) {
                    break;
                }
            }
            if (diffY != 0) {
                if (maze.canPlayerGoTo(stepX + 1, stepY)
                        || maze.canPlayerGoTo(stepX - 1, stepY)) {
                    break;
                }
            }
        }
        player.goTo(stepX, stepY);

        if (exit.getPoint().equals(player.getPoint())) {
            create(maze.getSize() + 3);
        }

        view.invalidate();
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void draw(Canvas canvas) {
        for (Drawable drawableItem :
                drawables) {
            drawableItem.draw(canvas, rect);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setScreenSize(int width, int height) {
        screenSize = Math.min(width, height);
        rect.set(
                (width - screenSize) / 2,
                (height - screenSize) / 2,
                (width + screenSize) / 2,
                (height + screenSize) / 2
        );
    }



}
