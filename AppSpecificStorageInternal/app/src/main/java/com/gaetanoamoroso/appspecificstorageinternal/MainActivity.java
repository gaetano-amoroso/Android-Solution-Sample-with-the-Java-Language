package com.gaetanoamoroso.appspecificstorageinternal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    EditText et_in_out_data;
    Button btn_save, btn_load;
    Context context;
    private final static String FILE_NAME = "file1";
    private  static String IS_DIRECTORY_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        //Binding
        et_in_out_data = findViewById(R.id.et_in_out_data);
        btn_load = findViewById(R.id.btn_load);
        btn_save = findViewById(R.id.btn_save);

        // Recupero o creo la directory privata per lo scenario App specific storage internal.
        // La path recuperata è system independent.
        IS_DIRECTORY_PATH = context.getFilesDir().getAbsolutePath();
        Log.d("MAIN_ACTIVITY", "--> App specific Storage internal private Directory Path:  "+ IS_DIRECTORY_PATH);

        // Scrive nel file il cui nome è "FILE_NAME" sotto la DIRECTORY "IS_DIRECTORY_PATH"
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apro uno stream in scrittura legato al file appena creato
                try ( FileOutputStream fos = context.openFileOutput(FILE_NAME, MODE_APPEND)){
                    write_content(fos);
                }
                catch (FileNotFoundException fne){fne.printStackTrace();}
                catch (IOException ioe ){ioe.printStackTrace();}
            }
        });
        // Leggo dal file il cui nome è "FILE_NAME" sotto la DIRECTORY "IS_DIRECTORY_PATH"
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apro uno stream in lettura legato al file appena creato
                try (FileInputStream fis = context.openFileInput(FILE_NAME)){
                        // Save edit text content if the field not empty
                    String content;
                    if(file_exist(FILE_NAME)){
                        content =  read_content(fis);
                        Log.d("MAIN_ACTIVITY", "--> Content read");
                        if(!content.equals(""))
                            et_in_out_data.setText(content);
                    }
                }
                catch (FileNotFoundException fne ){fne.printStackTrace();}
                catch (IOException ioe ){ioe.printStackTrace();}
            }
        });
    }// END on Create()

    private  void  write_content(FileOutputStream fos){
       try(PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)))){
           if(!et_in_out_data.getText().toString().equals("")){
               writer.print(et_in_out_data.getText().toString());
               Log.d("MAIN_ACTIVITY", "--> Content written");
               if (writer.checkError()) {Log.w("Main_Activity", "Error writing sensor event data");}
           }else{Log.d("MAIN_ACTIVITY", "--> Content of Edit Text is empty");}
       }
    }

    private  String read_content(FileInputStream fis) {
        String line;
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            while (((line = reader.readLine())!= null)) {content.append(line);}
        } catch (IOException e) {e.printStackTrace();}
        return content.toString();
    }

    private boolean file_exist(String file_name){
        for(String file_name_in_array: context.fileList()){
            if(file_name_in_array.equals(file_name)) return true;}
        return false;
    }
}