package com.example.lzumarraga.ejemploconlistadinamica;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Equipo implements Parcelable {

    //ATRIBUTOS
    private String nombre;
    private String denominacion;
    private liga liga;
    private int escudo;
    private String descripcion;

    //CONSTRUCTORES
    public Equipo(){
        this.nombre = "equipo";
        this.denominacion = "equipo";
    }

    public Equipo (String nombre, String denominacion, liga liga, String descripcion){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.descripcion = descripcion;
        this.liga = liga;
    }

    public Equipo (String nombre, String denominacion, int escudo){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
        this.descripcion = "nulo";
    }

    public Equipo (String nombre, String denominacion, int escudo, liga liga){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
        this.descripcion = "nulo";
        this.liga = liga;
    }

    public Equipo (String nombre, String denominacion, int escudo,String descripcion){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
        this.descripcion = descripcion;
    }

    public Equipo (String nombre, String denominacion, int escudo,liga liga, String descripcion){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
        this.liga = liga;
        this.descripcion = descripcion;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public com.example.lzumarraga.ejemploconlistadinamica.liga getLiga() {
        return liga;
    }

    public void setLiga(com.example.lzumarraga.ejemploconlistadinamica.liga liga) {
        this.liga = liga;
    }

    //AÑADIDOS
    @Override
    public String toString() {
        return denominacion ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.denominacion);
        dest.writeInt(this.liga == null ? -1 : this.liga.ordinal());
        dest.writeInt(this.escudo);
        dest.writeString(this.descripcion);
    }

    protected Equipo(Parcel in) {
        this.nombre = in.readString();
        this.denominacion = in.readString();
        int tmpLiga = in.readInt();
        this.liga = tmpLiga == -1 ? null : com.example.lzumarraga.ejemploconlistadinamica.liga.values()[tmpLiga];
        this.escudo = in.readInt();
        this.descripcion = in.readString();
    }

    public static final Parcelable.Creator<Equipo> CREATOR = new Parcelable.Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel source) {
            return new Equipo(source);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };
}
