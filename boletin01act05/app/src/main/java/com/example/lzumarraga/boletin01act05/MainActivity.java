package com.example.lzumarraga.boletin01act05;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int maxTextSize = 50;
    private final int minTextSize = 10;
    private int idFoto = 0;
    ImageView img1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.imageView1);
        img1.setTag(android.R.drawable.btn_star_big_off);
    }

    public void aumentar (View v){
        TextView t1 = findViewById(R.id.text1);
        if(t1.getTextSize() < maxTextSize){
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
        }

    }

    public void reducir (View v){
        TextView t1 = findViewById(R.id.text1);
        if(t1.getTextSize() > minTextSize){
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
        }
    }

    public void reset (View v){
        TextView t1 = findViewById(R.id.text1);
        t1.setTextSize(20);
    }

    public void cambiarFoto(View v){
        /*
        if(idFoto == 0){
            img1.setImageResource(android.R.drawable.btn_star_big_off);
            idFoto = 1;
        }else{
            img1.setImageResource(android.R.drawable.btn_star_big_on);
            idFoto = 0;
        }*/

        if((Integer)img1.getTag() == android.R.drawable.btn_star_big_off){
            img1.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            img1.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }
    /*
    public void bucle (View v) throws InterruptedException {
        TextView t1 = findViewById(R.id.text1);
        for(int i = 0; i < 99999; i++){

            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 6);

        }
    }*/

}
