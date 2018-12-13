package com.luisz.flama.clicker.clickerflama;

public class ViewModel extends android.arch.lifecycle.ViewModel{

    public long sumador = 1, contador = 0;
    public long precioCobre = 50;
    public long precioBronce = 5000;
    public long precioPlata = 500000;
    public long x = 1, y = 0, z = 0;
    public long contadorPulsaciones=0, contadorCobre = 0, contadorBronce = 0, contadorPlata = 0;

    public long sumatron (){
        contador = contador + sumador;
        contadorPulsaciones++;
        return contador;
    }

    public void sumadorCobre(){
        if(contador >= precioCobre) {
            contador = contador - precioCobre;
            precioCobre = ((long) Math.ceil(precioCobre + Math.max(50, 15 * Math.pow(1.1, ++x))));
            sumador = (long) Math.ceil(sumador + Math.max(1, 0.25 * Math.pow(1.07, x)));
            contadorCobre++;
        }
    }

    public void sumadorBronce(){
        if(contador >= precioBronce) {

            contador = contador - precioBronce;
            precioBronce = ((long)Math.ceil(precioBronce + Math.max(5000, 1550 * Math.pow(1.11, ++y))));
            sumador = (long)Math.ceil(sumador + Math.max(100, 25 * Math.pow(1.07, y)));
            contadorBronce++;
        }
    }

    //TODO
    public void sumadorPlata(){
        if(contador >= precioPlata) {
            contador = contador - precioPlata;
            precioPlata = ((long)Math.ceil(precioPlata + Math.max(500000, 155000 * Math.pow(1.1111, ++z))));
            sumador = (long)Math.ceil(sumador + Math.max(100, 2500 * Math.pow(1.07, z)));
            contadorPlata++;
        }
    }

}
