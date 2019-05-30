package com.luisz.qrstore.Models;

public class Objeto {

    private String idobjeto;
    private String idcaja;
    private String nombre;
    private String descripcion;

    public Objeto(String idcaja, String nombre, String descripcion) {
        this.idcaja = idcaja;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Objeto() {
    }

    public String getIdobjeto() {
        return idobjeto;
    }

    public void setIdobjeto(String idobjeto) {
        this.idobjeto = idobjeto;
    }

    public String getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(String idcaja) {
        this.idcaja = idcaja;
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
