package com.example.lzumarraga.mylistview;

public class Ciudad {

    int id_ciudad;
    String nombre;
    int poblacion;
    String descripcion;

    public Ciudad (){
        id_ciudad = 0;
        nombre = "";
        poblacion = 0;
        descripcion = "";
    }

    public Ciudad (int id_ciudad, String nombre, int poblacion, String descripcion){
        this.id_ciudad=id_ciudad;
        this.nombre=nombre;
        this.poblacion=poblacion;
        this.descripcion=descripcion;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return id_ciudad +" "+ nombre ;
    }
}
