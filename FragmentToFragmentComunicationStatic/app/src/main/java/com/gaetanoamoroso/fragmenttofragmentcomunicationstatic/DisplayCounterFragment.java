package com.gaetanoamoroso.fragmenttofragmentcomunicationstatic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayCounterFragment extends Fragment {

    View view;
    TextView tv_counter_display;
    public DisplayCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_display_counter, container, false);
        tv_counter_display = view.findViewById(R.id.tv_counter_display);
        return view;
    }

    public void displayUpdate(int data) {

        tv_counter_display.setText(""+data);
    }
}