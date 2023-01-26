package com.gaetanoamoroso.fragmenttofragmentcomunicationdinamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ListTitlesFragment.OnSongItemListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Quì setto il Fragment da visualizzare All'avvio
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_container, ListTitlesFragment.newInstance(), "ListTitleFragment")
                .addToBackStack("'ListTitleFragment'").
                commit();
    }

    @Override
    public void onSongItem(String title, String author, String initial, String color, int position) {
        /*
       Il Metodo di callback "onSongItem" serve a recuperare i dati: titolo, autore, index
       provenienti dal Fragmento che visualizza la lista degli Items. Index è un indice che
       determina il testo della canzona da associare al item selezionato.

       Una volta recuperato tali dati li inoltra Al Fragment "TextSongFragment" settando un Bundle.
         */
        replaceView(title, author,initial, color, position);
    }
    private void replaceView(String title, String author,String initial, String color, int position){
       /*  Attraverso l'oggetto fragmentManager avvio una transizione che mi consente di rimpiazzare
        Il fragment corrente, "ListTitleFragment", con il fragment TextSongFragment. Quest'ultimo
        conterrà il testo della canzone relativa all'item selezionato'
        */


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TextSongFragment textSongFragment = TextSongFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("author", author);
        bundle.putString("initial", initial);
        bundle.putString("color", color);
        bundle.putInt("position", position);
        // Imposto il bundle che successivamente posso recuperare con Bundle bundle = getArguments()
        textSongFragment.setArguments(bundle);
        //Effettuo il rimpiazzo
        fragmentTransaction.replace(R.id.fr_container, TextSongFragment.class ,bundle,null).addToBackStack(null).commit();


    }



}