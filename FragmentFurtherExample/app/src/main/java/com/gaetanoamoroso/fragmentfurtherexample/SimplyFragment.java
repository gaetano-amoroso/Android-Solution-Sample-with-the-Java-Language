package com.gaetanoamoroso.fragmentfurtherexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class SimplyFragment extends Fragment {
    private static final int YES = 0;
    private static final int NO = 1;


    public SimplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root_view = inflater.inflate(R.layout.fragment_simply, container, false);
        RadioGroup  radio_group_feedback = root_view.findViewById(R.id.rg_feedback);
        RatingBar rb_song = root_view.findViewById(R.id.rb_song);

        radio_group_feedback.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Ottengo un riferimento al radiobutton selezionato
                RadioButton radio_button = (RadioButton) radio_group_feedback.findViewById(checkedId);
                // ottengo l'indice del radio button selezionato
                int index = radio_group_feedback.indexOfChild(radio_button);
                // ottengo un riferimento alla textview nel fragment
                TextView tv_fragment_header = root_view.findViewById(R.id.tv_fragment_header);

                switch (index){

                    case YES:// L'utente ha selezionato il radio button Yes
                        tv_fragment_header.setText(R.string.yes_message);
                        break;
                    case  NO:// L'utente ha selezionato il radio button No
                        tv_fragment_header.setText(R.string.no_message);
                        break;
                }
            }
        });
        rb_song.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Toast.makeText(getContext(),
                           "rating is: "+ String.valueOf(rating).charAt(0),
                            Toast.LENGTH_SHORT).show();
            }
        });



        return root_view;
    }
}