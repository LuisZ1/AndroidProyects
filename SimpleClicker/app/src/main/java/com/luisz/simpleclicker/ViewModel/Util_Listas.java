package com.luisz.simpleclicker.ViewModel;

import android.content.Context;

import com.luisz.simpleclicker.Models.Constantes;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.Models.Mejora_AutoClick;
import com.luisz.simpleclicker.Models.Mejora_Per_Maq_Her;
import com.luisz.simpleclicker.R;

import java.util.ArrayList;

public class Util_Listas {

    private Constantes cons = new Constantes();

    /**
     * Devuelve lista predeterminada de Mejoras
     * */
    public ArrayList<Mejora> rellenarListaMejoras(Context contexto){
        ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();

        listaMejoras.add(new Mejora(1, contexto.getString(R.string.aluminio), 0, cons.BASE_PRECIO_ALUMINIO, cons.BASE_INGRESOS_ALUMINIO, cons.BASE_INGRESOS_ALUMINIO, "#D0D8D9"));
        listaMejoras.add(new Mejora(2, contexto.getString(R.string.zinc), 0, cons.BASE_PRECIO_ZINC, cons.BASE_INGRESOS_ZINC, cons.BASE_INGRESOS_ZINC, "#B6B6B6"));
        listaMejoras.add(new Mejora(3, contexto.getString(R.string.cobre), 0, cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE, cons.BASE_INGRESOS_COBRE, "#FFB74D"));
        listaMejoras.add(new Mejora(4, contexto.getString(R.string.niquel), 0, cons.BASE_PRECIO_NIQUEL, cons.BASE_INGRESOS_NIQUEL, cons.BASE_INGRESOS_NIQUEL, "#FFE57F"));
        listaMejoras.add(new Mejora(5, contexto.getString(R.string.bronce), 0, cons.BASE_PRECIO_BRONCE, cons.BASE_INGRESOS_BRONCE, cons.BASE_INGRESOS_BRONCE, "#EF6C00"));
        listaMejoras.add(new Mejora(6, contexto.getString(R.string.plata), 0, cons.BASE_PRECIO_PLATA, cons.BASE_INGRESOS_PLATA, cons.BASE_INGRESOS_PLATA, "#9E9E9E"));
        listaMejoras.add(new Mejora(7, contexto.getString(R.string.iridio), 0, cons.BASE_PRECIO_IRIDIO, cons.BASE_INGRESOS_IRIDIO, cons.BASE_INGRESOS_IRIDIO, "#FFFF8D"));
        listaMejoras.add(new Mejora(8, contexto.getString(R.string.oro), 0, cons.BASE_PRECIO_ORO, cons.BASE_INGRESOS_ORO, cons.BASE_INGRESOS_ORO, "#FFD600"));
        listaMejoras.add(new Mejora(9, contexto.getString(R.string.platino), 0, cons.BASE_PRECIO_PLATINO, cons.BASE_INGRESOS_PLATINO, cons.BASE_INGRESOS_PLATINO, "#BDBDBD"));
        listaMejoras.add(new Mejora(10, contexto.getString(R.string.uranio), 0, cons.BASE_PRECIO_URANIO, cons.BASE_INGRESOS_URANIO, cons.BASE_INGRESOS_URANIO, "#137656"));

        return listaMejoras;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de autoclick
     * */
    public ArrayList<Mejora_AutoClick> rellenarListaMejorasAutoClick(Context contexto){
        ArrayList<Mejora_AutoClick> listaMejorasAutoClick = new ArrayList<Mejora_AutoClick>();

        listaMejorasAutoClick.add(new Mejora_AutoClick(1, "Nivel 1", 10000000, 1000, "#90CAF9"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(2, "Nivel 2", 100000000, 500, "#42A5F5"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(3, "Nivel 3", 1000000000, 100, "#1E88E5"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(4, "Nivel 4", 10000000000L, 50, "#1565C0"));

        return listaMejorasAutoClick;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Personal
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasPersonal(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasPersonal = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(1,"Peón",30000000,0.5,70,"#FFC107",R.drawable.it_peon));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(2,"Minero",55000000,2,20,"#FFA000",R.drawable.it_minero));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(3,"Arquitecto",1000000000,3,10,"#FF6F00", R.drawable.it_arquitecto));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(4,"Jefazo",15000000000L,8,5,"#E65100", R.drawable.it_jefazo));

        return listaMejorasPersonal;
    }


    /**
     * Devuelve lista predeterminada de Mejoras de Maquinaria
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasMaquinaria(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasMaquinaria = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(1,"Pico",1000000,0.15,75,"#90A4AE", R.drawable.it_pico));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(2,"Martillo neumático",350000000,1.5,20,"#607D8B", R.drawable.it_pico));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(3,"Vagoneta",750000000,3,3,"#455A64", R.drawable.it_vagon));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(4,"Tuneladora",1500000000,8.5,2,"#263238", R.drawable.it_tuneladora));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(5,"Dinamita",3000000000L,35,1,"#d50000", R.drawable.it_pico));

        return listaMejorasMaquinaria;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Herramientas
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasHerramientas(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasHerramientas = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(1,"Madera",1000000,2,2,"#795548", R.drawable.it_piedra));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(2,"Piedra",25000000,5,2,"#B0BEC5", R.drawable.it_piedra));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(3,"Hierro",400000000,10,1,"#78909C", R.drawable.it_piedra));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(4,"Oro",5500000000L,15,1,"#FFD600", R.drawable.it_piedra));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(5,"Diamante",20000000000L,20,1,"#00BCD4", R.drawable.it_piedra));

        return listaMejorasHerramientas;
    }

}
