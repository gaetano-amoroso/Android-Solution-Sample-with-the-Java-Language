package com.gaetanoamoroso.fragmentfurtherexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding
        ListView lv_details = findViewById(R.id.lv_details);

        // Recupero i dettagli dalle risorsa array-string
        String[] details = getResources().getStringArray(R.array.details);

        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(MainActivity.this,
                                                                            R.layout.detail_item,
                                                                            R.id.tv_item_details,
                                                                            Arrays.asList(details)
                                                                           );
        lv_details.setAdapter(arrayAdapter);

    }
}