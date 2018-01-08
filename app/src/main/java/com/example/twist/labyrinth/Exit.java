package com.example.twist.labyrinth;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by twist on 08.01.2018.
 */
public class Exit extends Dot {
    public Exit(Point point, int size) {
        super(size, point, getPaint());
    }
    private static Paint getPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        return paint;
    }
}

