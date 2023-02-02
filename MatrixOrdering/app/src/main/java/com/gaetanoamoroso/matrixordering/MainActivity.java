package com.gaetanoamoroso.matrixordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  GridLayout gl_matrix;
    private  Button btn_start_game, btn_reset_game;
    private  TextView[] core_nodes;
    private TextView tv_counter;
    private  Matrix.Node[][] nodes;
    private  Matrix matrix;

    String tag_node = null;
    Bundle saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Instanzia una oggetto matrix della classe Matrix. L'oggetto matrix è un singleton.
        matrix = Matrix.getInstance(null);
        // Matrice di Nodi per valorizzare i TextView
        nodes = matrix.getMatrix();



        // Binding
        gl_matrix = findViewById(R.id.gl_matrix);
        btn_reset_game = findViewById(R.id.btn_reset_game);
        btn_start_game = findViewById(R.id.btn_start_game);
        tv_counter = findViewById(R.id.tv_counter);
        tv_counter.setText(""+ 0);


        if(savedInstanceState != null){
            // lo utilizzo nel metodo init()
            saved = savedInstanceState;

            // Getting dei valori del Bundle
            int counter = savedInstanceState.getInt("counter");
            Matrix.order = savedInstanceState.getInt("order");
            tv_counter.setText(""+ counter);
            for(int i = 0, count = 0; i<Matrix.order;i++){
                for (int j =0; j< Matrix.order;j++) {
                    count++;
                    // Faccio il recoveri dei valori nella matrice a seguito del cambio di configurazione
                    nodes[i][j].setValue(savedInstanceState.getInt("node_" + count));

                }
            }
        }

        //Iniziaria L'array con i Textview
        init();
        // setto il campo text con i valori recuperati.
        populateMatrixTextVies();


       // Mischio i nodi quando premo il tasto start
       btn_start_game.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               matrix.initMatrix(false);
               populateMatrixTextVies();
               tv_counter.setText(""+ 0);
           }
       });

        // Ordino i nodi quando premo il tasto reset
        btn_reset_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matrix.initMatrix(true);
                populateMatrixTextVies();
                tv_counter.setText(""+ 0);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("order",Matrix.getOrder());
        outState.putInt("counter", Integer.parseInt(tv_counter.getText().toString()));
        for(int i = 0, count = 1; i<Matrix.order;i++){
            for (int j =0; j< Matrix.order;j++)
                outState.putInt("node_"+count++, nodes[i][j].getValue());
        }

    }

    private void populateMatrixTextVies(){
        /**
         * Popolo la vista che rappresento la matriceQuesta è  la vista della matrice ordinata
         * Valorizzo il camp text e il tag
         */
        for(int h=0, count=0; h<Matrix.order; h++){
            for (int k = 0; k<Matrix.order; k++, count++){
                core_nodes[count].setText(""+nodes[h][k].getValue());
                core_nodes[count].setTag(""+nodes[h][k].getPosition_x()+nodes[h][k].getPosition_y());
                core_nodes[count].setBackgroundColor(Color.parseColor("#559966"));
                Log.d("MAIN_ACTIVITY", "cell index ==> "+ nodes[h][k].getPosition_x()+nodes[h][k].getPosition_y());

            }
        }

    }

    public void onshiftClicked(View view){
        if (tag_node != null) {
            String shift_tag = (((TextView)view).getTag().toString());
            int[] temp = new int[3];
            shift(tag_node, shift_tag, temp);
            tv_counter.setText(""+(Integer.parseInt(tv_counter.getText().toString()) +1) );
        }else{
            Toast.makeText(this, "Seleziona una cella \n prima dello shift", Toast.LENGTH_LONG).show();
        }
        tag_node = null;
    }

    private void init(){

        int i = 0;// indice dei children, ovvero dei (TextView) in GridLayout che rappresentano i nodi core numerici
        int j = 0;// indice di tutti i children indifferentemente


        // Array di TextVies ognuno dei quali rappresenta visivamente un nodo core, ovvero i textview numerici
        core_nodes = new TextView[Matrix.order*Matrix.order];

        // Riempio Array filtrando  i TextView, escludo quelli che fanno lo shift
        String tag;
        while (true){
            tag = gl_matrix.getChildAt(j).getTag().toString();
            if(!(tag.contains("up")
                    || tag.contains("down")
                    || tag.contains("left")
                    || tag.contains("right"))){
                //BINDING
                core_nodes[i] = (TextView) gl_matrix.getChildAt(j);
                i++;
            }
            if(j>=gl_matrix.getChildCount()-1)break;
            j++;
        }
        // Inizializzo la matrice ordinata all'avvio del gioco

        if(saved==null) {
            // Inizializzo la matrice ordinata all'avvio del gioco
            matrix.initMatrix(true);
            // Popolo la view corrispondente
            populateMatrixTextVies();
        }
    }

    private void shift(String n_m, String direction, int[] vector){
        int row = Character.getNumericValue(n_m.charAt(0));
        int column = Character.getNumericValue(n_m.charAt(1));
        switch(direction){
            case "up":
                for(int i = 0, k=0; i<Matrix.order; i++)
                    vector[i] = nodes[i][column].getValue();
                for(int j = 0, k=0; j<Matrix.order; j++){
                    if(j==2) k=0; else k = j+1;
                    nodes[j][column].setValue(vector[k]);
                }break;
            case "down":
                for(int i = 0; i<Matrix.order; i++)
                    vector[i] = nodes[i][column].getValue();
                for(int j = 0, k=0; j<Matrix.order; j++) {
                    if (j == 0) k = 2;else k = j-1;
                    nodes[j][column].setValue(vector[k]);
                }break;
            case "left":
                for(int i = 0; i<Matrix.order; i++)
                    vector[i] = nodes[row][i].getValue();
                for(int j = 0, k=0; j<Matrix.order; j++) {
                    if (j == 2) k = 0;else k= j+1;
                    nodes[row][j].setValue(vector[k]);
                }break;
            case "right":
                for(int i = 0; i<Matrix.order; i++)
                    vector[i] = nodes[row][i].getValue();
                for(int j = 0, k=0; j<Matrix.order; j++) {
                    if (j == 0) k = 2;else k= j-1;
                    nodes[row][j].setValue(vector[k]);
                }break;
        }
        populateMatrixTextVies();
    }

    public void onNodeClicked(View view) {
        tag_node = ((TextView)view).getTag().toString();
        ((TextView)view).setBackgroundColor(Color.parseColor("#666666"));

    }
}