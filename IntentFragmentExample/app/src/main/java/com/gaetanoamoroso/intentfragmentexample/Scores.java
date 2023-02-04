package com.gaetanoamoroso.intentfragmentexample;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Comparator;

public class Scores {
    private static Scores singleton;

    private ArrayList<Row> rows;

    private Scores() {
        rows = new ArrayList();

    }
     public static Scores newInstance() {

        if(singleton == null){
            return  new Scores();
        }
        return singleton;
    }
     public void add(Row row){
        rows.add(row);
     }

     public ArrayList<Row>sort(ArrayList<Row> toOrder){
        // SortByScore è un Comparator
        toOrder.sort(new SortByscore());
        return toOrder;
     }

    public ArrayList<Row> getRows() {
        return rows;
    }

    class SortByscore implements Comparator<Row> {
         /**
          *
          * @param row_1 the first object to be compared.
          * @param row_2 the second object to be compared.
          * @return Confronta i suoi due argomenti per l'ordine. Restituisce un numero intero
          *         negativo, zero o un numero intero positivo rispettivamente se il primo argomento è minore,
          *         uguale o maggiore del secondo.
          */
         @Override
         public int compare(Row row_1, Row row_2) {
             return  Integer.parseInt(row_2.getScore()) - Integer.parseInt(row_1.getScore());
         }
     }

    class Row{
        private String record_men;
        private String score;
        public Row(String record_men, String score) {
            this.record_men = record_men;
            this.score = score;
        }
        public String getRecord_men() {
            return record_men;
        }
        public String getScore() {
            return score;
        }

    }
}
