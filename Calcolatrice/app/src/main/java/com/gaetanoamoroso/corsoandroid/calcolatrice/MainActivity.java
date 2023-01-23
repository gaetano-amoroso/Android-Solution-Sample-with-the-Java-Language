package com.gaetanoamoroso.corsoandroid.calcolatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {
    private static final char[] OPERATOR = {'+', '-', '*', '÷','%'};
    private static final char[] UNARI = {'+', '-'};

    TextView display1, display2, memory_display;
    Button exp_symbol_btn;
    String exp_displayed;
    String cache = null;


    ExpressionConfiguration configuration;
    Expression expression;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display1 = findViewById(R.id.screen1_textview);
        display2 = findViewById(R.id.screen2_textview);
        memory_display = findViewById(R.id.memory_display);




    }

    private boolean has_occurrence_of(char symbol, String string){
        int count = 0;
        for (int i = 0; i < string.length(); i++){
            if (symbol == string.charAt(i)){
                count++;
            }
        }
        return count != 0;
    }
    private boolean has_occurrence_of(char symbol, char[] sequence){
        int count = 0;
        for (char c : sequence) {
            if (symbol == c) {
                count++;
            }
        }
        return count != 0;
    }
    private int count_occurrence(char c, String str){
        int count = 0;
        for(int i = 0; i< str.length(); i++){
            if( c == str.charAt(i))
                count++;
        }
        return count;
    }


   public void expression_symbol_pressed(View view){
        boolean valid_symbol = false;
        boolean already_update = true;
        if(view instanceof Button) {
            exp_symbol_btn = (Button) view;
            char symbol = exp_symbol_btn.getText().charAt(0);
            String exp_displayed = display1.getText().toString();
            int index_last_symbol = exp_displayed.length() -1;

            // Caso in cui il display non è vuoto
            if(!exp_displayed.equals("")){
               char last_symbol = exp_displayed.charAt(index_last_symbol);
               // Se l'ultimo carattere è una cifra numerica
               if(Character.isDigit(last_symbol)){
                   if (Character.isDigit(symbol) || has_occurrence_of(symbol, OPERATOR)
                                                 || (symbol == '.' && !has_occurrence_of(symbol, exp_displayed))){
                       valid_symbol = true;
                   }
               // Se l'ultimo carattere mostrato a video della espressione è un '.'
               }else if(last_symbol == '.'){
                   if (Character.isDigit(symbol) || has_occurrence_of(symbol, OPERATOR)){valid_symbol = true;
                   }
               // Se l'ultimo carattere mostrato a video della espressione è contenuto in {+, -, *, /, %}
               }else if(has_occurrence_of(last_symbol, OPERATOR)){
                   if (Character.isDigit(symbol) || (symbol == '.' && !has_occurrence_of(symbol, exp_displayed))){
                       valid_symbol = true;
                   }
                   if (has_occurrence_of(symbol, OPERATOR) && exp_displayed.length() > 1){
                       exp_displayed = exp_displayed.substring(0,exp_displayed.length()-1)
                                                    .concat(String.valueOf(symbol));
                       display1.setText(exp_displayed);
                       valid_symbol = true;
                       already_update = false;
                   }
                   if(has_occurrence_of(symbol, UNARI) && exp_displayed.length() == 1) {
                       exp_displayed = String.valueOf(symbol);
                       display1.setText(exp_displayed);
                       valid_symbol = true;
                       already_update = false;
                   }
                // Se l'ultimo carattere mostrato a video della espressione è una '('
               }else if(last_symbol == '('){
                   if(has_occurrence_of(symbol, UNARI) || Character.isDigit(symbol) || symbol=='('){
                       exp_displayed = exp_displayed.concat(String.valueOf(symbol));
                       display1.setText(exp_displayed);
                   }
               // Se l'ultimo carattere mostrato a video della espressione è una ')'
               }else if(last_symbol == ')'){
                   if (Character.isDigit(symbol) || symbol=='(' ){
                       exp_displayed = exp_displayed.concat("*").concat(String.valueOf(symbol));
                       display1.setText(exp_displayed);
                   }else if(has_occurrence_of(symbol, OPERATOR)){
                       exp_displayed = exp_displayed.concat(String.valueOf(symbol));
                       display1.setText(exp_displayed);
                   }
               }
            // Caso in cui il display e vuoto
            }else{
                if (Character.isDigit(symbol) || has_occurrence_of(symbol, UNARI)
                                              || (symbol == '.' && !has_occurrence_of(symbol, exp_displayed))){
                    valid_symbol = true;}
            }
            if (valid_symbol && already_update) display1.setText(exp_displayed.concat(String.valueOf(symbol)));
            HorizontalScrollView sv= (HorizontalScrollView) findViewById(R.id.SV1);
            sv.scrollTo(sv.getWidth(), sv.getHeight());
        }
   }

   private String evaluate(){
       String expression_as_string = display1.getText().toString();
       String string_result = "";
       expression_as_string = expression_as_string.replaceAll("÷", "/");
       if(!expression_as_string.equals("")) {
           if((expression_as_string.charAt(expression_as_string.length() - 1) == '.') && (expression_as_string.length() == 1)){
               expression_as_string = "0";
           }

           configuration = ExpressionConfiguration.builder().decimalPlacesRounding(6).build();
           expression = new Expression(expression_as_string, configuration);
           try {expression.validate();}
           catch (ParseException p){
               Toast.makeText(MainActivity.this, p.getLocalizedMessage(), Toast.LENGTH_LONG).show();
               display2.setText("ERROR");
           }
           try {
               EvaluationValue result = expression.evaluate();
               string_result = result.getStringValue();
               display2.setText(string_result);
           }catch (EvaluationException | ParseException ee){
               Toast.makeText(MainActivity.this, ee.getLocalizedMessage(), Toast.LENGTH_LONG).show();
               display2.setText("ERROR");
           }
       }
        return string_result;
    }


    public void expression_result(View view) {evaluate();}


    // If the pressed Button is M+ then the method get the expression  displayed on the screen,
    // valuate it and add it to the value in the memory cache.
    // If the pressed button is M-, then the method get the value displayed on the screen
    // and subtract it from the value memorized in cache if any exit, otherwise makes negative
    // the value displayed add it to the value in the memory cache. If on the screen nothing appear
    // then in such case nothing happens
    public void memory_add(View view) {
        String string_expression = display1.getText().toString();
        exp_symbol_btn = (Button)view;
        char symbol = exp_symbol_btn.getText().charAt(0);
        double d3;
            if (cache == null && !string_expression.equals("")) {
                cache = evaluate();
            } else if (cache != null && !string_expression.equals("")) {
                if (has_occurrence_of(string_expression.charAt(string_expression.length() - 1), OPERATOR)) {
                    string_expression = string_expression.concat(cache);
                    display1.setText(string_expression);
                } else {
                    double d1 = Double.parseDouble(evaluate());
                    double d2 = Double.parseDouble(cache);
                    d3 = d1 + d2;
                    cache = Double.valueOf(d3).toString();
                }
            }
            memory_display.setText(cache);

    }


     // get memory value if is registered and display it.
     //  If de last symbol of expression is an operator then concatenate  that value otherwise replace it
    public void memory_recall(View view) {
    CharSequence sequence = display1.getText();
    String string_expression = display1.getText().toString();

        int index_last_char = sequence.length() - 1;
        char last_char = sequence.charAt(index_last_char);
        if (has_occurrence_of(last_char, OPERATOR) && !sequence.toString().equals("") &&
                cache != null && !String.valueOf(cache.charAt(0)).equals("-")) {
            display1.setText(sequence.toString().concat(cache));
        } else if (cache != null && String.valueOf(cache.charAt(0)).equals("-")) {
            string_expression = string_expression.substring(0, string_expression.length() - 1);
            string_expression = string_expression.concat(String.valueOf(cache.charAt(0)));
            display1.setText(string_expression);
        } else {
            if (cache != null) display1.setText(cache);
        }
        HorizontalScrollView sv= (HorizontalScrollView) findViewById(R.id.SV1);
        sv.scrollTo(sv.getWidth(), sv.getHeight());

    }

    public void memory_cancel(View view) {
    cache = null;
    memory_display.setText("");

    }

    public void del_all(View view) {
        display1.setText("");
        display2.setText("");
        memory_display.setText("");

    }


    public void del_from_right(View view) {
        exp_displayed = display1.getText().toString();
        if(!exp_displayed.equals("")) {
            exp_displayed = exp_displayed.substring(0, exp_displayed.length() - 1);
            display1.setText(exp_displayed);
        }
    }

    public void add_parentecis(View view) {
        exp_symbol_btn = (Button) view;
        char last_char;
        String symbol = exp_symbol_btn.getText().toString();
        String string_expression = display1.getText().toString();
        if(!string_expression.equals("")) {
            last_char = string_expression.charAt(string_expression.length() - 1);
            if (symbol.equals("(") && string_expression.equals("")) {
                string_expression = string_expression.concat(symbol);
                display1.setText(string_expression);
            }
            if (symbol.equals("(") && Character.isDigit(display1.getText().charAt(display1.getText().length() - 1))) {
                string_expression = string_expression.concat("*").concat(symbol);
                display1.setText(string_expression);

            } else if (symbol.equals("(") && has_occurrence_of(last_char, OPERATOR)) {
                string_expression = string_expression.concat(symbol);
                display1.setText(string_expression);
            } else if ((symbol.equals("(") && last_char == ')')) {
                string_expression = string_expression.concat("*").concat(symbol);
                display1.setText(string_expression);
            } else if (symbol.equals(")") && last_char == '(') {
                display1.setText(string_expression);
            } else if (symbol.equals(")") && !has_occurrence_of(last_char, OPERATOR) && last_char != '.') {
                if (count_occurrence(')', string_expression) < count_occurrence('(', string_expression)) {
                    string_expression = string_expression.concat(")");
                    display1.setText(string_expression);
                }
            }
        }// Se la il display è vuoto, espressione non immessa
        else{
            if(symbol.equals("(")){
                string_expression = string_expression.concat(symbol);
                display1.setText(string_expression);
            }
        }
    }
}