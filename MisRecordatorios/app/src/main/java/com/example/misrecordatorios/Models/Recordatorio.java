package com.example.misrecordatorios.Models;

import java.util.Date;

public class Recordatorio {

    private Date fecha;
    private String contenido;

    public Recordatorio(Date fecha, String contenido) {
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
