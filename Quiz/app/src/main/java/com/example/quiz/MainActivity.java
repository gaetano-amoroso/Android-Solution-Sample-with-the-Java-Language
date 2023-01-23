package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String TAG =  "MAINACTIVITY";
    Button btn_back, btn_true, btn_false, btn_next, btn_hint;
    TextView tv_question, tv_counter_valid, tv_counter_hint, tv_counter_invalid;
    ListView lv_questions_list;
    int cursor = 0, valid_counter = 0, invalid_counter = 0, hinted_counter = 0;
    List<Question> questions = new ArrayList<Question>();
    List<String> choices = new ArrayList<String>();

    ArrayAdapter<String> m_arrayAdapter;


    View btn_save_or_true_false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creo due question: una DirectQuestion e una QuestionToMultipleChoice
        String[] choices1 = {"Tropopausa", "Troposfera", "Termosfera", "Stratosfera"};
        choices.addAll(Arrays.asList(choices1));
        QuestionToMultipleResponse q1 = new QuestionToMultipleResponse(
                "Quale degli strati della calotta atmosferica terrestre è più vicina alla terra?",
                1, choices);

        DirectQuestion q2 = new DirectQuestion("La Dora Baltea nasce dalle Alpi", true);
        DirectQuestion q3 = new DirectQuestion("Il bacino artificiale del lago di Santa Gioustina si trova in Piemonte", false);
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);


        // Ottengo i riferimenti ai Textview, Button, ListView --> Binding
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_container);
        tv_question = findViewById(R.id.tv_question);
        lv_questions_list = (ListView) findViewById(R.id.lv_questions_list);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_hint = (Button)findViewById(R.id.btn_hint);
        tv_counter_valid = findViewById(R.id.tv_counter_valid);
        tv_counter_invalid = findViewById(R.id.tv_counter_invalid);
        tv_counter_hint = findViewById(R.id.tv_counter_hint);




        // Setto il valore iniziale del textview question
        tv_question.setText(questions.get(cursor).getQuestion());

        // Inizializzo L'adapter
        m_arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_single_choice,
                new ArrayList<String>());
        lv_questions_list.setAdapter(m_arrayAdapter);

        Question firstQuestion = (Question) questions.get(cursor);
        if(firstQuestion instanceof QuestionToMultipleResponse){
            choices = ((QuestionToMultipleResponse) firstQuestion).getChoices();
            for(String choice: choices){
                m_arrayAdapter.add(choice);
                m_arrayAdapter.notifyDataSetChanged();
            }
            btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_questiontomultipleresponse, null);
            ll.addView(btn_save_or_true_false,1);
        }else{
            btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_directquestion, null);
            ll.addView(btn_save_or_true_false,1);
        }

        // Clicco il bottone per andare alla domanda precedente se esiste
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor > 0){
                    cursor--;
                    Question q = questions.get(cursor);
                    m_arrayAdapter.clear();
                    m_arrayAdapter.notifyDataSetChanged();
                    if(q instanceof QuestionToMultipleResponse) {
                        choices = ((QuestionToMultipleResponse) q).getChoices();
                        for (String choice : choices) {
                            m_arrayAdapter.add(choice);
                            m_arrayAdapter.notifyDataSetChanged();
                        }
                        ll.removeView(btn_save_or_true_false);
                        btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_questiontomultipleresponse, null);
                    }else{
                        ll.removeView(btn_save_or_true_false);
                        btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_directquestion, null);
                    }
                    ll.addView(btn_save_or_true_false,1);
                    tv_question.setText(q.getQuestion());

                }
            }
        });
        // Clicco il bottone per andare alla domanda successiva se esiste

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor < questions.size()-1){
                    cursor++;
                    Question q = questions.get(cursor);
                    m_arrayAdapter.clear();
                    m_arrayAdapter.notifyDataSetChanged();
                    if(q instanceof QuestionToMultipleResponse){

                        choices = ((QuestionToMultipleResponse) q).getChoices();
                        for(String choice: choices){
                            m_arrayAdapter.add(choice);
                            m_arrayAdapter.notifyDataSetChanged();
                        }
                        ll.removeView(btn_save_or_true_false);
                        btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_questiontomultipleresponse, null);

                    }else{
                        ll.removeView(btn_save_or_true_false);
                        btn_save_or_true_false = getLayoutInflater().inflate(R.layout.bar_for_directquestion, null);
                    }
                    ll.addView(btn_save_or_true_false,1);
                    tv_question.setText(q.getQuestion());
                }
            }
        });
        // Button che lancia la activity con la quale l'utente interagisce per visualizzare l'aiuto
        // eventualmente mostrato.


        btn_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuggerimentoActivity.class);
                Question q = questions.get(cursor);
                if(q instanceof QuestionToMultipleResponse){
                    intent.putExtra("VALID_RESPONSE", ((QuestionToMultipleResponse) q).getChoices().get(((QuestionToMultipleResponse) q).getResponse()));
                }else{
                    intent.putExtra("VALID_RESPONSE", ((DirectQuestion) q).isResponse());
                }
                intent.putExtra("REPROPOSED_QUESTION", questions.get(cursor).getQuestion());
                startActivityForResult(intent, 100);
            }
        });

    }


    public void true_clicked(View view){
        DirectQuestion current = ((DirectQuestion) questions.get(cursor));
        boolean response =  current.isResponse();
        boolean is_answered = current.isQuestion_answered();
        if(!is_answered) {
            if (current.isResponse()) {
                if (!current.isHint_showed())
                    tv_counter_valid.setText(getResources().getString(R.string.valid_answer) + (++valid_counter));
                else
                    tv_counter_hint.setText(getResources().getString(R.string.hinted_answer) + (++hinted_counter));
            } else {
                tv_counter_invalid.setText(getResources().getString(R.string.invalid_answer) + (++invalid_counter));
            }
            current.setQuestion_answered(true);
        }

    }
    public void false_clicked(View view){
        DirectQuestion current = ((DirectQuestion) questions.get(cursor));
        boolean response =  current.isResponse();
        boolean is_answered = current.isQuestion_answered();
        if(!is_answered) {
            // Risposta valida
            if (!current.isResponse()) {
                // se il suggerimento non è stato mostrato e la risposta è valida
                if (!current.isHint_showed()) {
                    tv_counter_valid.setText(getResources().getString(R.string.valid_answer) + (++valid_counter));
                }// Se la risposta è valida ma è stato mostrato il suggerimento
                else
                    tv_counter_hint.setText(getResources().getString(R.string.hinted_answer) + (++hinted_counter));
                // La risposta non è valida indipendentemente dal fatto che il suggerimento sia stato mostrato o meno
            } else {
                tv_counter_invalid.setText(getResources().getString(R.string.invalid_answer) + (++invalid_counter));
            }
            current.setQuestion_answered(true);
        }

    }

    // Funzione che viene chiamata quando la activity che abbiamo lanciato esce.
    // Se la activity uscente invoca setResult(int resultcode, Intent i) della classe Activity,
    // quindi ha creato un intenta
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if((requestCode != 100 || resultCode != Activity.RESULT_OK || intent == null))return;
        boolean hint_showed = intent.getBooleanExtra("is_hin_showed", false);
        if(hint_showed){
            questions.get(cursor).setHint_showed(true);
            Toast.makeText(MainActivity.this, "Suggerimento Mostrato!", Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(MainActivity.this, "Suggerimento Non Mostrato!", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onActivityResult");
    }


}
