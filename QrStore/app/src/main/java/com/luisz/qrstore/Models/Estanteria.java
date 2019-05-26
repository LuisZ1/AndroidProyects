package com.luisz.qrstore.Models;

public class Estanteria {

    private String idestanteria;
    private String nombre;
    private String ubicacion;
    private String descripcion;

    public Estanteria() {
    }

    public Estanteria(String nombre, String ubicacion, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Estanteria(String idestanteria, String nombre, String ubicacion, String descripcion) {
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
