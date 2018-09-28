package com.example.lzumarraga.boletin01act04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] imagenes = {R.drawable.patata1,R.drawable.patata2,R.drawable.patata3};
    private int sizeImagenes = imagenes.length;
    private int posActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * El método mostrará otra imagen, simulando un desplazamiento a derecha
     * @param v recibe una view
     * */
    public void moveDcha(View v){

        ImageView imageView01 = findViewById(R.id.imageView01);

        if(posActual <= sizeImagenes){
            posActual = 0;
        }else{
            posActual++;
        }

        imageView01.setImageResource(imagenes[posActual]);

    }

    /**
     * El método mostrará otra imagen, simulando un desplazamiento a izq
     * @param v recibe una view
     * */
    public void moveIzq(View v){

        ImageView imageView01 = findViewById(R.id.imageView01);

        if(posActual == 0){
            posActual = sizeImagenes;
        }else{
            posActual--;
        }

        imageView01.setImageResource(imagenes[posActual]);

    }

}
