package com.example.lzumarraga.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.listilla);

        Ciudad sevilla = new Ciudad(0,"Sevilla",690560,"La más grande");
        Ciudad madrid = new Ciudad(1,"Madrid",3125000,"La más pestosa");
        Ciudad barcelona = new Ciudad(2,"Barcelona",1624000,"La más indepe");

        String[] ciudades={"Roma","París","Londres"};

        Ciudad[] ciudades2= {sevilla,madrid,barcelona};

        //ArrayAdapter a = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,R.id.selection, ciudades);
        ArrayAdapter a = new ArrayAdapter<Ciudad>(this,R.layout.simple_list_item_1,R.id.selection, ciudades2);
        lv.setAdapter(a);


    }
}
