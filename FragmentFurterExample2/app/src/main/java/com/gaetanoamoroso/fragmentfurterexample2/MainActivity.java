package com.gaetanoamoroso.fragmentfurterexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView lv_details;
    Button btn_open_close;
    boolean is_fragment_opened = false;

    ArrayAdapter<String> arrayAdapter;
    String[] details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_details = findViewById(R.id.lv_details);
        btn_open_close = findViewById(R.id.btn_open_close);
        details = getResources().getStringArray(R.array.details);
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.detail_item, R.id.tv_detail_item, Arrays.asList(details));
        lv_details.setAdapter(arrayAdapter);


        btn_open_close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(is_fragment_opened){
                    closeFragment();
                }else{
                    displayFragment();
                }
            }
        });
    }

    public void displayFragment(){
        FeedBackFragment feedBackFragment = FeedBackFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.Fragment_comtainer, feedBackFragment)
                .addToBackStack("feedBackFrafment")
                .commit();


        //
        btn_open_close.setText("CLOSE");
        is_fragment_opened = true;
    }
    public void closeFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FeedBackFragment feedBackFragment = (FeedBackFragment)fm.findFragmentById(R.id.Fragment_comtainer);
        if (is_fragment_opened && feedBackFragment!= null) {
           fm.beginTransaction().remove(feedBackFragment).commit();
        }
        btn_open_close.setText("OPEN");
        is_fragment_opened = false;
        }



    }
