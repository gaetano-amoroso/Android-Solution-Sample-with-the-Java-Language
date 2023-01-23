package com.gaetanoamoroso.thread_no;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    int index = 1, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    // binding
        TextView  tv_counter = findViewById(R.id.tv_counter);
        ImageView iv_photo = findViewById(R.id.iv_photo);
        Button btn_load = findViewById(R.id.btn_load);
        Button btn_add_one = findViewById(R.id.btn_add_one);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                switch (index){

                    case 1:
                        bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.flash);
                        index = 2;
                        break;
                    case 2:
                        bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.hulk);
                        index = 3;
                        break;
                    case 3:
                        bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.ironman);
                        index = 1;
                        break;
                }

                iv_photo.setImageBitmap(bitmap);

            }
        });



        btn_add_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_counter.setText("" + count);
            }
        });

    }
}