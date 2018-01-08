package com.example.twist.labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by twist on 08.01.2018.
 */

public class MazeView extends View {
    private GameManager gameManager;

    public MazeView(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        gameManager.setView(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameManager.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameManager.setScreenSize(w, h);
    }
}