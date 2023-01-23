package com.gaetanoamoroso.corsoandroid.activitylifeciclewithmultiactivitybyintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class Activity2 extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_LIFE_CICLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.d(TAG,"onCreate() --> "+ this.getClass().getSimpleName());

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() --> "+ this.getClass().getSimpleName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() --> "+ this.getClass().getSimpleName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() --> "+ this.getClass().getSimpleName());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() --> "+ this.getClass().getSimpleName());

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart() --> "+ this.getClass().getSimpleName());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroyed() --> "+ this.getClass().getSimpleName());

    }
    public void goToActivity3(View view){
        Intent intent = new Intent(getApplicationContext(), Activity3.class);
        startActivity(intent);
        Log.d(TAG,"onCreate() --> "+ this.getClass().getSimpleName());

    };
}