package com.example.lzumarraga.ejemploconlistadinamica;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class equipitos {

    @SerializedName("equipos")
    @Expose
    private static Equipo[] equipos;

    public equipitos(Equipo[] equipos) {
        this.equipos = equipos;
    }

    public equipitos() {
        equipos = null;
    }

    public static Equipo[] getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }


}
