package com.gaetanoamoroso.dinamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btm_news, btn_sport, btn_science;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btm_news = findViewById(R.id.btn_news);
        btn_sport = findViewById(R.id.btn_sport);
        btn_science = findViewById(R.id.btn_science);


        if(savedInstanceState == null){
            // Eseguiamo questo codice quando si avvia la activity main per la prima volta.
            // Inoltre il Bundle savedInstanceState è valorizzato da Android durante il cambio di
            // configurazione per ciò che concerne i fragment, di fatto ripristinando il fragment
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.fr_container, Fragment1.class, null);
            ft.addToBackStack("fragment1");
            ft.commit();
        }

        btm_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fr_container, Fragment1.class, null);
                ft.addToBackStack("fragment1");
                ft.commit();

            }
        });

        btn_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fr_container, Fragment2.class, null);
                ft.addToBackStack("fragment2");
                ft.commit();

            }
        });


        btn_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fr_container, Fragment3.class, null);
                ft.addToBackStack("fragment3");
                ft.commit();

            }
        });


    }
}