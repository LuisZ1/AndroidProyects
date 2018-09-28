package com.example.lzumarraga.boletin01act02;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toBlue();

        Button BtntoRed = (Button) findViewById(R.id.btnRed);
        BtntoRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toRed(); }
        });

    }

    public void toBlue(){
        Button BtntoBlue = (Button) findViewById(R.id.btnBlue);
        BtntoBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit1 = findViewById(R.id.editText2);
                TextView tV01 = findViewById(R.id.textView01);
                tV01.setText(mEdit1.getText().toString());
                mEdit1.setTextColor(Color.BLUE);
                tV01.setTextColor(Color.BLUE);
            }
        });

    }

    public void toRed(){
        EditText mEdit1 = findViewById(R.id.editText2);
        TextView tV01 = findViewById(R.id.textView01);
        tV01.setText(mEdit1.getText().toString());
        mEdit1.setTextColor(Color.RED);
        tV01.setTextColor(Color.RED);
    }


    public void toGreen(View v){

        EditText mEdit1 = findViewById(R.id.editText2);
        TextView tV01 = findViewById(R.id.textView01);

        tV01.setText(mEdit1.getText().toString());
        mEdit1.setTextColor(Color.GREEN);
        tV01.setTextColor(Color.GREEN);
    }

    public void moveRight(View v){
        TextView tV01 = findViewById(R.id.textView01);
        tV01.setGravity(Gravity.RIGHT);
    }

    public void moveLeft(View v){
        TextView tV01 = findViewById(R.id.textView01);
        tV01.setGravity(Gravity.LEFT);
    }

}
