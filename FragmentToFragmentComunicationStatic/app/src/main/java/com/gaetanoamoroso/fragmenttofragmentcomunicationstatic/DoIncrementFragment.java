package com.gaetanoamoroso.fragmenttofragmentcomunicationstatic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DoIncrementFragment extends Fragment {
    int counter = 0;
    public static DoIncrementFragment newInstance(){
        return new DoIncrementFragment();
    }

    OnCouterUpdateListner onCouterUpdateListner;

    public interface OnCouterUpdateListner {

        public void onCounterUpdate(int counter);
    }


    public DoIncrementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Verifico che la activity che contiene questo Fragment abbia implementato il metodo della interfaccia OnCouterUpdateListner.
        try {
            onCouterUpdateListner = (OnCouterUpdateListner)context;
        } catch (ClassCastException e) {
            Log.d("DoIncrementFragment", "Must be implement 'int onCounterUpdate(int count)' method");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_do_increment, container, false);
        Button btn_add1 = view.findViewById(R.id.btn_add_1);
        Button btn_sub1 = view.findViewById(R.id.btn_sub_1);

        btn_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(onCouterUpdateListner != null) {
                    onCouterUpdateListner.onCounterUpdate(counter);
                }
            }
        });

        btn_sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if(onCouterUpdateListner != null) {
                    onCouterUpdateListner.onCounterUpdate(counter);
                }

            }
        });
        return view;
    }
}