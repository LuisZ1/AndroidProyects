package com.example.lzumarraga.boletin01act05;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int maxTextSize = 50;
    private final int minTextSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void bucle (View v) throws InterruptedException {
        TextView t1 = findViewById(R.id.text1);
        for(int i = 0; i < 99999; i++){

            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() + 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 4);
            SystemClock.sleep(7000);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX,t1.getTextSize() - 6);
            SystemClock.sleep(7000);

        }
    }

}
