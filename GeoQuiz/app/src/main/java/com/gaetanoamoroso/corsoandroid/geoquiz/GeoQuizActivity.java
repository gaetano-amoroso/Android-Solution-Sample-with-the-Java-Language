package com.gaetanoamoroso.corsoandroid.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuizActivity extends AppCompatActivity {
    private static final String TAG = "GeoQuizActivity";
    private Button  true_button;
    private Button  false_button;
    private Button  m_button_next;
    private Button  m_button_prev;
    private TextView m_textView_question;
    private Question[] questions = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),

    };

    private int current_index = 0;

    private void display_question(){
        // riferimento alla view Text_view_question
        m_textView_question = (TextView) findViewById(R.id.question_text_view);
        // Recupero intero che rappresenta la risorsa stringa question nell'array  questions
        int question_id = questions[current_index].getResource_id();
        // Utilizzo  l'intero che rappresenta la risorsa appena ottenuto per valorizzare la view
        // test_question_view
        m_textView_question.setText(question_id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        // Inflate main screen interface
        setContentView(R.layout.activity_geoquiz);
        false_button  = (Button)findViewById(R.id.false_button);
        true_button  = (Button) findViewById(R.id.true_button);
        m_button_next = findViewById(R.id.button_next);
        m_button_prev = findViewById(R.id.button_prev);


        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if  (!questions[current_index].isAnswer_true()) {
                    Toast.makeText(GeoQuizActivity.this,
                            R.string.correct_answer,
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(GeoQuizActivity.this,
                            R.string.incorrect_answer,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (questions[current_index].isAnswer_true()) {
                    Toast.makeText(GeoQuizActivity.this,
                            R.string.correct_answer,
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(GeoQuizActivity.this,
                            R.string.incorrect_answer,
                            Toast.LENGTH_LONG).show();
                }
                display_question();


            }
        });

        display_question();

        m_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(current_index);
                if( current_index < questions.length-1)
                    current_index++;
                else{
                    current_index = 0;
                }
                display_question();

            }
        });


        m_button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(current_index);
                if (current_index > 0){
                    current_index--;
                }else{
                    current_index = questions.length - 1;
                }
                display_question();


            }
        });
    }

   // This code below serves to figure out how the lifecycle of activity works.
   // Can we note that, if we launch the app then a state chancing happens.
   // The transition goes from «not exist» to «on create» and during such as transition,
   // also two methods are called. Respectively, the «onResume()» and «onStart()» methods.

    // Una volta che l
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}