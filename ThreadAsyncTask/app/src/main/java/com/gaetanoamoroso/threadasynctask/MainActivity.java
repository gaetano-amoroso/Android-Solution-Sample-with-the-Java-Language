package com.gaetanoamoroso.threadasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_counter;
    Button btn_load_image, btn_add_one;
    ImageView iv_photo_loaded;
    ProgressBar pb_photo_progres_bar;

    int count = 0, progress = 0, index = 1, id_photo_res = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_counter = findViewById(R.id.tv_counter);
        btn_load_image = findViewById(R.id.btn_load_image);
        btn_add_one = findViewById(R.id.btn_add_one);
        iv_photo_loaded = findViewById(R.id.iv_photo_loaded);
        pb_photo_progres_bar = findViewById(R.id.pb_photo_progres_bar);

        // Codice da eseguire nel main thread
        btn_add_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_counter.setText("" + count);
            }
        });

        // Recupero id risorsa da passare come parametro generico PARAM all'asynctask
        btn_load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index){
                    case 1: id_photo_res = R.drawable.flash;
                        index = 2;
                        break;

                    case 2: id_photo_res = R.drawable.hulk;
                        index = 3;
                        break;

                    case 3: id_photo_res = R.drawable.ironman;
                        index = 1;
                        break;
                }

                // Istanzio un asynctask e lo seguo sull'UI thread, nel nostro caso il main thread.
                new LoadImageTask().execute(id_photo_res);
            }
        });





    }

    // Codice da Implementare con la classe AsynkTask

    class LoadImageTask extends AsyncTask<Integer, Integer, Bitmap>{
        // Metodo eseguito nel main threads per operazioni d'inizializzazione e setup del task asincrono.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_photo_progres_bar.setVisibility(View.VISIBLE);
        }


        // Metodo eseguito nel thread in background per svolgere le operazioni in concorrenza, ovvero
        // la computazione in background. Il parametro passato al metodo è il tipo generico Params.
        // Params descrive i parametri su cui il thread in background deve operare; è un variable
        // length argument.
        @Override
        protected Bitmap doInBackground(Integer... id_photos_res) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id_photos_res[0] );

            // Simulo il ritardo di caricamento da internet
            for(int i = 0; i< 100; i++){
                try {
                    Thread.sleep(50);
                    progress++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // metodo che pubblica i progressi intermedi della computazione in background
                publishProgress(progress * 10);
            }

            return bitmap; // Tipo generico RESULT
        }



        @Override
        protected void onProgressUpdate(Integer... progresses) {
            super.onProgressUpdate(progresses);
            pb_photo_progres_bar.setProgress(progresses[0]);
            if(progress > (pb_photo_progres_bar.getMax()/4)*3){
                Toast.makeText(MainActivity.this, "Ci siamo quasi....", Toast.LENGTH_SHORT).show();
            }
        }



        // Metodo eseguito nel main threads
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            pb_photo_progres_bar.setVisibility(View.INVISIBLE);
            pb_photo_progres_bar.setProgress(0);
            progress = 0;
            iv_photo_loaded.setImageBitmap(bitmap);
        }
        // Metodo eseguito nel main threads a seguito dell'invocazione del metodo publishProgress(PROGRESS)
        // nel corpo di doInBackGround(PARAMS)


    }





    // Binding

}