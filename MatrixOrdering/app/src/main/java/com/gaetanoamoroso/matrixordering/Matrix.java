package com.gaetanoamoroso.matrixordering;

import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private static final int MAX_ORDER = 3;
    private static final int ROW_DOWN_LIMIT = MAX_ORDER -1, COLUMN_RIGHT_LIMIT = MAX_ORDER -1;
    private static final int ROW_UP_LIMIT = 0, COLUMN_LEFT_LIMIT = 0;
    private static final int MIN_VALUE = 0, MAX_VALUE = MAX_ORDER*MAX_ORDER ;
    static Integer order;
    private static Node[][] matrix;
    private static Matrix unique_instance = null;

    private  Matrix() {
    }

    public static Matrix getInstance(@Nullable Integer order) {
        if(Matrix.order == null ){Matrix.order = 3;}else{Matrix.order = order; }
            if (unique_instance == null) {
                unique_instance = new Matrix();
                matrix =  new Node[Matrix.order][Matrix.order];
                unique_instance.makeMatrix();
            }
        return unique_instance;
    }

    public static Integer getOrder() {
        return order;
    }

    public Node[][] getMatrix() {
        return matrix;
    }

    private void makeMatrix(){
        /*
        * Costruisce uma matrice quadrata di ordine "order"/ Di default order = 3;
        * La Matrice costruita è ordinata
        * */
        if (order == null) order = 3;
        for (int i=0,count = 0; i< order; i++){
            for(int j=0; j<order;j++){
                Node node =new Node(i,j);
                node.setValue(++count);
                matrix[i][j] = node;
            }
        }
    }


    private void initMatrix(){
        /* Inizializza una matrice ordinata in modo randomico */
        Node node = null;
        int n = 0;
        Random random = new Random();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i=0; i< MAX_ORDER; i++){
            for(int j=0; j<MAX_ORDER;j++){
                while(true){
                    n = random.nextInt(MAX_VALUE)+1;
                    if(!tmp.contains(n)){
                        node = matrix[i][j];
                        node.setValue(n);
                        tmp.add(n);
                        break;
                    }
                }
            }
        }
    }
    public void initMatrix(boolean is_ordered){
        /**
         * Inizializza una matrice ordinata
         */
        if(is_ordered){
            Node node = null;
            for (int i=0, count = 0; i< MAX_ORDER; i++){
                for(int j=0; j<MAX_ORDER;j++){
                    node = matrix[i][j];
                    node.setValue(++count);
                }
            }
        }else{
            initMatrix();
        }

    }

    class Node {
        public int getPosition_x() {
            return position_x;
        }

        public void setPosition_x(int position_x) {
            this.position_x = position_x;
        }

        public int getPosition_y() {
            return position_y;
        }

        public void setPosition_y(int position_y) {
            this.position_y = position_y;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        private int position_x;
        private int position_y;

        private int value;

        public Node(int position_x, int position_y) {
            this.position_x = position_x;
            this.position_y = position_y;
            value = Integer.MIN_VALUE;

        }
    }

}
