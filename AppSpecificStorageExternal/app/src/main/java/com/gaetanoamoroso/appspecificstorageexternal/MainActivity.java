package com.gaetanoamoroso.appspecificstorageexternal;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MUSIC;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
   private  RadioGroup rg_storage_scenarios;
   private  EditText et_in_out_data;
   private  TextView tv_storage_location;
  private   Button btn_save, btn_load;
  private   Context application_context;
  private   int counter = 0;
    private static String FILE_NAME = "File1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding
        rg_storage_scenarios = findViewById(R.id.rg_storage_scenarios);
        et_in_out_data = findViewById(R.id.et_in_out_data);
        tv_storage_location = findViewById(R.id.tv_storage_location);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);

        application_context = getApplicationContext();



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = null;
                // Recupero il resId del radio-button selezionato
                int id = rg_storage_scenarios.getCheckedRadioButtonId();
                // primo RadioButto con indice zero --> INTERNAL STORAGE APP SPECIFIC
                if(rg_storage_scenarios.getChildAt(0).getId() == id){
                    data = writeData(FILE_NAME,  null);
                    Log.i("MAIN_ACTIVITY", "App Specific Internal Storage updated:"+ data);
                // secondo RadioButto con indice uno --> EXTERNAL STORAGE APP SPECIFIC
                } else if (rg_storage_scenarios.getChildAt(1).getId() == id) {
                    data = writeData(application_context, FILE_NAME,  null);
                    Log.i("MAIN_ACTIVITY", " App Specific External Storage updated:"+ data);
                }
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = null;
                // Recupero il resId del radio-button selezionato
                int id = rg_storage_scenarios.getCheckedRadioButtonId();
                // primo RadioButto con indice zero --> INTERNAL STORAGE APP SPECIFIC
                if(rg_storage_scenarios.getChildAt(0).getId() == id){
                    data = readData(FILE_NAME);
                    Log.i("MAIN_ACTIVITY", "App Specific Internal Storage data was read:"+ data);
                    et_in_out_data.setText(data);
                    // secondo RadioButto con indice uno --> EXTERNAL STORAGE APP SPECIFIC
                }else if (rg_storage_scenarios.getChildAt(1).getId() == id){
                    data = readData(application_context, FILE_NAME);
                    Log.i("MAIN_ACTIVITY", "It was loaded data from  App Specific External Storage:"+ data);
                    et_in_out_data.setText(data);
                }

            }
        });


    }// END onCreate() method
    private void buildReport(String file_path){
        StringBuilder sb = new StringBuilder();
        String current_text = tv_storage_location.getText().toString();
        sb.append(current_text).append(""+ (++counter) + "==> File '"+ FILE_NAME +"' has been opened ==>").append(file_path).append("\n\n");
        tv_storage_location.setText(sb.toString());
    }

    //@overloaded
    private String writeData(String file_name, @Nullable String data){
        // Apre un file privato associato al pacchetto applicativo di questo contesto per la scrittura.
        // Crea il file se non esiste già. Non sono necessari ulteriori permessi per l'applicazione
        // chiamante per leggere o scrivere il file restituito.
        try(FileOutputStream fos = application_context.openFileOutput(file_name, MODE_PRIVATE)){
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)), true);
            File file = new File(application_context.getFilesDir(), file_name);
            if(file.exists() && file.canWrite()){
                // Scrivo lo storage location per l'opzione l'app specific storage internal
                buildReport(file.getAbsolutePath());
                // quando i dati provengono dal field EditText
                if (!et_in_out_data.getText().toString().equals("") && data == null) {
                    data = et_in_out_data.getText().toString();
                    writer.write(data);
                }
                // Quando i dati sono passati direttamente sotto forma di parametro.
                else if(data != null)
                    writer.write(data);

                // Controllo se ci sono problemi con l'oggetto write della classe PrintWriter quando tenta di scrivere
                if(writer.checkError()){
                    Log.w("MAIN_ACTIVITY", "writer error is occurred");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    //@overloaded
    private String writeData(Context application_context, String file_name, @Nullable String data) {


        // Controllo che le directory che costituiscono volume fisico o emulato per l'app specific
        // storage esterno sia accessibile in scrittura.
        if (isExternalStorageWritable()) {
            // Creo un file sotto il volume/directory principale
            File appSpecificExternalFile = new File(application_context.getExternalFilesDir(null), FILE_NAME);
            //Creo il stream in uscita associato all'oggetto della classe File passato come argomento
            try (FileOutputStream fos = new FileOutputStream(appSpecificExternalFile)) {
                // utilizzo un writer non orientato al byte ovvero non raw
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)), true);
                // Verifico che il file appena creato sotto il volume principale sia stato creato e sia accessibile in scrittura.
                if(appSpecificExternalFile.exists() && appSpecificExternalFile.canWrite()) {
                    if (!et_in_out_data.getText().toString().equals("") && data == null) {
                        data = et_in_out_data.getText().toString();
                        writer.write(data);
                    }else if(data != null){
                        writer.write(data);
                    }
                    // Scrivo lo storage location per l'opzione l'app specific storage external
                    buildReport(appSpecificExternalFile.getAbsolutePath());
                    Log.i("MAIN_ACTIVITY",appSpecificExternalFile.getAbsolutePath());
                    if (writer.checkError()) {
                        Log.w("MAIN_ACTIVITY", "writer error is occurred");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

    //@overloaded
    private String readData(String file_name){
        StringBuilder sb = new StringBuilder();
        String line = "" ;
       try( FileInputStream fis = application_context.openFileInput(file_name)){
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
           while( (line = bufferedReader.readLine()) != null){
               sb.append(line);
           }
           // Restituisce il percorso assoluto della directory sul filesystem in cui sono archiviati
           // i file creati con openFileOutput.
           File dir = application_context.getFilesDir();
           // File path abstraction To String
           String path = dir.getAbsolutePath();
           // Scrivo lo storage location per l'opzione l'app specific storage internal
           buildReport(path);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
        return sb.toString();
    }

    //@overloaded
    private String readData(Context application_context, String file_name){
        StringBuilder sb = new StringBuilder();
        String line = "" ;
            // Controllo se il volume per lo storage app specific external è montato e accessibile almeno o in lettura
            if(isExternalStorageReadable()){
                // Recupero la directory che rappresenta il volume per lo storage app specific external
                File appSpecificExternalFile = application_context.getExternalFilesDir(null);
                // Ottengo un riferimento a tutte le cartelle sotto tale volume
                if(appSpecificExternalFile.listFiles().length > 0){
                    File[] files = appSpecificExternalFile.listFiles();
                    // Itero sull'array di directory appena ottenuto in cerca del file col nome FILE_NAME
                    for(File file : files){
                        if(file.getName().equals(FILE_NAME)){
                            try( FileInputStream fis = new FileInputStream(file)){
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                                while ((line = bufferedReader.readLine()) != null){
                                    sb.append(line);
                                }
                                // Scrivo lo storage location per l'opzione l'app specific storage External
                                buildReport(appSpecificExternalFile.getAbsolutePath());
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        return sb.toString();
    }
    //@overloaded
    private void writeData( SharedPreferences.Editor editor, String key, int value, boolean commit){
        editor.putInt(key,value);
        if(commit){editor.commit();}
        else {editor.apply();}
    }
    //@overloaded
    private void writeData( SharedPreferences.Editor editor, String key, String value, boolean commit){
        editor.putString(key,value);
        if(commit){editor.commit();}
        else {editor.apply();}
    }
    //@overloaded
    private void writeData( SharedPreferences.Editor editor, String key, float value, boolean commit){
        editor.putFloat(key,value);
        if(commit){editor.commit();}
        else {editor.apply();}

    }



    private boolean isExternalStorageWritable() {
        /*
        Verifica se un volume contenente external storage è disponibile per la lettura e la scrittura.
         */
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // Verifica se un volume contenente memoria esterna è disponibile almeno per la lettura
    private boolean isExternalStorageReadable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
    }



}