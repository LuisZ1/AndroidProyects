package com.luisz.qrstore.Models;

public class Objeto {

    private String idobjeto;
    private String idcaja;
    private String nombre;
    private String descripcion;

    public Objeto(String idobjeto, String idcaja, String nombre) {
        this.idobjeto = idobjeto;
        this.idcaja = idcaja;
        this.nombre = nombre;
    }

    public Objeto(String idobjeto, String idcaja, String nombre, String descripcion) {
        this.idobjeto = idobjeto;
        this.idcaja = idcaja;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
