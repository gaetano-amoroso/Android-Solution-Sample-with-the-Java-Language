package com.gaetanoamoroso.corsoandroid.layoutexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater() è un metodo derivato dalla classe Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layouts_and_widget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.relative_layout:
                setContentView(R.layout.activity_relative_layout);
                return true;
            case R.id.linear_layout:
                setContentView(R.layout.activity_linear_layout);;
                return true;
            case R.id.grid_layout:_layout:
                setContentView(R.layout.activity_grid_layout);;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}