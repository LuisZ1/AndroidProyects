package com.luisz.flama.clicker.clickerflama;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class ViewModel extends android.arch.lifecycle.ViewModel{

    constantesClicker cons = new constantesClicker();

    public long sumador = 1, contador = 0;

    public long precioCobre = cons.BASE_PRECIO_COBRE;
    public long precioBronce = cons.BASE_PRECIO_BRONCE;
    public long precioPlata = cons.BASE_PRECIO_PLATA;
    public long precioOro = cons.BASE_PRECIO_ORO;
    public long precioPlatino = cons.BASE_PRECIO_PLATINO;
    public long precioDiamante = cons.BASE_PRECIO_DIAMANTE;

//    public long exponenteCobre = 1, exponenteBronce = 1, exponentePlata = 1;
//    public long exponenteOro = 1, exponenteDiamante = 1, exponenteaPlatino = 1;

    public long contadorPulsaciones=0, contadorCobre = 0, contadorBronce = 0, contadorPlata = 0;
    public long contadorPulsacionesParcial=0, contadorOro = 0, contadorPlatino = 0, contadorDiamante = 0;



    public long sumatron (){
        contador = contador + sumador;
        contadorPulsaciones++;
        contadorPulsacionesParcial++;
        return contador;
    }

    public void sumadorCobre(){
        if(contador >= precioCobre) {
            contadorCobre++;
            contador = contador - precioCobre;
            precioCobre = (long) Math.ceil(cons.BASE_PRECIO_COBRE * Math.pow(cons.MULTIPLICADOR, contadorCobre));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorCobre, cons.BASE_INGRESOS_COBRE * contadorCobre)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorBronce(){
        if(contador >= precioBronce) {
            contadorBronce++;
            contador = contador - precioBronce;
            precioBronce = (long) Math.ceil(cons.BASE_PRECIO_BRONCE * Math.pow(cons.MULTIPLICADOR, contadorBronce));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorBronce, cons.BASE_INGRESOS_BRONCE * contadorBronce)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorPlata(){
        if(contador >= precioPlata) {
            contadorPlata++;
            contador = contador - precioPlata;
            precioPlata = (long) Math.ceil(cons.BASE_PRECIO_PLATA * Math.pow(cons.MULTIPLICADOR, contadorPlata));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorPlata, cons.BASE_INGRESOS_PLATA * contadorPlata)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorOro(){
        if(contador >= precioOro) {
            contadorOro++;
            contador = contador - precioOro;
            precioOro = (long) Math.ceil(cons.BASE_PRECIO_ORO * Math.pow(cons.MULTIPLICADOR, contadorOro));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorOro, cons.BASE_INGRESOS_ORO * contadorOro)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorPlatino(){
        if(contador >= precioPlatino) {
            contadorPlatino++;
            contador = contador - precioPlatino;
            precioPlatino = (long) Math.ceil(cons.BASE_PRECIO_PLATINO * Math.pow(cons.MULTIPLICADOR, contadorPlatino));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorPlatino, cons.BASE_INGRESOS_PLATINO * contadorPlatino)));
            contadorPulsacionesParcial = 0;
        }
    }

    public void sumadorDiamante(){
        if(contador >= precioDiamante) {
            contadorDiamante++;
            contador = contador - precioDiamante;
            precioDiamante = (long) Math.ceil(cons.BASE_PRECIO_DIAMANTE * Math.pow(cons.MULTIPLICADOR, contadorDiamante));
            sumador = sumador +((long) Math.ceil(Math.max(cons.minimoSumadorDiamante, cons.BASE_INGRESOS_DIAMANTE * contadorDiamante)));
            contadorPulsacionesParcial = 0;
        }
    }


    //MINERO AUTOMATICO
//
//    private Timer timer;
//    private TimerTask timerTask;
//    private Handler handler = new Handler();
//
//    //To stop timer
//    public void stopTimer(){
//        if(timer != null){
//            timer.cancel();
//            timer.purge();
//        }
//    }
//
//    //To start timer
//    public void startTimer(){
//        timer = new Timer();
//        timerTask = new TimerTask() {
//            public void run() {
//                handler.post(new Runnable() {
//                    public void run(){
//                        sumatron ();
//                    }
//                });
//            }
//        };
//        timer.schedule(timerTask, 1000, 1000);
//    }

}
