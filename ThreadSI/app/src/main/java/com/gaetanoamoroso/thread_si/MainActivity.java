package com.gaetanoamoroso.thread_si;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    int index = 1, count = 0;
    boolean flag = false;
    int progress = 1;
    ProgressBar pb_photo_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    // binding
        TextView  tv_counter = findViewById(R.id.tv_counter);
        ImageView iv_photo = findViewById(R.id.iv_photo);
        Button btn_load = findViewById(R.id.btn_load);
        Button btn_add_one = findViewById(R.id.btn_add_one);
        pb_photo_loading = findViewById(R.id.pb_photo_loading);


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb_photo_loading.setVisibility(View.VISIBLE);
                // THREAD FIGLIO DI MAIN
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for(int i=0; i<100; i++){
                                Thread.sleep(100);
                                progress++;
                                progress();
                            }
                            progress = 0;

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        flag = true;
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




                        // NON POSSO INTERAGIRE CON I COMPONENTI DELLA UI ATTRAVERSO UN THREAD NON MAIN.
                        // SOLO IL PROCESSO CHE HA GENERATO; PROCESSO MAIN PUO' FARLO.
                            // iv_photo.setImageBitmap(bitmap);

                        // DUE SOLUZIONI POSSIBILI
                            // Soluzione 1
                        iv_photo.post(new Runnable() {
                            @Override
                            public void run() {
                                pb_photo_loading.setVisibility(View.INVISIBLE);
                                pb_photo_loading.setProgress(0);
                                iv_photo.setImageBitmap(bitmap);

                            }
                        });

                            // Soluzione 2
//                        MainActivity.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                iv_photo.setImageBitmap(bitmap);
//                            }
//                        });
                    } // END run()
                }).start();
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
    private void progress(){
        if(progress < 100){
            pb_photo_loading.setProgress(progress, true);
        }else {
            pb_photo_loading.setProgress(100);
        }
    }
}