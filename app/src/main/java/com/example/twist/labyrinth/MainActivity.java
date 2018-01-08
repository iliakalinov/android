package com.example.twist.labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//пользовательский интерфейс
public class MainActivity extends AppCompatActivity {

    private MazeView view;
    private GestureDetector gestureDetector;
     private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            gameManager = new GameManager();

        view = new MazeView(this, gameManager);
        setContentView(view);

        gestureDetector = new GestureDetector(this, gameManager);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }

}



