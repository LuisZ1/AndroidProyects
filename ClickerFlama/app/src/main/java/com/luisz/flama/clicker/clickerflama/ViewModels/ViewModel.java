package com.luisz.flama.clicker.clickerflama.ViewModels;

import com.luisz.flama.clicker.clickerflama.modelos.constantesClicker;
import com.luisz.flama.clicker.clickerflama.modelos.mejora;

import java.util.ArrayList;

public class ViewModel extends android.arch.lifecycle.ViewModel{

    constantesClicker cons = new constantesClicker();

    public long sumador = 1, puntos = 0;

    public long precioCobre = cons.BASE_PRECIO_COBRE;
    public long precioBronce = cons.BASE_PRECIO_BRONCE;
    public long precioPlata = cons.BASE_PRECIO_PLATA;
    public long precioOro = cons.BASE_PRECIO_ORO;
    public long precioPlatino = cons.BASE_PRECIO_PLATINO;
    public long precioDiamante = cons.BASE_PRECIO_DIAMANTE;

    public long contadorPulsaciones=0, contadorCobre = 0, contadorBronce = 0, contadorPlata = 0;
    public long contadorPulsacionesParcial=0, contadorOro = 0, contadorPlatino = 0, contadorDiamante = 0;

    public ArrayList<mejora> listaMejoras = new ArrayList<mejora>();


    public ViewModel(){
        sumador = 1;
        puntos = 0;
        precioCobre = cons.BASE_PRECIO_COBRE;
        precioBronce = cons.BASE_PRECIO_BRONCE;
        precioPlata = cons.BASE_PRECIO_PLATA;
        precioOro = cons.BASE_PRECIO_ORO;
        precioPlatino = cons.BASE_PRECIO_PLATINO;
        precioDiamante = cons.BASE_PRECIO_DIAMANTE;
        contadorPulsaciones=0;
        contadorCobre = 0;
        contadorBronce = 0;
        contadorPlata = 0;
        contadorPulsacionesParcial=0;
        contadorOro = 0;
        contadorPlatino = 0;
        contadorDiamante = 0;
        rellenarListaMejoras();
    }

    public long sumatron (){
        puntos = puntos + sumador;
        contadorPulsaciones++;
        contadorPulsacionesParcial++;
        return puntos;
    }

    public void sumadorCobre(){
        if(puntos >= precioCobre) {
            contadorCobre++;
            puntos = puntos - precioCobre;
            precioCobre = (long) Math.ceil(cons.BASE_PRECIO_COBRE * Math.pow(cons.MULTIPLICADOR, contadorCobre));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_COBRE, cons.BASE_INGRESOS_COBRE * contadorCobre)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorBronce(){
        if(puntos >= precioBronce) {
            contadorBronce++;
            puntos = puntos - precioBronce;
            precioBronce = (long) Math.ceil(cons.BASE_PRECIO_BRONCE * Math.pow(cons.MULTIPLICADOR, contadorBronce));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_BRONCE, cons.BASE_INGRESOS_BRONCE * contadorBronce)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorPlata(){
        if(puntos >= precioPlata) {
            contadorPlata++;
            puntos = puntos - precioPlata;
            precioPlata = (long) Math.ceil(cons.BASE_PRECIO_PLATA * Math.pow(cons.MULTIPLICADOR, contadorPlata));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_PLATA, cons.BASE_INGRESOS_PLATA * contadorPlata)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorOro(){
        if(puntos >= precioOro) {
            contadorOro++;
            puntos = puntos - precioOro;
            precioOro = (long) Math.ceil(cons.BASE_PRECIO_ORO * Math.pow(cons.MULTIPLICADOR, contadorOro));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_ORO, cons.BASE_INGRESOS_ORO * contadorOro)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorPlatino(){
        if(puntos >= precioPlatino) {
            contadorPlatino++;
            puntos = puntos - precioPlatino;
            precioPlatino = (long) Math.ceil(cons.BASE_PRECIO_PLATINO * Math.pow(cons.MULTIPLICADOR, contadorPlatino));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_PLATINO, cons.BASE_INGRESOS_PLATINO * contadorPlatino)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorDiamante(){
        if(puntos >= precioDiamante) {
            contadorDiamante++;
            puntos = puntos - precioDiamante;
            precioDiamante = (long) Math.ceil(cons.BASE_PRECIO_DIAMANTE * Math.pow(cons.MULTIPLICADOR, contadorDiamante));
            sumador = sumador +((long) Math.ceil(Math.max(cons.MINIMO_SUMADOR_DIAMANTE, cons.BASE_INGRESOS_DIAMANTE * contadorDiamante)));
            contadorPulsacionesParcial = 0;
        }
    }

    public ArrayList<mejora> rellenarListaMejoras(){

        mejora miMejora = new mejora("cobre",0,cons.BASE_PRECIO_COBRE,7,1, "#CC8F60");
        mejora miMejora1 = new mejora("bronce",0,cons.BASE_PRECIO_BRONCE,25,10,"#CD7F32");
        mejora miMejora2 = new mejora("plata",0,cons.BASE_PRECIO_PLATA,75,100,"#B3B6AF");
        mejora miMejora3 = new mejora("oro",0,cons.BASE_PRECIO_ORO,190,1000,"#C39738");
        mejora miMejora4 = new mejora("platino",0,cons.BASE_PRECIO_PLATINO,600,10000,"#D9D6CE");
        mejora miMejora5 = new mejora("diamante",0,cons.BASE_PRECIO_DIAMANTE,1500,100000,"#7FBFFF");

        listaMejoras.add(miMejora);
        listaMejoras.add(miMejora1);
        listaMejoras.add(miMejora2);
        listaMejoras.add(miMejora3);
        listaMejoras.add(miMejora4);
        listaMejoras.add(miMejora5);

        return listaMejoras;
    }

    public ArrayList<mejora> getListaMejoras() {
        return listaMejoras;
    }
}
