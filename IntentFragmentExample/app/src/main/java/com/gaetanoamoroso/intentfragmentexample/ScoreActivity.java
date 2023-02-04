package com.gaetanoamoroso.intentfragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    Scores scores;
    TextView[] tv_record_men;
    TextView[] tv_score_point;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // 1.0 ==> Binding
        tv_record_men = new TextView[3];
        tv_record_men[0] = findViewById(R.id.tv_record_men_1);
        tv_record_men[1] = findViewById(R.id.tv_record_men_2);
        tv_record_men[2] = findViewById(R.id.tv_record_men_3);
        tv_score_point = new TextView[3];
        tv_score_point[0] = findViewById(R.id.tv_score_1);
        tv_score_point[1] = findViewById(R.id.tv_score_2);
        tv_score_point[2] = findViewById(R.id.tv_score_3);

        // 2.0 ==> Recupero L'Intent che ha lanciato questa Activity
        Intent intent = getIntent();
        // 2.1 ==> Recupero i dati associati all'Intent del punto 2.0
        String recordman = intent.getStringExtra("recordman");
        String score = intent.getStringExtra("score");
        // 2.2 ==> Chiamo set Result per poter passare eventualmente dati alla Activity che ha lanciato questa Activity
        //         recuperabili con la callback onActivityResult(int resultCode, Intent i)
        // TODO Eventuali dati da passare.
        setResult(RESULT_OK, intent);

        // 3.0 ==> Ottengo un oggetto sharedPreferences attraverso il quale posso scrivere e leggere
        //         nei files XML delle preferenze dati che sono memorizzati come coppie chiave valore.
        SharedPreferences preferences = getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 3.1 ==> Verifico che nel file di preferenze chi sia un campo counter che semplicemente
        //         tiene conto del numero di coppie di righe, (recordman score), inserite.
        if(!preferences.contains("counter"))
            editor.putInt("counter",counter);
        // 3.2 ==> Inserisco due nuovi campi le cui key hanno il suffisso costruito con counter
        counter = preferences.getInt("counter",0);
        editor.putString("recordman_"+counter, recordman);
        editor.putString("score_"+counter, score);
        // 3.3 ==> Faccio il commit delle due operazioni al punto 3.2
        editor.apply();
        // 4.0 ==> Scores è un oggetto che rappresenta una lista Row ognuna delle quali è un oggetto
        //         che costa di due campi: recordman e score.
        scores = Scores.newInstance();

        // 5.0 ==> Dal file delle preference scores.xml itero sul numero di coppie chiave valore
        //         per recuperare una alla volta tutti i recordman e il relativo score associato
        //         Con ognuna di tale coppia costruisco un oggetto della classe Score.Row e lo aggiungo
        //         alla lista di tali oggetti.
        for(int i = 0; i<(preferences.getAll().size()-1)/2; i++){
           String  r =preferences.getString("recordman_"+i, null);
           String s = preferences.getString("score_"+i, null);
           scores.add(scores.new Row(r,s));
        }
        // 6.0 ==> A questo punto abbiamo inserito un nuova coppia di campi chiave valore nel file
        //         scores.xml, abbiamo letto e memorizzati tutti i campi di tale file in una lista
        //         Quindi incrementiamo di uno il campi counter, lo aggiorno nel file
        editor.putInt("counter", ++counter);
        editor.apply();

        // 7.0 ==> Ordino gli oggetti della classe Score.Row nell'arrayList "scores.getRows()"
        ArrayList<Scores.Row> rows  =  scores.sort(scores.getRows());

        // 8.0 ==> Aggiorno la vista degli Scores, ovvero Popolo la classifica.
        for(int j= 0; j<3 && j < scores.getRows().size(); j++){
            tv_score_point[j].setText(""+rows.get(j).getScore());
            tv_record_men[j].setText(rows.get(j).getRecord_men());
        }
    }





}