package com.gaetanoamoroso.fragmenttofragmentcomunicationdinamic;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class TextSongFragment extends Fragment {

    private String[] songs_text;
    ListView lv_text_song;
    TextView tv_song_text_title;
    TextView tv_song_text_author;

    TextView tv_song_text_badge;

    public TextSongFragment() {
        // Required empty public constructor
    }

    public static TextSongFragment newInstance(){
        return new TextSongFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Testi delle canzoni prelevato dalle resource file
        songs_text =  getResources().getStringArray(R.array.text_of_song);

        Bundle bundle = getArguments();
        String[] song_text = {songs_text[bundle.getInt("position")]};

        // Inflate the layout for this fragment and bind the listview
        View view = inflater.inflate(R.layout.fragment_text_song, container, false);
        lv_text_song = view.findViewById(R.id.lv_text_song);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.item_song_text,
                R.id.tv_item_song_text,
                Arrays.asList(song_text));
        lv_text_song.setAdapter(arrayAdapter);

        //Binding Views inside de ListView and setting data
        tv_song_text_title = view.findViewById(R.id.tv_song_text_title);
        tv_song_text_title.setText(bundle.getString("title"));


        tv_song_text_author = view.findViewById(R.id.tv_song_text_author);
        tv_song_text_author.setText(bundle.getString("author"));


        tv_song_text_badge = view.findViewById(R.id.tv_song_text_badge);
        tv_song_text_badge.setText(bundle.getString("initial"));
        GradientDrawable gradientDrawable = (GradientDrawable)getResources().getDrawable(R.drawable.badge);
        gradientDrawable.setColor(Color.parseColor(bundle.getString("color")));
        tv_song_text_badge.setBackground(gradientDrawable);







        return view;
    }

}