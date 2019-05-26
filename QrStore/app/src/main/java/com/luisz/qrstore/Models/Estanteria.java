package com.luisz.qrstore.Models;

public class Estanteria {

    private String idestanteria;
    private String nombre;
    private String lugar;
    private String descripcion;

    public Estanteria(String idestanteria, String nombre, String lugar) {
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public Estanteria(String idestanteria, String nombre, String lugar, String descripcion) {
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public String getIdestanteria() {
        return idestanteria;
    }

    public void setIdestanteria(String idestanteria) {
        this.idestanteria = idestanteria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
