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

        String[] ciudades={"Roma","París","Londres"};

        ArrayAdapter a = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,R.id.selection, ciudades);
        lv.setAdapter(a);


    }
}
