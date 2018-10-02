package com.example.lzumarraga.boletin02act01;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toBold(View v){
        TextView textV1 = findViewById(R.id.textChange);
        Switch swBold = findViewById(R.id.swBold);
        if (swBold.isChecked()) {
            textV1.setTypeface(Typeface.DEFAULT_BOLD);
        }else{
            textV1.setTypeface(Typeface.DEFAULT);
        }
    }

    public void toRed(View v){
        TextView textV1 = findViewById(R.id.textChange);
        Switch swRed = findViewById(R.id.swRed);
        if(swRed.isChecked()) {
            textV1.setTextColor(Color.RED);
        }else{
            textV1.setTextColor(Color.BLACK);
        }
    }

    public void toBig(View v){

        TextView textV1 = findViewById(R.id.textChange);

        Switch swSmall = findViewById(R.id.swSmall);
        if(swSmall.isChecked()){
            swSmall.setChecked(false);
            textV1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textV1.getTextSize() + 15);
        }

        Switch swBig = findViewById(R.id.swBig);
        if (swBig.isChecked()) {
            textV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,textV1.getTextSize() + 10);
        }

    }

    public void toSmall(View v){

        TextView textV1 = findViewById(R.id.textChange);

        Switch swBig = findViewById(R.id.swBig);
        if(swBig.isChecked()){
            swBig.setChecked(false);
            textV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,textV1.getTextSize() - 10);
        }
        Switch swSmall = findViewById(R.id.swSmall);
        if (swSmall.isChecked()) {
            textV1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textV1.getTextSize() - 15);
        }
    }

}
