package com.example.lzumarraga.myapplication8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t1 = findViewById(R.id.textito);
                t1.setText("¿Ka pachao?");
            }
        });
    }

    public void addOperation(View v){ //Siempre reciben un view

        EditText mEdit1   = findViewById(R.id.sum1);
        EditText mEdit2   = findViewById(R.id.sum2);

        int sumar1 = Integer.parseInt(mEdit1.getText().toString());
        int sumar2 = Integer.parseInt(mEdit2.getText().toString());
        int resultado = sumar1 + sumar2 ;

        TextView t1 = findViewById(R.id.resultadoEditable);
        t1.setText(String.valueOf(resultado));

    }


}
