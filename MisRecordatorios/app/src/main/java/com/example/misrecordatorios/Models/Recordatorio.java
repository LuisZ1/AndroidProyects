package com.example.misrecordatorios.Models;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Recordatorio {

    @PrimaryKey
    @NonNull
    private String idRecordatorio;
    private Date fecha;
    private String contenido;
    private String color;

    public Recordatorio(Date fecha, String contenido, String color) {
        this.fecha = fecha;
        this.contenido = contenido;
        this.color = color;
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

    public String getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(String idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
