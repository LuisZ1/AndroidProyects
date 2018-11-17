package com.example.lzumarraga.ejerciciosvariostrim1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private TextView selection;

    private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        selection=(TextView)findViewById(R.id.selection);

        Spinner spin=(Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                items);

        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    public void onItemSelected(AdapterView<?> parent,
                               View v, int position, long id) {
        selection.setText(items[position]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}
