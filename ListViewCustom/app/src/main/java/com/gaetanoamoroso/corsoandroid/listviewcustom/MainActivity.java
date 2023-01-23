package com.gaetanoamoroso.corsoandroid.listviewcustom;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ListView;


import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    ListView lvContacts;
    String[] names = {" Tania Pearson", " Jacquelyn Galloway", "Clara Peck", "Brogan Marquez",
            "Salma Newman", " Shayla Humphrey", "Clinton Hancock", "Jessie Mcclure", " Yael Fuentes",
            "Reuben Mcgee", "Jazmin Levine", " Allyson Choi", "Toby Rasmussen", "Colt Hobbs",
            "Alanna Harris", "Liam Ho", "Natalee Manning", "Maria Wagner", "Dereon Blevins",
            "Alice Lambert", "Jayvon Mckenzie", "Lee Schneider", "Myles Dougherty", "Henry Humphrey",
            "Timothy Jacobs", "Zackery Kirby", "Amari Jefferson", "Bria Scott", "Enrique Frey",
            "Immanuel Chase"
    };

    CustomAdapter custom_adapter;
    Contatto contatto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Riferimento alla ListView. Questo oggetto conterrà gli Item definiti nel layout contacts_list_item
        // Oggetto sul quale settiamo l'adapter
        lvContacts = (ListView) findViewById(R.id.lvContacts);
        // Invece di adoperare un ArrayAdapter utilizzeremo un Adapter personalizzato.
         custom_adapter = new CustomAdapter(MainActivity.this, R.layout.contacts_list_item, new ArrayList<>());
        // Setto Adapter
        lvContacts.setAdapter(custom_adapter);
        for (String name : names) {
            contatto = new Contatto(name,
                    "+39 351 8282 721",
                    getResources().getDrawable(R.drawable.ic_baseline_photo_camera_front_96));
            custom_adapter.add(contatto);
        }


        //
//        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Contatto contatto = (Contatto)lvContacts.getItemAtPosition(position);
//                String objectId = contatto.toString();
//                Toast.makeText(MainActivity.this, objectId, Toast.LENGTH_SHORT).show();
//            }
//        });



    }

    public void tcNameOnClick(View v) {

        int itemPosition = (Integer)v.getTag();
        // Contatto at position itemPosition in Array:ost<Contatto>
        contatto = (Contatto)custom_adapter.getItem(itemPosition);
        String message = contatto.getName()+" at position: " + itemPosition;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }




    public void tcPhoneNumberOnClick(View v) {
        int itemPosition = (Integer)v.getTag();
        // Contatto at position itemPosition in Array:ost<Contatto>
        contatto = (Contatto)custom_adapter.getItem(itemPosition);
        String message =" Cell number is: " + contatto.getPhone_number()+" of position: " + itemPosition;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



    public void ivPlaceHolderOnClick(View v) {
        int itemPosition = (Integer)v.getTag();
        // Contatto at position itemPosition in Array:ost<Contatto>
        contatto = (Contatto)custom_adapter.getItem(itemPosition);
        String message ="Photo: " + contatto.getDrawable().toString()+" at position: " + itemPosition;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }








}