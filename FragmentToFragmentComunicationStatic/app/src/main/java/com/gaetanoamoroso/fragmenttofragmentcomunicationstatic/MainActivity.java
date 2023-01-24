package com.gaetanoamoroso.fragmenttofragmentcomunicationstatic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DoIncrementFragment.OnCouterUpdateListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCounterUpdate(int counter) {
        FragmentManager fm =  getSupportFragmentManager();
        DisplayCounterFragment displayCounterFragment = (DisplayCounterFragment)fm.findFragmentById(R.id.fragment_container_Display_counter);
        if(displayCounterFragment != null){
            displayCounterFragment.displayUpdate(counter);
        }

    }
}