package com.example.lzumarraga.examenluisz_trim1;

import android.os.Parcel;

public class JugadorBaloncesto extends Jugador{

    //ATRIBUTOS
    private int puntos;
    private int rebotes;
    private int asistencias;

    //CONSTRUCTORES

    public JugadorBaloncesto(String nombre, int foto, TipoJugador tipoJugador, int puntos, int rebotes, int asistencias) {
        super(nombre, foto, tipoJugador);
        this.puntos = puntos;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
    }

    public JugadorBaloncesto(Parcel in, int puntos, int rebotes, int asistencias) {
        super(in);
        this.puntos = puntos;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
    }

    //GETTERS Y SETTERS
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.puntos);
        dest.writeInt(this.rebotes);
        dest.writeInt(this.asistencias);
    }

    protected JugadorBaloncesto(Parcel in) {
        super(in);
        this.puntos = in.readInt();
        this.rebotes = in.readInt();
        this.asistencias = in.readInt();
    }

    public static final Creator<JugadorBaloncesto> CREATOR = new Creator<JugadorBaloncesto>() {
        @Override
        public JugadorBaloncesto createFromParcel(Parcel source) {
            return new JugadorBaloncesto(source);
        }

        @Override
        public JugadorBaloncesto[] newArray(int size) {
            return new JugadorBaloncesto[size];
        }
    };
}
