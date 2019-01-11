package com.luisz.simpleclicker.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.luisz.simpleclicker.Models.Constantes;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.R;

import java.util.ArrayList;

public class ViewModel extends /*android.arch.lifecycle.ViewModel*/ AndroidViewModel{

    Constantes cons = new Constantes();

    @SerializedName("sumador")
    @Expose
    public long sumador = 1;

    @SerializedName("puntos")
    @Expose
    public long puntos = 0;

    @SerializedName("contadorPulsaciones")
    @Expose
    public long contadorPulsaciones=0;

    @SerializedName("contadorPulsacionesParcial")
    @Expose
    public long contadorPulsacionesParcial=0;

    @SerializedName("listaMejorasMutable")
    @Expose
    public MutableLiveData<ArrayList<Mejora>> listaMejorasMutable = new MutableLiveData<ArrayList<Mejora>>();

    @SerializedName("listaMejoras")
    @Expose
    public ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();

    private Context miAppContext;

    public ViewModel(Application application){
        super(application);
        miAppContext = application.getApplicationContext();
        sumador = 1;
        puntos = 0;
        contadorPulsaciones=0;
        contadorPulsacionesParcial=0;
        rellenarListaMejoras();
    }

    /*  public long sumatron ()
     *   DESCRIPCIÓN: es el encargado de aumentar los puntos y los contadores
     *   ENTRADA: -- será llamado desde el evento click
     *   SALIDA: -- aumenta los contadores
     */
    public long sumatron (){
        puntos = puntos + sumador;
        contadorPulsaciones++;
        contadorPulsacionesParcial++;
        return puntos;
    }

    /*  synchronized boolean sumadorMejora(int mejoraSeleccionada)
    *   DESCRIPCIÓN: calcula los nuevos precios y sumadores de la mejora pulsada, declarado
    *   synchronized para protegerlo en multihilos
    *   ENTRADA: indice de la posicion que ocupa la mejora en el array de mejoras
    *   SALIDA: true en caso de que haya ido todo bien
    */
    public synchronized boolean sumadorMejora(int mejoraSeleccionada){

        boolean veredicto = false;
        Mejora mejora = new Mejora(1,"Cobre",0,cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE,cons.MINIMO_SUMADOR_COBRE, "#CC8F60");
        try {
            mejora = listaMejoras.get(mejoraSeleccionada);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        if(puntos >= mejora.getPrecio()) {
            mejora.setNivel(mejora.getNivel()+1);
            puntos = puntos - mejora.getPrecio();
            mejora.setPrecio((long) Math.ceil(mejora.getPrecioBase() * Math.pow(cons.MULTIPLICADOR, mejora.getNivel())));
            sumador = sumador +((long) Math.ceil( mejora.getIngresosBase()/* * mejora.getNivel()*/));
            contadorPulsacionesParcial = 0;

            listaMejoras.remove(mejoraSeleccionada);
            listaMejoras.add(mejoraSeleccionada, mejora);

            listaMejorasMutable.setValue(listaMejoras);

            veredicto = true;
        }
        return veredicto;
    }

    /*  public void rellenarListaMejoras()
     *   DESCRIPCIÓN:
     *   ENTRADA:
     *   SALIDA:
     */
    public void rellenarListaMejoras(){

        listaMejoras.add(new Mejora(1, miAppContext.getString(R.string.aluminio),0,cons.BASE_PRECIO_ALUMINIO, cons.BASE_INGRESOS_ALUMINIO,cons.BASE_INGRESOS_ALUMINIO, "#D0D8D9"));
        listaMejoras.add(new Mejora(2, miAppContext.getString(R.string.zinc),0,cons.BASE_PRECIO_ZINC, cons.BASE_INGRESOS_ZINC,cons.BASE_INGRESOS_ZINC, "#B6B6B6"));
        listaMejoras.add(new Mejora(3, miAppContext.getString(R.string.cobre),0,cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE,cons.BASE_INGRESOS_COBRE, "#FFB74D"));
        listaMejoras.add(new Mejora(4, miAppContext.getString(R.string.niquel),0,cons.BASE_PRECIO_NIQUEL, cons.BASE_INGRESOS_NIQUEL,cons.BASE_INGRESOS_NIQUEL, "#FFE57F"));
        listaMejoras.add(new Mejora(5, miAppContext.getString(R.string.bronce),0,cons.BASE_PRECIO_BRONCE, cons.BASE_INGRESOS_BRONCE,cons.BASE_INGRESOS_BRONCE, "#EF6C00"));
        listaMejoras.add(new Mejora(6, miAppContext.getString(R.string.plata),0,cons.BASE_PRECIO_PLATA, cons.BASE_INGRESOS_PLATA,cons.BASE_INGRESOS_PLATA, "#9E9E9E"));
        listaMejoras.add(new Mejora(7, miAppContext.getString(R.string.iridio),0,cons.BASE_PRECIO_IRIDIO, cons.BASE_INGRESOS_IRIDIO,cons.BASE_INGRESOS_IRIDIO, "#FFFF8D"));
        listaMejoras.add(new Mejora(8, miAppContext.getString(R.string.oro),0,cons.BASE_PRECIO_ORO, cons.BASE_INGRESOS_ORO,cons.BASE_INGRESOS_ORO, "#FFD600"));
        listaMejoras.add(new Mejora(9, miAppContext.getString(R.string.platino),0,cons.BASE_PRECIO_PLATINO, cons.BASE_INGRESOS_PLATINO,cons.BASE_INGRESOS_PLATINO, "#BDBDBD"));
        listaMejoras.add(new Mejora(10, miAppContext.getString(R.string.uranio),0,cons.BASE_PRECIO_URANIO, cons.BASE_INGRESOS_URANIO,cons.BASE_INGRESOS_URANIO, "#137656"));

        listaMejorasMutable.setValue(listaMejoras);
    }
    /*  public MutableLiveData<ArrayList<Mejora>> getListaMejorasMutable()
     *   DESCRIPCIÓN:
     *   ENTRADA:
     *   SALIDA:
     */
    public MutableLiveData<ArrayList<Mejora>> getListaMejorasMutable() {
        return listaMejorasMutable;
    }

    /*  public ArrayList<Mejora> getListaMejoras()
     *   DESCRIPCIÓN:
     *   ENTRADA:
     *   SALIDA:
     */
    public ArrayList<Mejora> getListaMejoras() {
        return listaMejoras;
    }

    /*  public void setListaMejoras(ArrayList<Mejora> listaMejoras)
     *   DESCRIPCIÓN:
     *   ENTRADA:
     *   SALIDA:
     */
    public void setListaMejoras(ArrayList<Mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }

    public boolean reiniciarPartida(){
        boolean resultado = false;
        try{
            sumador = 1;
            puntos = 0;
            contadorPulsaciones=0;
            contadorPulsacionesParcial=0;
            listaMejoras.clear();
            rellenarListaMejoras();

            resultado = true;
        }catch (Exception e){}

        return resultado;
    }
}
