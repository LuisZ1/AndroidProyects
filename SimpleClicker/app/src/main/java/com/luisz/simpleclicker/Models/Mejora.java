package com.luisz.simpleclicker.Models;

import android.annotation.SuppressLint;
import android.graphics.Color;

public class Mejora {

    private int id;
    private String nombre;
    private long nivel;
    private String colorFondo;

    private long precioBase;
    private long precio;
    private long ingresosBase;
    private long minimoSumador;

    public Mejora(int id, String nombre, long nivel, long precioBase, long ingresosBase, long minimoSumador, String colorFondo) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.precioBase = precioBase;
        this.ingresosBase = ingresosBase;
        this.minimoSumador = minimoSumador;
        this.colorFondo = colorFondo;
        this.precio = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public long getNivel() {
        return nivel;
    }

    public void setNivel(long nivel) {
        this.nivel = nivel;
    }

    public long getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(long precioBase) {
        this.precioBase = precioBase;
    }

    public long getIngresosBase() {
        return ingresosBase;
    }

    public void setIngresosBase(long ingresosBase) {
        this.ingresosBase = ingresosBase;
    }

    public long getMinimoSumador() {
        return minimoSumador;
    }

    public void setMinimoSumador(int minimoSumador) {
        this.minimoSumador = minimoSumador;
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
}