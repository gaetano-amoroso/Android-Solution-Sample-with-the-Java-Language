package com.gaetanoamoroso.socketrowbasicnetworking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    EditText et_address, et_port, et_header_http;
    TextView tv_response;
    Button btn_send;

    int port = 80;
    String address = "www.repubblica.it";
    String get_request = "GET /home HTTP/1.1\n" +
                         "Host: www.repubblica.it/\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding
        et_address = findViewById(R.id.et_address);
        et_port = findViewById(R.id.et_port);
        et_header_http = findViewById(R.id.et_header_http);
        tv_response = findViewById(R.id.tv_response);
        btn_send = findViewById(R.id.btn_send);



        // Alternative Setting By user Input




        //Setting pre filled
//        et_address.setText(address);
//        et_port.setText(""+ port);
//        et_header_http.setText(get_request);







        // Action onClick on send button
        btn_send.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                address = et_address.getText().toString();
                port = Integer.parseInt(et_port.getText().toString());
                get_request = et_header_http.getText().toString();
                Toast.makeText(MainActivity.this, "The get request is being sent to the server", Toast.LENGTH_SHORT).show();
                NetworkTask networkTask = new NetworkTask();
                networkTask.execute();
            }
        });


    }

    class NetworkTask extends AsyncTask<Void, Void, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("doInBackground ", " --> background thread begin");
            Socket socket = null;
            InetAddress  server_address;
            StringBuilder response= new StringBuilder();
            Log.d("doInBackground ", " --> background thread attempt to connect to the server: " + address);
            try {
                // Creo il vero indirizzo ip del server a partire dal DNS o dal quartetto di 3 cifre  decimali
                server_address = InetAddress.getByName(address);
                // Creo una presa di comunicazione e la connetto a una specifica porta e indirizzo
                socket = new Socket(server_address, port);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (socket == null){
                Log.d("doInBackground ", " --> Socket non creato");
                return null;

            }
            // Invio lo stream di dati
            try {
                Log.d("doInBackground ", " --> background thread attempt to send Get request message: "
                        + et_header_http.getText().toString()
                        + " to the server: " +address);

                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(et_header_http.getText().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while((line = in.readLine()) != null){
                    response.append(line).append("\n");
                    //if(line.length() == 0)break;;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return response.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            tv_response.setText(response);
        }
    }
    }
