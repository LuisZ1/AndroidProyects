package com.luisz.simpleclicker.Models;

public class Mejora_Personal_Maquinaria_Herramientas {

    private int id;
    private String nombre;
    private String colorFondo;

    private long precio;
    private double porcentajeAumento;
    private int limiteDeCompra;

    public Mejora_Personal_Maquinaria_Herramientas(int id, String nombre, long precio, double porcentajeAumento, int limiteDeCompra, String colorFondo) {
        this.id = id;
        this.nombre = nombre;
        this.colorFondo = colorFondo;
        this.precio = precio;
        this.porcentajeAumento = porcentajeAumento;
        this.limiteDeCompra = limiteDeCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public double getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setPorcentajeAumento(double porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }

    public int getLimiteDeCompra() {
        return limiteDeCompra;
    }

    public void setLimiteDeCompra(int limiteDeCompra) {
        this.limiteDeCompra = limiteDeCompra;
    }

    public int getId() {return id;}
}