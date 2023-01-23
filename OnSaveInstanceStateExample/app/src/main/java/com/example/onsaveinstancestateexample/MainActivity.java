package com.example.onsaveinstancestateexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter;
    TextView tv_counter;
    Button btn_add_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // binding view resources
        tv_counter = findViewById(R.id.tv_counter);
        btn_add_one = findViewById(R.id.btn_add_one);

        // If a configuration change is happened then Bundle variable aren't empty so the state
        // restored.
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("saved_counter");
            tv_counter.setText(""+ counter);
        }else{
            counter = 0;
        }

        btn_add_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tv_counter.setText(""+counter);
            }
        });
    }
    // Richiamato per recuperare lo stato della singola istanza da un'attività prima di essere uccisa,
    // in modo che lo stato possa essere ripristinato in onCreate o in onRestoreInstanceState
    // (il bundle popolato da questo metodo sarà passato a entrambi).
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("saved_counter", counter);
    }


}