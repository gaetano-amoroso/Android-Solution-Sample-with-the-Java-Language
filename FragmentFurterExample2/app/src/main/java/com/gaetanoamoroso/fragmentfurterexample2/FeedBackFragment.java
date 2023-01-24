package com.gaetanoamoroso.fragmentfurterexample2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FeedBackFragment extends Fragment {
    private static final int YES = 1;
    private static final int NO = 0;

    public FeedBackFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view_root = inflater.inflate(R.layout.fragment_feed_back, container, false);
        RadioGroup radioGroup = view_root.findViewById(R.id.rg_rating);
        TextView tv_article_question = view_root.findViewById(R.id.tv_article_question);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               int index =   radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
               switch(index){
                   case YES: tv_article_question.setText("We apreciate that!");
                    break;
                   case NO:tv_article_question.setText("Thanks!");
               }

            }
        });

        return view_root;
    }

    public static FeedBackFragment newInstance(){
            return new FeedBackFragment();
    }

}