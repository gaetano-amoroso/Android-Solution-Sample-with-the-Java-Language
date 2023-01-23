package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuggerimentoActivity extends AppCompatActivity {
    static final String TAG =  "SuggerimentoActivity";
    TextView tv_question_reproposed, tv_hint_showed;
    Button btn_show, btn_go_back;
    boolean hint_shoved;
    Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Binding
        setContentView(R.layout.activity_suggerimento);
        tv_question_reproposed  = findViewById(R.id.tv_question_reproposed);
        tv_hint_showed = findViewById(R.id.tv_hint_showed);
        btn_go_back = findViewById(R.id.btn_go_back);
        btn_show = findViewById(R.id.btn_show);
        hint_shoved = false;

        // Retrive Intent and Extra
        Intent intent =  getIntent();
        Bundle extra = intent.getExtras();
        String reproposed_question = (String)extra.get("REPROPOSED_QUESTION");
        Object response = extra.get("VALID_RESPONSE");


        // Setting UI
        tv_question_reproposed.setText(reproposed_question);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_hint_showed.setText(response.toString());
                hint_shoved = true;
                // Aggiorno l'intent e gli extra se torno indietro col
                // tasto back dopo aver avuto il suggerimento
                update();
            }
        });

        // In caso che l'utente prema il tasto fisico di back invece del button btn_go_back
        // creo l'Intent, setto gli extra e invoco getResult senz chiedere suggerimenti
        update();




        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("is_hin_showed",hint_shoved);
                setResult(RESULT_OK, i);
               onBackPressed();
            }
        });

    }//FINE ON CREATE

    private void update(){
        i.putExtra("is_hin_showed",hint_shoved);
        setResult(RESULT_OK, i);

    }

}