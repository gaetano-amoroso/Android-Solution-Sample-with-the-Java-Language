package com.gaetanoamoroso.fragmenttofragmentcomunicationdinamic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class ListTitlesFragment extends Fragment {
    private static final String[] COLORS = {"#FF000000", "#FF3F51B5", "#FF9C27B0","#FFF44336",
                                            "#FF4CAF50","#FF018786","#FF9C27B0","#FF43444E","#FF06600A",
                                            "#FF046D63","#FF5F2969","#FF92123D","#FF713C38","#FF7A6F14",
                                            "#FF6E5291","#FF4B787E"};

    CustomAdapter customAdapter;
    ListView lv_of_author_title_badge_items;
    private String[] item_info;


    String title;
    String author;
    String color;

    OnSongItemListener onSongItemListener;
    public interface OnSongItemListener{
        public void onSongItem(String title, String author, String initial, String color, int position);
    }

    public static ListTitlesFragment newInstance(){
        return new ListTitlesFragment();
    }
    public ListTitlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            onSongItemListener = (OnSongItemListener) context;

        }catch (ClassCastException exception){
            Log.d("ListTitleFragment", "Must be implemented the onSongItem method");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_list_titles, container, false);
        ListView lv_of_author_title_badge_items = view.findViewById(R.id.lv_of_author_title_badge_items);

        CustomAdapter customAdapter = new CustomAdapter(getContext(),
                R.layout.item_of_list_titles,
                new ArrayList<Item>());



        // Creiamo ArrayList di Item da passare al costruttore della classe CustomAdapter
        // dalla risorsa string-array
        item_info = getResources().getStringArray(R.array.top_title_songs);
        for(int i = 0; i < item_info.length; i++){
            // Ottengo il titolo della canzone e l'autore da una stringa che li contiene entrambi.
            String[] string_parts = item_info[i].split(":");
            title = string_parts[0];
            author = string_parts[1];
            // cambio il colore del drawable "badge.xml" scegliendo un colore randomicamente tra quelli presenti in COLORS
            color = COLORS[new Random().nextInt(COLORS.length)];
            GradientDrawable gradientDrawable = (GradientDrawable)getResources().getDrawable(R.drawable.badge);
            gradientDrawable.setColor(Color.parseColor(color));

            // Creo una istanza di Item costruita attraverso i passaggi appena sopra
            Item item = new Item(title,author, gradientDrawable);
            customAdapter.add(item);


        }
        lv_of_author_title_badge_items.setAdapter(customAdapter);
        lv_of_author_title_badge_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item)lv_of_author_title_badge_items.getItemAtPosition(position);
                // Callback per passare titolo, autore e testo della canzone prima alla activity la
                // quale si occuperà d'inoltrare al TextSongFragment questi dati.
                onSongItemListener.onSongItem(item.getTitle(), item.getAuthor(), item.getInitial(), color, position);
            }
        });


        return view;
    }



}