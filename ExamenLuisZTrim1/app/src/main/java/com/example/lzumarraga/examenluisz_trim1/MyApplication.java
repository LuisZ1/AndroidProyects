package com.example.lzumarraga.examenluisz_trim1;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public ArrayList<Jugador> getListaJugadores() {

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

        String[] posiciones1 = {"Defensa", "Delantero"};
        String[] posiciones2 = {"Extremo derecho del banquillo", "Auxiliar de botella"};
        String[] posiciones3 = {"Fuego Igneo", "Dame tu cosita"};

        //String nombre, int foto, TipoJugador tipoJugador, String[] posiciones
        JugadorFutbol j1 = new JugadorFutbol("Kevin de Bruyne", R.drawable.kevin_de_bruyne_f,TipoJugador.FUTBOL, posiciones1);
        JugadorFutbol j2 = new JugadorFutbol("Romelu Lukaku", R.drawable.romelu_lukaku_f,TipoJugador.FUTBOL, posiciones2);
        JugadorFutbol j3 = new JugadorFutbol("Aurelio de Basic", R.drawable.romelu_lukaku_f,TipoJugador.FUTBOL, posiciones2);
        JugadorFutbol j4 = new JugadorFutbol("Charmander Le Blue", R.drawable.kevin_de_bruyne_f,TipoJugador.FUTBOL, posiciones3);
        JugadorFutbol j5 = new JugadorFutbol("Gandalf el Blanco", R.drawable.kevin_de_bruyne_f,TipoJugador.FUTBOL, posiciones2);
        JugadorFutbol j6 = new JugadorFutbol("Pablo Motos", R.drawable.romelu_lukaku_f,TipoJugador.FUTBOL, posiciones3);
        JugadorFutbol j7 = new JugadorFutbol("David Broncano", R.drawable.kevin_de_bruyne_f,TipoJugador.FUTBOL, posiciones3);

        //String nombre, int foto, TipoJugador tipoJugador, int puntos, int rebotes, int asistencias
        JugadorBaloncesto j10 = new JugadorBaloncesto("Lebron James", R.drawable.lebron_james_b,TipoJugador.BALONCESTO, 360, 5, 250);
        JugadorBaloncesto j11 = new JugadorBaloncesto("Marc Gasol", R.drawable.marc_gasol_b,TipoJugador.BALONCESTO, 360, 150, 250);
        JugadorBaloncesto j13 = new JugadorBaloncesto("Pau Gasol", R.drawable.pau_gasol_b,TipoJugador.BALONCESTO, 400, 55, 25);
        JugadorBaloncesto j14 = new JugadorBaloncesto("Antonio Bragueta", R.drawable.lebron_james_b,TipoJugador.BALONCESTO, 360, 5, 45);
        JugadorBaloncesto j12 = new JugadorBaloncesto("Dolores Fuertes", R.drawable.pau_gasol_b,TipoJugador.BALONCESTO, 560, 7, 68);
        JugadorBaloncesto j15 = new JugadorBaloncesto("Andy y Lucas", R.drawable.marc_gasol_b,TipoJugador.BALONCESTO, 13500, 130, 0);
        JugadorBaloncesto j16 = new JugadorBaloncesto("Ortega y Gasset", R.drawable.lebron_james_b,TipoJugador.BALONCESTO, 5860, 1453, 250);

        //Rellenar lista
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j13);
        jugadores.add(j14);
        jugadores.add(j3);
        jugadores.add(j4);
        jugadores.add(j12);
        jugadores.add(j11);
        jugadores.add(j3);
        jugadores.add(j4);
        jugadores.add(j12);
        jugadores.add(j11);
        jugadores.add(j5);
        jugadores.add(j6);
        jugadores.add(j15);
        jugadores.add(j16);
        jugadores.add(j7);
        jugadores.add(j10);
        jugadores.add(j6);
        jugadores.add(j15);
        jugadores.add(j16);
        jugadores.add(j7);
        jugadores.add(j10);





        return jugadores;
    }
}
