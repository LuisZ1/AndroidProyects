package com.luisz.simpleclicker.Models;

public class Mejora_Per_Maq_Her {

    private int id;
    private String nombre;
    private String colorFondo;
    private int imgFondo;

    private long precio;
    private double porcentajeAumento;
    private int limiteDeCompra;
    private int mejorasCompradas;

    public Mejora_Per_Maq_Her(int id, String nombre, long precio, double porcentajeAumento, int limiteDeCompra, String colorFondo, int imgFondo) {
        this.id = id;
        this.nombre = nombre;
        this.colorFondo = colorFondo;
        this.precio = precio;
        this.porcentajeAumento = porcentajeAumento;
        this.limiteDeCompra = limiteDeCompra;
        this.imgFondo = imgFondo;
    }
    public Mejora_Per_Maq_Her() {    }

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

    public int getMejorasCompradas() {
        return mejorasCompradas;
    }

    public void setMejorasCompradas(int mejorasCompradas) {
        this.mejorasCompradas = mejorasCompradas;
    }

    public int getImgFondo() {
        return imgFondo;
    }

    public void setImgFondo(int imgFondo) {
        this.imgFondo = imgFondo;
    }
}