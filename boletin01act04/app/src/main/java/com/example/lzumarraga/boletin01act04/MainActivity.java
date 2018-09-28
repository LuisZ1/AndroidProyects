package com.example.lzumarraga.boletin01act04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] imagenes = {R.drawable.patata1,R.drawable.patata2,R.drawable.patata3};
    private int sizeImagenes = 3;
    private int posActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView011 = findViewById(R.id.imageView01);
        imageView011.setImageResource(imagenes[posActual]);
    }

    /**
     * El método mostrará otra imagen, simulando un desplazamiento a derecha
     * @param v recibe una view
     * */
    public void moveDcha(View v){

        ImageView imageView01 = findViewById(R.id.imageView01);

        posActual = posActual + 1;

        if(posActual>=sizeImagenes) {
            posActual = 0;
        }

        imageView01.setImageResource(imagenes[posActual]);
    }

    /**
     * El método mostrará otra imagen, simulando un desplazamiento a izq
     * @param v recibe una view
     * */
    public void moveIzq(View v){

        ImageView imageView01 = findViewById(R.id.imageView01);

        posActual = posActual - 1;

        if(posActual < 0) {
            posActual = sizeImagenes-1;
        }

        imageView01.setImageResource(imagenes[posActual]);
    }

}
