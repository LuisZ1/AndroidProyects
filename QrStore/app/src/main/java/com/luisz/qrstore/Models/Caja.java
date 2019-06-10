package com.luisz.qrstore.Models;

public class Caja {

    private String idcaja;
    private String idestanteria;
    private String nombre;
    private String descripcion;

    public Caja(String idestanteria, String nombre, String descripcion) {
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Caja(String idcaja, String idestanteria, String nombre, String descripcion) {
        this.idcaja = idcaja;
        this.idestanteria = idestanteria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Caja() {
    }

    public String getidcaja() {
        return idcaja;
    }

    public void setidcaja(String idcaja) {
        this.idcaja = idcaja;
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
