package com.gaetanoamoroso.corsoandroid.activitylifeciclewithconfigchangeenabled;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_LIFE_CICLE";
    ArrayAdapter array_adapter;
    ListView list_view;
    String message;
    ArrayList lista = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.listCallBackEvent);
        array_adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, R.id.tvItemOfList, lista);
        list_view.setAdapter(array_adapter);
        Log.d(TAG, "onCreate() --> " + this.getClass().getSimpleName());
        addString("onCreate()");

    }

    private void addString(String str) {
        long time = SystemClock.elapsedRealtime();
        int seconds = (int) time / 1000;
        int milliseconds = (int) (time - seconds * 1000);
        int minutes = seconds / 60;
        seconds = seconds - (minutes * 60);
        int hour = (int) minutes / 60;
        minutes = minutes - hour * 60;
        int day = (int) hour / 24;
        hour = hour - day * 24;
        message = "In hours: " + hour + " : " + minutes + " : " + seconds + " : " + milliseconds;
        message = str + " --> " + message + " --> " + this.getClass().getSimpleName();
        array_adapter.add(message);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() --> " + this.getClass().getSimpleName());
        addString("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() --> " + this.getClass().getSimpleName());
        addString("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() --> " + this.getClass().getSimpleName());
        addString("onPause()");
        ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() --> " + this.getClass().getSimpleName());
        addString("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() --> " + this.getClass().getSimpleName());
        addString("onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed() --> " + this.getClass().getSimpleName());
        addString("onDestroy()");
    }


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//
//            addString("onConfigurationChanged()");
//            Log.d(TAG, "onConfigurationChanged()");
//
//            Toast.makeText(this, "From portrait to Landscape", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "From landscape to portrait", Toast.LENGTH_SHORT).show();
//
//        }
//    }


}
