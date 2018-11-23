package com.example.lzumarraga.examenluisz_trim1;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {

    //ATRIBUTOS
    private String nombre;
    private int foto;
    private TipoJugador tipoJugador;

    //CONSTRUCTORES


    public Jugador(String nombre, int foto, TipoJugador tipoJugador) {
        this.nombre = nombre;
        this.foto = foto;
        this.tipoJugador = tipoJugador;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }

    public void setTipoJugador(TipoJugador tipoJugador) {
        this.tipoJugador = tipoJugador;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeInt(this.foto);
        dest.writeInt(this.tipoJugador == null ? -1 : this.tipoJugador.ordinal());
    }

    protected Jugador(Parcel in) {
        this.nombre = in.readString();
        this.foto = in.readInt();
        int tmpTipoJugador = in.readInt();
        this.tipoJugador = tmpTipoJugador == -1 ? null : TipoJugador.values()[tmpTipoJugador];
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel source) {
            return new Jugador(source);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}
