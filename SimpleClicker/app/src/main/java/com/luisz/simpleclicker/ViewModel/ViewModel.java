package com.luisz.simpleclicker.ViewModel;

import android.arch.lifecycle.MutableLiveData;

import com.luisz.simpleclicker.Models.Constantes;
import com.luisz.simpleclicker.Models.Mejora;

import java.util.ArrayList;

public class ViewModel extends android.arch.lifecycle.ViewModel{

    Constantes cons = new Constantes();

    public long sumador = 1, puntos = 0;

    public long contadorPulsaciones=0;
    public long contadorPulsacionesParcial=0;

    public MutableLiveData<ArrayList<Mejora>> listaMejorasMutable = new MutableLiveData<ArrayList<Mejora>>();
    public ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();


    public ViewModel(){
        sumador = 1;
        puntos = 100;
        contadorPulsaciones=100;
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
    synchronized boolean sumadorMejora(int mejoraSeleccionada){

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

        Mejora miMejora = new Mejora(1,"Cobre",0,cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE,cons.MINIMO_SUMADOR_COBRE, "#CC8F60");
        Mejora miMejora1 = new Mejora(2,"Bronce",0,cons.BASE_PRECIO_BRONCE, cons.BASE_INGRESOS_BRONCE,cons.MINIMO_SUMADOR_BRONCE,"#CD7F32");
        Mejora miMejora2 = new Mejora(3,"Plata",0,cons.BASE_PRECIO_PLATA,cons.BASE_INGRESOS_PLATA,cons.MINIMO_SUMADOR_PLATA,"#B3B6AF");
        Mejora miMejora3 = new Mejora(4,"Oro",0,cons.BASE_PRECIO_ORO,cons.BASE_INGRESOS_ORO,cons.MINIMO_SUMADOR_ORO,"#C39738");
        Mejora miMejora4 = new Mejora(5,"Platino",0,cons.BASE_PRECIO_PLATINO,cons.BASE_INGRESOS_PLATINO,cons.MINIMO_SUMADOR_PLATINO,"#D9D6CE");
        Mejora miMejora5 = new Mejora(6,"Diamante",0,cons.BASE_PRECIO_DIAMANTE,cons.BASE_INGRESOS_DIAMANTE,cons.MINIMO_SUMADOR_DIAMANTE,"#7FBFFF");

        listaMejoras.add(miMejora);
        listaMejoras.add(miMejora1);
        listaMejoras.add(miMejora2);
        listaMejoras.add(miMejora3);
        listaMejoras.add(miMejora4);
        listaMejoras.add(miMejora5);

        listaMejorasMutable.setValue(listaMejoras);
    }

    /*  public ArrayList<Mejora> getListaPredefinida()
     *   DESCRIPCIÓN:
     *   ENTRADA:
     *   SALIDA:
     */
    public ArrayList<Mejora> getListaPredefinida(){

        ArrayList<Mejora> misMejorasPred = new ArrayList<>();

        Mejora miMejora = new Mejora(1,"cobre",0,cons.BASE_PRECIO_COBRE,7,1, "#CC8F60");
        Mejora miMejora1 = new Mejora(2,"bronce",0,cons.BASE_PRECIO_BRONCE,25,10,"#CD7F32");
        Mejora miMejora2 = new Mejora(3,"plata",0,cons.BASE_PRECIO_PLATA,75,50,"#B3B6AF");
        Mejora miMejora3 = new Mejora(4,"oro",0,cons.BASE_PRECIO_ORO,190,180,"#C39738");
        Mejora miMejora4 = new Mejora(5,"platino",0,cons.BASE_PRECIO_PLATINO,600,500,"#D9D6CE");
        Mejora miMejora5 = new Mejora(6,"diamante",0,cons.BASE_PRECIO_DIAMANTE,1500,1000,"#7FBFFF");

        misMejorasPred.add(miMejora);
        misMejorasPred.add(miMejora1);
        misMejorasPred.add(miMejora2);
        misMejorasPred.add(miMejora3);
        misMejorasPred.add(miMejora4);
        misMejorasPred.add(miMejora5);

        return misMejorasPred;
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
}
