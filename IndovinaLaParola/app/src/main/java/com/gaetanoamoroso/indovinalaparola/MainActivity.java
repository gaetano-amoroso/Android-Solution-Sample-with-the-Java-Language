package com.gaetanoamoroso.indovinalaparola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements IndovinaParolaFragment.OnShowRandomWordHintListener {
    private TextView tv_try_counter;
    private LinearLayout linearLayout;
    private IndovinaParolaFragment wordFragment;
    private EditText et_lettera;
    private Button btn_show_hint, btn_new_game;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private static int CHECK_WIN = 0;

    private static int GAME_COUNT = 0;
    private static ArrayList<String> CHOICES = new ArrayList<>();
    private String random_word = null;

    CheckWinTask  checkWinTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        tv_try_counter = (TextView) findViewById(R.id.tv_try_counter);
        et_lettera = (EditText)findViewById(R.id.et_lettera);
        btn_show_hint = (Button)findViewById(R.id.btn_show_hint);
        btn_new_game = (Button)findViewById(R.id.btn_new_game);


//         Attacco il frammento IndovinaParola Alla Activity che lo contiene
        wordFragment = new IndovinaParolaFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_container_for_char, wordFragment).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Recupero la view restituita da onCreatView nella classe IndovinaParolaFragment
        // Se facciamo questa operazione in onCreate() di MainActivity invece che in onResume() la
        // view risulterà null perchè oncreateView() non avrà ancora restituito.
        View view = wordFragment.requireView();
        // Questo linearlayout è globale perche usato in una callback
        linearLayout = (LinearLayout) view;



        // Instanzio una Array i cui oggetti sono filtri per le view che prendono un input
        // In questo caso un filtro è di  lunghezza 2
        InputFilter[] filters = new InputFilter[2];
        // Creo i due filtri
        filters[0] = new InputFilter.AllCaps();
        filters[1] = new InputFilter.LengthFilter(1);
        // Applico il filtro
        et_lettera.setFilters(filters);
        et_lettera.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // EditText ha il focus, impostare il testo qui
                    et_lettera.setText("");
                }
            }
        });

        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIndovinaParolaFragment();

            }
        });

          checkWinTask = (CheckWinTask) new CheckWinTask().execute(random_word);

    }





    public void onHintRequested(View view){
        TextView tv_hint = findViewById(R.id.tv_hint);
        tv_hint.setVisibility(View.VISIBLE);
        int current_temptetion = Integer.parseInt(tv_try_counter.getText().toString());
        current_temptetion  += 5;
        tv_try_counter.setText(""+current_temptetion );
        btn_show_hint.setVisibility(View.INVISIBLE);
    }

    public void onVerificaScelta(View view){
        String lettera = et_lettera.getText().toString();
        int current_temptation = Integer.parseInt(tv_try_counter.getText().toString());
        current_temptation++;
        if(!lettera.equals("")){
            boolean flag = isChoiceAlreadyMade(lettera);
            // Scandisco tutti i textview che costituiscono la parola da indovinare, carattere per carattere
            // e ogni qualvolta la lettera scelta coincide con quella in un textview la faccio comparire
            for(int i = 0; i< linearLayout.getChildCount(); i++){
                TextView textView = (TextView) linearLayout.getChildAt(i);
                if(textView.getText().toString().equals(lettera)){
                    textView.performClick();
                    ++CHECK_WIN;
                    if(!flag) CHOICES.add(lettera);
                }
                // Aggiorno il textview che visualizza il numero dei tentativi
                tv_try_counter = (TextView) findViewById(R.id.tv_try_counter);
                tv_try_counter.setText("" + current_temptation);

            }
        }
        et_lettera.clearFocus();
    }

    private boolean isChoiceAlreadyMade(String lettera){
        if(CHOICES.contains(lettera)){
            Toast.makeText(this, "Tentativo Sprecato!/n Lettera già individuata", Toast.LENGTH_LONG).show();
            return  true;
        }
        return false;

    }

    @Override
    public void onShowRandomHint(String key) {
        String hint = new DataSet().getWordList().get(key);
        TextView tv_hint = findViewById(R.id.tv_hint);
        tv_hint.setText(hint);
        random_word = key;

    }

    private void updateIndovinaParolaFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        checkWinTask.cancel(true);
        startActivity(intent);
    }

    class CheckWinTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            while(true){
                if(CHECK_WIN == strings[0].length())
                    break;
                try {
                    Thread.sleep(1000);
                    Log.d("Debug", "doInBackground:"+ strings[0].length());
                    Log.d("Debug", "main: "+ CHECK_WIN);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }
            }
            return true;
        }

//        @Override
//        protected void onProgressUpdate(Boolean... values) {
//            super.onProgressUpdate(values[0]);
//        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(getApplicationContext(), "Hai terminato il gioco con successo\n Inizia una nuova partita.", Toast.LENGTH_SHORT).show();
                btn_show_hint.setOnClickListener(null);
                cancel(true);
            }
        }
    }

}