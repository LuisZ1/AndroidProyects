package com.example.misrecordatorios.Models;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Recordatorio {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int idRecordatorio;
    private String fecha;
    private String contenido;
    private String color;

    public Recordatorio(String fecha, String contenido, String color) {
        this.fecha = fecha;
        this.contenido = contenido;
        this.color = color;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
