package com.example.lzumarraga.probandoviewmodel;

public class ViewModel extends android.arch.lifecycle.ViewModel{

    public long sumador = 1;
    public long contador = 0;
    public long preciox2 = 50;
    public long preciox4 = 100;

    public long sumatron (){
        contador = contador + sumador;
        return contador;
    }

    public void sumadorx2(){
        if(contador >= preciox2){
            sumador = sumador * 2;
            contador = contador - preciox2;
            preciox2 = preciox2 * 2;
//            preciox4 = preciox4 * 2;
        }
    }

    public void sumadorx4(){
        if(contador >= preciox4) {
            sumador = sumador * 4;
            contador = contador - preciox4;
//            preciox2 = preciox2 * 2;
            preciox4 = preciox4 * 2;
        }
    }

}
