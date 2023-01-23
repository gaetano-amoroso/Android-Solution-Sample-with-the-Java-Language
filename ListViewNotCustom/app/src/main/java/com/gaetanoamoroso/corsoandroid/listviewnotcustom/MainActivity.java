package com.gaetanoamoroso.corsoandroid.listviewnotcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] array =  {" Tania Pearson",
       " Jacquelyn Galloway",
        "Clara Peck",
        "Brogan Marquez",
        "Salma Newman",
       " Shayla Humphrey",
        "Clinton Hancock",
        "Jessie Mcclure",
       " Yael Fuentes",
        "Reuben Mcgee",
        "Jazmin Levine",
       " Allyson Choi",
        "Toby Rasmussen",
        "Colt Hobbs",
        "Alanna Harris",
        "Liam Ho",
        "Natalee Manning",
        "Maria Wagner",
        "Dereon Blevins",
        "Alice Lambert",
        "Jayvon Mckenzie",
        "Lee Schneider",
        "Myles Dougherty",
        "Henry Humphrey",
        "Timothy Jacobs",
        "Zackery Kirby",
        "Amari Jefferson",
        "Bria Scott",
        "Enrique Frey",
        "Immanuel Chase"
      };

    ArrayAdapter<String> array_adapter;
    ListView lvListNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListNames = (ListView)findViewById(R.id.lvListNames);
        array_adapter =  new ArrayAdapter<>(getApplicationContext(), R.layout.list_names, R.id.lvListNamesItem, array);
        lvListNames.setAdapter(array_adapter);


        lvListNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String)lvListNames.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Position: ".concat(""+position+" - "+ name), Toast.LENGTH_SHORT).show();
            }
        });
    }




}
