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

        listaMejoras.add(new Mejora(101, contexto.getString(R.string.aluminio), 0, cons.BASE_PRECIO_ALUMINIO, cons.BASE_INGRESOS_ALUMINIO, cons.BASE_INGRESOS_ALUMINIO, "#D0D8D9"));
        listaMejoras.add(new Mejora(102, contexto.getString(R.string.zinc), 0, cons.BASE_PRECIO_ZINC, cons.BASE_INGRESOS_ZINC, cons.BASE_INGRESOS_ZINC, "#B6B6B6"));
        listaMejoras.add(new Mejora(103, contexto.getString(R.string.cobre), 0, cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE, cons.BASE_INGRESOS_COBRE, "#FFB74D"));
        listaMejoras.add(new Mejora(104, contexto.getString(R.string.niquel), 0, cons.BASE_PRECIO_NIQUEL, cons.BASE_INGRESOS_NIQUEL, cons.BASE_INGRESOS_NIQUEL, "#FFE57F"));
        listaMejoras.add(new Mejora(105, contexto.getString(R.string.bronce), 0, cons.BASE_PRECIO_BRONCE, cons.BASE_INGRESOS_BRONCE, cons.BASE_INGRESOS_BRONCE, "#EF6C00"));
        listaMejoras.add(new Mejora(106, contexto.getString(R.string.plata), 0, cons.BASE_PRECIO_PLATA, cons.BASE_INGRESOS_PLATA, cons.BASE_INGRESOS_PLATA, "#9E9E9E"));
        listaMejoras.add(new Mejora(107, contexto.getString(R.string.iridio), 0, cons.BASE_PRECIO_IRIDIO, cons.BASE_INGRESOS_IRIDIO, cons.BASE_INGRESOS_IRIDIO, "#FFFF8D"));
        listaMejoras.add(new Mejora(108, contexto.getString(R.string.oro), 0, cons.BASE_PRECIO_ORO, cons.BASE_INGRESOS_ORO, cons.BASE_INGRESOS_ORO, "#FFD600"));
        listaMejoras.add(new Mejora(109, contexto.getString(R.string.platino), 0, cons.BASE_PRECIO_PLATINO, cons.BASE_INGRESOS_PLATINO, cons.BASE_INGRESOS_PLATINO, "#BDBDBD"));
        listaMejoras.add(new Mejora(110, contexto.getString(R.string.uranio), 0, cons.BASE_PRECIO_URANIO, cons.BASE_INGRESOS_URANIO, cons.BASE_INGRESOS_URANIO, "#137656"));

        return listaMejoras;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de autoclick
     * */
    public ArrayList<Mejora_AutoClick> rellenarListaMejorasAutoClick(Context contexto){
        ArrayList<Mejora_AutoClick> listaMejorasAutoClick = new ArrayList<Mejora_AutoClick>();

        listaMejorasAutoClick.add(new Mejora_AutoClick(201, contexto.getString(R.string.autoclick_nivel1), 10000000, 1000, "#90CAF9"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(202, contexto.getString(R.string.autoclick_nivel2), 100000000, 500, "#42A5F5"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(203, contexto.getString(R.string.autoclick_nivel3), 1000000000, 100, "#1E88E5"));
        listaMejorasAutoClick.add(new Mejora_AutoClick(204, contexto.getString(R.string.autoclick_nivel4), 10000000000L, 50, "#1565C0"));

        return listaMejorasAutoClick;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Personal
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasPersonal(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasPersonal = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(301,contexto.getString(R.string.personal_peon),30000000,0.5,70,"#FFC107",R.drawable.it_peon));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(301,contexto.getString(R.string.personal_aprendiz),55000000,1,45,"#FFC107",R.drawable.it_aprendiz));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(302,contexto.getString(R.string.personal_minero),2750000000L,2,20,"#FFA000",R.drawable.it_minero));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(303,contexto.getString(R.string.personal_arquitecto),15000000000L,3,10,"#FF6F00", R.drawable.it_arquitecto));
        listaMejorasPersonal.add(new Mejora_Per_Maq_Her(304,contexto.getString(R.string.personal_Jefazo),100000000000L,8,5,"#E65100", R.drawable.it_jefazo));

        return listaMejorasPersonal;
    }


    /**
     * Devuelve lista predeterminada de Mejoras de Maquinaria
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasMaquinaria(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasMaquinaria = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(401,contexto.getString(R.string.maquinaria_pico),1000000,0.15,100,"#90A4AE", R.drawable.it_pico));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(402,contexto.getString(R.string.maquinaria_martillo),350000000,1.5,60,"#607D8B", R.drawable.it_martillo));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(403,contexto.getString(R.string.maquinaria_vagoneta),850000000,3,30,"#455A64", R.drawable.it_vagoneta));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(405,contexto.getString(R.string.maquinaria_dinamita),6500000000L,9,15,"#d50000", R.drawable.it_dinamita));
        listaMejorasMaquinaria.add(new Mejora_Per_Maq_Her(404,contexto.getString(R.string.maquinaria_tuneladora),40500000000L,30,10,"#37474F", R.drawable.it_tuneladora));

        return listaMejorasMaquinaria;
    }

    /**
     * Devuelve lista predeterminada de Mejoras de Herramientas
     * */
    public ArrayList<Mejora_Per_Maq_Her> rellenarListaMejorasHerramientas(Context contexto){
        ArrayList<Mejora_Per_Maq_Her> listaMejorasHerramientas = new ArrayList<Mejora_Per_Maq_Her>();

        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(501,contexto.getString(R.string.herramientas_madera),1000000,2,12,"#795548", R.drawable.it_madera));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(502,contexto.getString(R.string.herramientas_piedra),25000000,5,10,"#B0BEC5", R.drawable.it_piedra));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(503,contexto.getString(R.string.herramientas_hierro),400000000,10,8,"#78909C", R.drawable.it_hierro));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(504,contexto.getString(R.string.herramientas_oro),5500000000L,15,5,"#FFD600", R.drawable.it_oro));
        listaMejorasHerramientas.add(new Mejora_Per_Maq_Her(505,contexto.getString(R.string.herramientas_diamante),20000000000L,20,2,"#00BCD4", R.drawable.it_diamante));

        return listaMejorasHerramientas;
    }

}
