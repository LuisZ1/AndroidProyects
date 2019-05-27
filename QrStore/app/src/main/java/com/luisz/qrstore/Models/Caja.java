package com.luisz.qrstore.Models;

public class Caja {

    private String idCaja;
    private String idestanteria;
    private String nombre;
    private String descripcion;

    public Caja(String idestanteria, String nombre, String descripcion) {
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Caja() {
    }

    public String getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(String idCaja) {
        this.idCaja = idCaja;
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

    @Override
    public String toString() {
        return getNombre();
    }
}