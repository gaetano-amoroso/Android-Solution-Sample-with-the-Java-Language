package com.gaetanoamoroso.corsoandroid.listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Contatto> {
    // Oggetto che fa l'inflate dei layout
    LayoutInflater m_inflater;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Contatto> objects) {
        super(context, resource, objects);
        // Creo un LayoutInflater capace di fare l'inflate di layout basati su context
        m_inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            // Faccio l'inflate del Layout che rappresenta il singolo Item della lista.
            // Tante volte quanti sono gli oggetti Contatto della lista Contatti
            view = m_inflater.inflate(R.layout.contacts_list_item, null);
        }

        // recupero l'indice di ogni oggetto Contatto nella List<Contatto>
        Contatto contatto = getItem(position);

        // Dichiaro gli oggetti di cui l'Item è composto recuperando le risorse dal l'oggetto
        // view che rappresenta il layout per Item
        TextView tvPhoneNumber;
        TextView tvName;
        ImageView ivPhoto;

        // Dunque recupero tali risorse XML
        tvName = view.findViewById(R.id.tvName);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        ivPhoto = view.findViewById(R.id.ivPhoto);

        // Valorizzo tali risorse. E associo un Tag per portarmi
        // dietro la posizione dell'item nella lista
        tvName.setText(contatto.getName());
        tvName.setTag(position);
        tvPhoneNumber.setText(contatto.getPhone_number());
        tvPhoneNumber.setTag(position);
        ivPhoto.setImageDrawable(contatto.getDrawable());
        ivPhoto.setTag(position);



        // Restituisco la listview cosi costruita.
        return view;

    }
}
