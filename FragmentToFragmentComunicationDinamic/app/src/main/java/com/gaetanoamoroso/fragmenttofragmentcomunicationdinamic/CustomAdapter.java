package com.gaetanoamoroso.fragmenttofragmentcomunicationdinamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> items) {
        super(context, resource,  items);
        // Instanzio un layout inflater basato sul context attuale
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        // Facci l'inflate della view "item_of_list_titles" tante volte quante sono gli Items
        // aggiunti al custom adapter
            if(view == null) view = layoutInflater.inflate(R.layout.item_of_list_titles, null);


        Item item = getItem(position);
        TextView tv_item_title_of_song = view.findViewById(R.id.tv_item_title_of_song);
        TextView tv_item_author_of_song = view.findViewById(R.id.tv_item_author_of_song);
        TextView tv_item_badge_of_song = view.findViewById(R.id.tv_item_badge_of_song);


        // Valorizzo tali risorse. E associo un Tag per portarmi
        // dietro la posizione dell'item nella lista
        tv_item_author_of_song.setText(item.getAuthor());
        tv_item_author_of_song.setTag(position);
        tv_item_title_of_song.setText(item.getTitle());
        tv_item_title_of_song.setTag(position);
        tv_item_badge_of_song.setText(item.getInitial());
        tv_item_badge_of_song.setBackground(item.getDrawable());
        tv_item_badge_of_song.setTag(position);

        // Restituisco la listview cosi costruita
        return view;

    }
}
