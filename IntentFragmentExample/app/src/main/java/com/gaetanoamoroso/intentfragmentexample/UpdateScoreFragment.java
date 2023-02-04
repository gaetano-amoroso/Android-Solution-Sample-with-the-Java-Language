package com.gaetanoamoroso.intentfragmentexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdateScoreFragment extends Fragment {
    Button btn_update_score;
    EditText et_update_score_name;
    EditText et_update_score_point;
    OnScoreUpdateListener onScoreUpdateListener;
    int score;
    String recordman;


    public UpdateScoreFragment() {
        // Required empty public constructor
    }


    public interface OnScoreUpdateListener{
        void  onScoreUpdate(String recordman, String score);
    }

    public static UpdateScoreFragment newInstance(){
        return new UpdateScoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_score, container, false);

        // Binding
        et_update_score_point = view.findViewById(R.id.et_update_score_point);
        et_update_score_name = view.findViewById(R.id.et_update_score_name);
        btn_update_score = view.findViewById(R.id.btn_update_score);
        btn_update_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onScoreUpdateListener != null) {
                    // Setting views
                    recordman = et_update_score_name.getText().toString();
                    score = Integer.parseInt(et_update_score_point.getText().toString());
                    onScoreUpdateListener.onScoreUpdate(recordman, String.valueOf(score));
                }

            }
        });




        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        // Serve per controllarre che l'activity a cui è attaccato il frammento
        // implemnti l'interaccia OnScoreUpdate
        super.onAttach(context);
         try {
             onScoreUpdateListener = (OnScoreUpdateListener) context;

         }catch (ClassCastException exception){
             exception.printStackTrace();
         }
    }
}