package com.example.lzumarraga.ejemploconlistadinamica;

public class Equipo {

    //ATRIBUTOS
    private String nombre;

    //CONSTRUCTORES
    public Equipo(){
        this.nombre = "";
    }

    public Equipo (String nombre){
        this.nombre = nombre;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //AÑADIDOS
    @Override
    public String toString() {
        return nombre ;
    }
}
