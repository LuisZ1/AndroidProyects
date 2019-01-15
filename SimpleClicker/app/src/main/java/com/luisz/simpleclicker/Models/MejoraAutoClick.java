package com.luisz.simpleclicker.Models;

public class MejoraAutoClick {

    private int id;
    private String nombre;
    private String colorFondo;

    private long precio;
    private int delay;
    private int period;

    public MejoraAutoClick(int id, String nombre, long precio, int delay, String colorFondo) {
        this.id = id;
        this.nombre = nombre;
        this.colorFondo = colorFondo;
        this.precio = precio;
        this.delay = delay;
        this.period = delay;
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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
