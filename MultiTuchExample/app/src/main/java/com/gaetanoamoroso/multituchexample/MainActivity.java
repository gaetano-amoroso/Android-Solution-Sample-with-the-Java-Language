package com.gaetanoamoroso.multituchexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout main_layout;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        main_layout = findViewById(R.id.main_layout);
        main_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        counter++;
                        Log.d("DEBUG", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        counter++;
                        Log.d("DEBUG", "ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("DEBUG", "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        counter--;
                        Log.d("DEBUG", "ACTION_UP");

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        counter--;
                        Log.d("DEBUG", "ACTION_POINTER_UP");
                        break;

                }

                Log.d("DEBUG", "IL numero di dita attualmente sullo schermo è: "+ counter);
                return true;
            }
        });



    }
}