package com.example.lzumarraga.ejemploconlistadinamica;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipo {

    //ATRIBUTOS
    private String nombre;
    private String denominacion;
    private int escudo;
    private String descripcion;

    //CONSTRUCTORES
    public Equipo(){
        this.nombre = "";
    }

    public Equipo (String nombre, String denominacion, int escudo){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
        this.descripcion = "nulo";
    }

    public Equipo (String nombre, String denominacion, int escudo,String descripcion){
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.escudo = escudo;
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

    //AÑADIDOS
    @Override
    public String toString() {
        return denominacion ;
    }

}
