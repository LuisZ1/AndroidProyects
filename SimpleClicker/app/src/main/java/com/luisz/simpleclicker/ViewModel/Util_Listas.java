package com.luisz.simpleclicker.ViewModel;

import com.luisz.simpleclicker.Models.Mejora_Personal_Maquinaria_Herramientas;

import java.util.ArrayList;

public class Util_Listas {

    /**
     * Devuelve lista predeterminada de Mejoras de Personal
     * */
    public ArrayList<Mejora_Personal_Maquinaria_Herramientas> rellenarListaMejorasPersonal(){
        ArrayList<Mejora_Personal_Maquinaria_Herramientas> listaMejorasPersonal = new ArrayList<Mejora_Personal_Maquinaria_Herramientas>();

        listaMejorasPersonal.add(new Mejora_Personal_Maquinaria_Herramientas(1,"Peón",3000000,0.5,70,"#FFC107"));
        listaMejorasPersonal.add(new Mejora_Personal_Maquinaria_Herramientas(2,"Minero",5500000,2,20,"#FFA000"));
        listaMejorasPersonal.add(new Mejora_Personal_Maquinaria_Herramientas(3,"Jefe de mina",10000000,3,15,"#FF6F00"));

        return listaMejorasPersonal;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Maquinaria
     * */
    public ArrayList<Mejora_Personal_Maquinaria_Herramientas> rellenarListaMejorasMaquinaria(){
        ArrayList<Mejora_Personal_Maquinaria_Herramientas> listaMejorasMaquinaria = new ArrayList<Mejora_Personal_Maquinaria_Herramientas>();

        listaMejorasMaquinaria.add(new Mejora_Personal_Maquinaria_Herramientas(1,"Pico",1000000,0.15,75,"#90A4AE"));
        listaMejorasMaquinaria.add(new Mejora_Personal_Maquinaria_Herramientas(2,"Martillo neumático",3500000,1.5,25,"#607D8B"));
        listaMejorasMaquinaria.add(new Mejora_Personal_Maquinaria_Herramientas(3,"Tuneladora",7500000,3,3,"#455A64"));
        listaMejorasMaquinaria.add(new Mejora_Personal_Maquinaria_Herramientas(4,"Tuneladora BIG",15000000,8.5,2,"#263238"));
        listaMejorasMaquinaria.add(new Mejora_Personal_Maquinaria_Herramientas(5,"Dinamita",3000000000l,35,1,"#d50000"));

        return listaMejorasMaquinaria;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Herramientas
     * */
    public ArrayList<Mejora_Personal_Maquinaria_Herramientas> rellenarListaMejorasHerramientas(){
        ArrayList<Mejora_Personal_Maquinaria_Herramientas> listaMejorasHerramientas = new ArrayList<Mejora_Personal_Maquinaria_Herramientas>();

        listaMejorasHerramientas.add(new Mejora_Personal_Maquinaria_Herramientas(1,"Madera",1000000,2,1,"#795548"));
        listaMejorasHerramientas.add(new Mejora_Personal_Maquinaria_Herramientas(2,"Piedra",2500000,5,1,"#B0BEC5"));
        listaMejorasHerramientas.add(new Mejora_Personal_Maquinaria_Herramientas(3,"Hierro",4000000,10,1,"#78909C"));
        listaMejorasHerramientas.add(new Mejora_Personal_Maquinaria_Herramientas(4,"Oro",5500000,15,1,"#FFD600"));
        listaMejorasHerramientas.add(new Mejora_Personal_Maquinaria_Herramientas(5,"Dinamita",7000000,20,1,"#00BCD4"));

        return listaMejorasHerramientas;
    }

}
