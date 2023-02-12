package com.gaetanoamoroso.indovinalaparola;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndovinaParolaFragment extends Fragment {
    private OnShowRandomWordHintListener m_onShowRandomWordHintListener;

    private LinearLayout rootLayout;

    public IndovinaParolaFragment() {
        // Required empty public constructor
    }

    public interface OnShowRandomWordHintListener{
        public void onShowRandomHint(String key);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            m_onShowRandomWordHintListener = (OnShowRandomWordHintListener) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indovina_parola,container, false);

        // Get reference to the LinearLayout
        rootLayout = (LinearLayout) view.findViewById(R.id.ll_root);

        String randomWord = new DataSet().randomWord();
        if(m_onShowRandomWordHintListener!= null)
            m_onShowRandomWordHintListener.onShowRandomHint(randomWord);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(72,72);
        params.setMargins(2,2,2,2);

        for (int i = 0; i < randomWord.length(); i++) {
            TextView textView = new CustomTextView(getActivity());
            textView.setText(String.valueOf(randomWord.charAt(i)).toUpperCase());
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            textView.setTextColor(Color.LTGRAY);
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setTextSize(20);
            textView.setTypeface(null, Typeface.BOLD);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.BLACK);
                }
            });

           rootLayout.addView(textView);
        }
        return rootLayout;
    }

}