package com.example.lzumarraga.examenluisz_trim1;

import android.os.Parcel;

import java.util.List;

public class JugadorFutbol extends Jugador {

    //Atributos
    private String[] posiciones;
    private int posicionActual; //indice del array en el que está su posicion actual

    //Constructores


    public JugadorFutbol(String nombre, int foto, TipoJugador tipoJugador, String[] posiciones) {
        super(nombre, foto, tipoJugador);
        this.posiciones = posiciones;
    }

    public JugadorFutbol(Parcel in, String[] posiciones) {
        super(in);
        this.posiciones = posiciones;
    }

    //getters y setters
    public String[] getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(String[] posiciones) {
        this.posiciones = posiciones;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(this.posiciones);
    }

    protected JugadorFutbol(Parcel in) {
        super(in);
        this.posiciones = in.createStringArray();
    }

    public static final Creator<JugadorFutbol> CREATOR = new Creator<JugadorFutbol>() {
        @Override
        public JugadorFutbol createFromParcel(Parcel source) {
            return new JugadorFutbol(source);
        }

        @Override
        public JugadorFutbol[] newArray(int size) {
            return new JugadorFutbol[size];
        }
    };
}

