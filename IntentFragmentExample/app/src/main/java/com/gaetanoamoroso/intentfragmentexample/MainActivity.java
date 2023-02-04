/**
 * Scrivere un’app che consiste di DUE Activity. La prima activity implementa un videogame; la
 * seconda activity serve per visualizzare i punteggi dei TRE migliori giocatori. Nella activity del
 * videogioco ci sono due pulsanti che servono rispettivamente a iniziare e finire una partita. La
 * partita è fittizia e prevede semplicemente un contatore che può essere incrementato e
 * decrementato con 2 pulsanti. Il valore del contatore nel momento in cui si preme il pulsante di
 * fine partita è il punteggio ottenuto nella partita. Se il punteggio ottenuto è fra i migliori 3, viene
 * chiesto d'inserire un nome e la classifica verrà opportunamente aggiornata.
 * Un ulteriore pulsante permette di lanciare la seconda activity che serve semplicemente a
 * visualizzare i TRE migliori punteggi con i rispettivi nomi. La classifica non deve essere persa
 * passando da un’activity all’altra e nemmeno se si esce dall’app.
 * Miglioramento 1: Fare in modo che durante lo svolgimento di una partita non si possa cliccare
 * il pulsante d'inizio di una nuova partita e nemmeno quello per i visualizzare i record.
 * Analogamente se non c’è una partita in corso, disabilitare il pulsante di fine partita.
 * Miglioramento 2: Curare l’aspetto grafico
 * Miglioramento 3: Implementare un gioco più interessante, ovviamente non complicato perché
 * non c’è il tempo necessario.
 */
package com.gaetanoamoroso.intentfragmentexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UpdateScoreFragment.OnScoreUpdateListener{

    private Button btn_start_game, btn_end_game, btn_plus, btn_minus;
    private TextView tv_counter;
    private FragmentContainerView fragmentContainerView;
    int row_counter = 0;
    boolean game_is_started;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTACH FRAGMENT
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_container_update_score,UpdateScoreFragment.class,null, null);
        fragmentTransaction.addToBackStack(null).commit();

        // BINDING
        btn_end_game = findViewById(R.id.btn_end_game);
        btn_start_game = findViewById(R.id.btn_start_game);
        fragmentContainerView = findViewById(R.id.fr_container_update_score);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        tv_counter = findViewById(R.id.tv_counter);
        game_is_started = false;




        btn_end_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                EditText et_update_score_point = ((EditText)fragmentContainerView.findViewById(R.id.et_update_score_point));
                et_update_score_point.setText(tv_counter.getText());
               et_update_score_point.setFocusable(false);
               et_update_score_point.setEnabled(false);
               et_update_score_point.setCursorVisible(false);
               et_update_score_point.setKeyListener(null);
               game_is_started = false;
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game_is_started)
                    tv_counter.setText(""+(Integer.parseInt(tv_counter.getText().toString()) -1));
                else
                    Toast.makeText(MainActivity.this, "First start the game", Toast.LENGTH_SHORT).show();
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game_is_started)
                    tv_counter.setText(""+(Integer.parseInt(tv_counter.getText().toString()) + 1));
                else
                    Toast.makeText(MainActivity.this, "First start the game", Toast.LENGTH_SHORT).show();

            }
        });

        btn_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_is_started = true;
                Toast.makeText(MainActivity.this, "let's the game begin", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onScoreUpdate(String recordman, String score) {
        /**
         * Crea un intento per un componente specifico. Tutti gli altri campi sono nulli, sebbene possano essere modificati successivamente con
         * chiamate esplicite. Ciò fornisce un modo conveniente per creare un intento che ha
         * lo scopo di eseguire un nome di classe hardcoded, piuttosto che fare affidamento
         * sul sistema per trovare una classe appropriata ==> INTENT ESPLICITO
         *
         */
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        intent.putExtra("recordman", recordman);
        intent.putExtra("score", score);
        intent.putExtra("ROW_COUNTER", row_counter);
        startActivityForResult(intent, 100);

        fragmentContainerView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if((requestCode != 100 || resultCode != Activity.RESULT_OK || intent == null))return;
        // Qui si gestituiscono putEctra se si chiama il metodo setResult nella activity dove si è cotturato
        // l'intent con getIntent(Result_Ok, Intent)'
        tv_counter.setText("0");
        game_is_started = false;

    }
}