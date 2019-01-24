package com.luisz.simpleclicker.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.luisz.simpleclicker.Models.Constantes;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.Models.Mejora_AutoClick;
import com.luisz.simpleclicker.Models.Mejora_Per_Maq_Her;

import java.util.ArrayList;

public class ViewModel extends /*android.arch.lifecycle.ViewModel*/ AndroidViewModel {

    private Constantes cons = new Constantes();
    private Util_Listas util_listas = new Util_Listas();

    //variables
    private long sumador = 1;
    private long puntos = 0;
    private boolean autoClickComprado;
    private int delay;
    private int rowNumber = 2;
    private double multiplicador = 1;

    //contadores
    private long contadorPulsacionesPartida = 0, contadorPulsacionesTotal = 0, contadorPulsacionesParcial = 0;
    private long contadorMejorasPartida = 0, contadorMejorasTotal = 0;
    private long contadorPuntosGastadosTotal = 0, contadorPuntosGastadosPartida = 0;
    private long contadorAluminioPartida = 0, contadorZincPartida = 0, contadorCobrePartida = 0, contadorNiquelPartida = 0, contadorBroncePartida = 0, contadorPlataPartida = 0, contadorIridioPartida = 0, contadorOroPartida = 0, contadorPlatinoPartida = 0, contadorUranioPartida = 0;
    private long contadorAluminioTotal = 0, contadorZincTotal = 0, contadorCobreTotal = 0, contadorNiquelTotal = 0, contadorBronceTotal = 0, contadorPlataTotal = 0, contadorIridioTotal = 0, contadorOroTotal = 0, contadorPlatinoTotal = 0, contadorUranioTotal = 0;
    private long puntuacionMaximaTotal = 0, puntuacionMaximaPartida = 0;

    //listas
    public MutableLiveData<ArrayList<Mejora>> listaMejorasMutable = new MutableLiveData<ArrayList<Mejora>>();
    public ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();

    public MutableLiveData<ArrayList<Mejora_AutoClick>> listaMejoraAutoClickMutable = new MutableLiveData<ArrayList<Mejora_AutoClick>>();
    public ArrayList<Mejora_AutoClick> listaMejoraAutoClick = new ArrayList<Mejora_AutoClick>();

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraPersonalMutable = new MutableLiveData<ArrayList<Mejora_Per_Maq_Her>>();
    public ArrayList<Mejora_Per_Maq_Her> listaMejoraPersonal = new ArrayList<Mejora_Per_Maq_Her>();

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraMaquinariaMutable = new MutableLiveData<ArrayList<Mejora_Per_Maq_Her>>();
    public ArrayList<Mejora_Per_Maq_Her> listaMejoraMaquinaria = new ArrayList<Mejora_Per_Maq_Her>();

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraHerramientasMutable = new MutableLiveData<ArrayList<Mejora_Per_Maq_Her>>();
    public ArrayList<Mejora_Per_Maq_Her> listaMejoraHerramientas = new ArrayList<Mejora_Per_Maq_Her>();

    //contexto
    private static Context miAppContext;

    public ViewModel(Application application) {
        super(application);
        miAppContext = application.getApplicationContext();
        sumador = 1;
        puntos = 0;
        delay = 100000;
        contadorPulsacionesPartida = 0;
        contadorPulsacionesParcial = 0;
        autoClickComprado = false;
        rellenarListaMejorasAutoClick();
        rellenarListaMejoras();
    }

    /**
     * public long sumatron ()
     * DESCRIPCIÓN: es el encargado de aumentar los puntos y los contadores
     * ENTRADA: -- será llamado desde el evento click
     * SALIDA: -- aumenta los contadores
     */
    public long sumatron() {
        try {
            puntos = puntos + (sumador * (long) multiplicador);
            contadorPulsacionesPartida++;
            contadorPulsacionesParcial++;
            contadorPulsacionesTotal++;

            if (puntos > puntuacionMaximaTotal) {
                puntuacionMaximaTotal = puntos;
            }

            if (puntos > puntuacionMaximaPartida) {
                puntuacionMaximaPartida = puntos;
            }

        } catch (Exception e) {
        }
        return puntos;
    }

    /**
     * synchronized boolean sumadorMejora(int mejoraSeleccionada)
     * DESCRIPCIÓN: calcula los nuevos precios y sumadores de la mejora pulsada, declarado
     * synchronized para protegerlo en multihilos
     * ENTRADA: indice de la posicion que ocupa la mejora en el array de mejoras
     * SALIDA: true en caso de que haya ido todo bien
     */
    public synchronized boolean sumadorMejora(int mejoraSeleccionada) {

        boolean veredicto = false;
        Mejora mejora = new Mejora(1, "Cobre", 0, cons.BASE_PRECIO_COBRE, cons.BASE_INGRESOS_COBRE, cons.MINIMO_SUMADOR_COBRE, "#CC8F60");
        try {
            mejora = listaMejoras.get(mejoraSeleccionada);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        if (puntos >= mejora.getPrecio()) {
            try {
                mejora.setNivel(mejora.getNivel() + 1);
                puntos = puntos - mejora.getPrecio();
                contadorPuntosGastadosPartida = contadorPuntosGastadosPartida + mejora.getPrecio();
                contadorPuntosGastadosTotal = contadorPuntosGastadosTotal + mejora.getPrecio();
                mejora.setPrecio((long) Math.ceil(mejora.getPrecioBase() * Math.pow(cons.MULTIPLICADOR, mejora.getNivel() / cons.DIVISOR_EXP_INICIAL)));
                sumador =sumador + ((long) Math.ceil(mejora.getIngresosBase()));
                contadorPulsacionesParcial = 0;

                listaMejoras.remove(mejoraSeleccionada);
                listaMejoras.add(mejoraSeleccionada, mejora);

                listaMejorasMutable.setValue(listaMejoras);
                aumentarContadoresClicMejora(mejora);

                veredicto = true;
            } catch (Exception e) {
            }
        }
        return veredicto;
    }

    private void aumentarContadoresClicMejora(Mejora mejora) {
        try {
            contadorMejorasTotal++;
            contadorMejorasPartida++;
        } catch (Exception e) {
        }

        switch (mejora.getId()) {
            case 1:
                contadorAluminioPartida++;
                contadorAluminioTotal++;
                break;
            case 2:

                contadorZincPartida++;
                contadorZincTotal++;
                break;
            case 3:
                contadorCobrePartida++;
                contadorCobreTotal++;
                break;
            case 4:
                contadorNiquelPartida++;
                contadorNiquelTotal++;
                break;
            case 5:
                contadorBroncePartida++;
                contadorBronceTotal++;
                break;
            case 6:
                contadorPlataPartida++;
                contadorPlataTotal++;
                break;
            case 7:
                contadorIridioPartida++;
                contadorIridioTotal++;
                break;
            case 8:
                contadorOroPartida++;
                contadorOroTotal++;
                break;
            case 9:
                contadorPlatinoPartida++;
                contadorPlatinoTotal++;
                break;
            case 10:
                contadorUranioPartida++;
                contadorUranioTotal++;
                break;
        }

    }

    public boolean clickCompraMejoraAutoClick(int mejoraAutoClickSeleccionada) {
        boolean resultado = false;
        Mejora_AutoClick mejoraSeleccionada = listaMejoraAutoClick.get(mejoraAutoClickSeleccionada);

        if (puntos >= mejoraSeleccionada.getPrecio()) {
            puntos = puntos - mejoraSeleccionada.getPrecio();
            delay = mejoraSeleccionada.getDelay();
            autoClickComprado = true;
            resultado = true;
        }

        return resultado;
    }

    public boolean clickCompraMejora_Per_Maq_Her(String tipoMejora, int IDmejoraSeleccionada) {
        boolean resultado = false;
        Mejora_Per_Maq_Her mejoraSeleccionada = new Mejora_Per_Maq_Her();

        switch (tipoMejora){
            case "personal": mejoraSeleccionada = listaMejoraPersonal.get(IDmejoraSeleccionada);
                break;
            case "maquinaria": mejoraSeleccionada = listaMejoraMaquinaria.get(IDmejoraSeleccionada);
                break;
            case "herramientas": mejoraSeleccionada = listaMejoraHerramientas.get(IDmejoraSeleccionada);
                break;
        }
        if (puntos >= mejoraSeleccionada.getPrecio() && mejoraSeleccionada.getMejorasCompradas() < mejoraSeleccionada.getLimiteDeCompra()) {
            puntos = puntos - mejoraSeleccionada.getPrecio();
            multiplicador = multiplicador + (mejoraSeleccionada.getPorcentajeAumento()/10); //TODO revisar
            mejoraSeleccionada.setMejorasCompradas(mejoraSeleccionada.getMejorasCompradas()+1);

            switch (tipoMejora){
                case "personal":
                    listaMejoraPersonal.remove(IDmejoraSeleccionada);
                    listaMejoraPersonal.add(IDmejoraSeleccionada, mejoraSeleccionada);
                    listaMejoraPersonalMutable.setValue(listaMejoraPersonal);
                    break;
                case "maquinaria":
                    listaMejoraMaquinaria.remove(IDmejoraSeleccionada);
                    listaMejoraMaquinaria.add(IDmejoraSeleccionada, mejoraSeleccionada);
                    listaMejoraMaquinariaMutable.setValue(listaMejoraMaquinaria);
                    break;
                case "herramientas":
                    listaMejoraHerramientas.remove(IDmejoraSeleccionada);
                    listaMejoraHerramientas.add(IDmejoraSeleccionada, mejoraSeleccionada);
                    listaMejoraHerramientasMutable.setValue(listaMejoraHerramientas);
                    break;
            }

            resultado = true;
        }

        return resultado;
    }

    /**
     * public void rellenarListaMejoras()
     * DESCRIPCIÓN:
     * ENTRADA:
     * SALIDA:
     */
    public void rellenarListaMejoras() {
        listaMejoras = util_listas.rellenarListaMejoras(miAppContext);
        listaMejorasMutable.setValue(listaMejoras);
    }

    public void rellenarListaMejorasAutoClick() {
        listaMejoraAutoClick = util_listas.rellenarListaMejorasAutoClick(miAppContext);
        listaMejoraAutoClickMutable.setValue(listaMejoraAutoClick);
    }

    public void rellenarListaMejorasPersonal() {
        listaMejoraPersonal = util_listas.rellenarListaMejorasPersonal(miAppContext);
        listaMejoraPersonalMutable.setValue(listaMejoraPersonal);
    }

    public void rellenarListaMejorasMaquinaria() {
        listaMejoraMaquinaria = util_listas.rellenarListaMejorasMaquinaria(miAppContext);
        listaMejoraMaquinariaMutable.setValue(listaMejoraMaquinaria);
    }

    public void rellenarListaMejorasHerramientas() {
        listaMejoraHerramientas = util_listas.rellenarListaMejorasHerramientas(miAppContext);
        listaMejoraHerramientasMutable.setValue(listaMejoraHerramientas);
    }

    public boolean reiniciarPartida() {
        boolean resultado = false;
        try {
            sumador = 1;
            puntos = 0;
            contadorPulsacionesPartida = 0;
            contadorPulsacionesParcial = 0;
            contadorMejorasPartida = 0;
            contadorPuntosGastadosPartida = 0;
            puntuacionMaximaPartida = 0;
            autoClickComprado = false;
            delay = 1000000;

            contadorAluminioPartida = 0;
            contadorZincPartida = 0;
            contadorCobrePartida = 0;
            contadorNiquelPartida = 0;
            contadorBroncePartida = 0;
            contadorPlataPartida = 0;
            contadorIridioPartida = 0;
            contadorOroPartida = 0;
            contadorPlatinoPartida = 0;
            contadorUranioPartida = 0;

            listaMejoras.clear();
            rellenarListaMejoras();

            listaMejoraAutoClick.clear();
            rellenarListaMejorasAutoClick();

            listaMejoraPersonal.clear();
            rellenarListaMejorasPersonal();

            listaMejoraMaquinaria.clear();
            rellenarListaMejorasMaquinaria();

            listaMejoraHerramientas.clear();
            rellenarListaMejorasHerramientas();

            resultado = true;
        } catch (Exception e) {
        }

        return resultado;
    }

    public boolean reiniciarEstadisticasTotales() {
        boolean resultado = false;
        try {
            contadorPulsacionesTotal = 0;
            contadorMejorasTotal = 0;
            contadorPuntosGastadosTotal = 0;
            puntuacionMaximaTotal = 0;

            contadorAluminioTotal = 0;
            contadorZincTotal = 0;
            contadorCobreTotal = 0;
            contadorNiquelTotal = 0;
            contadorBronceTotal = 0;
            contadorPlataTotal = 0;
            contadorIridioTotal = 0;
            contadorOroTotal = 0;
            contadorPlatinoTotal = 0;
            contadorUranioTotal = 0;

            resultado = true;
        } catch (Exception e) {
        }

        return resultado;
    }


    // Getters
    public long getSumador() {
        return sumador;
    }

    public long getPuntos() {
        return puntos;
    }

    public long getContadorPulsacionesPartida() {
        return contadorPulsacionesPartida;
    }

    public long getContadorPulsacionesParcial() {
        return contadorPulsacionesParcial;
    }

    public long getContadorPulsacionesTotal() {
        return contadorPulsacionesTotal;
    }

    public long getContadorMejorasTotal() {
        return contadorMejorasTotal;
    }

    public long getContadorMejorasPartida() {
        return contadorMejorasPartida;
    }

    public long getContadorPuntosGastadosTotal() {
        return contadorPuntosGastadosTotal;
    }

    public long getContadorPuntosGastadosPartida() {
        return contadorPuntosGastadosPartida;
    }

    public long getContadorAluminioPartida() {
        return contadorAluminioPartida;
    }

    public long getContadorZincPartida() {
        return contadorZincPartida;
    }

    public long getContadorCobrePartida() {
        return contadorCobrePartida;
    }

    public long getContadorNiquelPartida() {
        return contadorNiquelPartida;
    }

    public long getContadorBroncePartida() {
        return contadorBroncePartida;
    }

    public long getContadorPlataPartida() {
        return contadorPlataPartida;
    }

    public long getContadorIridioPartida() {
        return contadorIridioPartida;
    }

    public long getContadorOroPartida() {
        return contadorOroPartida;
    }

    public long getContadorPlatinoPartida() {
        return contadorPlatinoPartida;
    }

    public long getContadorUranioPartida() {
        return contadorUranioPartida;
    }

    public long getContadorAluminioTotal() {
        return contadorAluminioTotal;
    }

    public long getContadorZincTotal() {
        return contadorZincTotal;
    }

    public long getContadorCobreTotal() {
        return contadorCobreTotal;
    }

    public long getContadorNiquelTotal() {
        return contadorNiquelTotal;
    }

    public long getContadorBronceTotal() {
        return contadorBronceTotal;
    }

    public long getContadorPlataTotal() {
        return contadorPlataTotal;
    }

    public long getContadorIridioTotal() {
        return contadorIridioTotal;
    }

    public long getContadorOroTotal() {
        return contadorOroTotal;
    }

    public long getContadorPlatinoTotal() {
        return contadorPlatinoTotal;
    }

    public long getContadorUranioTotal() {
        return contadorUranioTotal;
    }

    public long getPuntuacionMaximaTotal() {
        return puntuacionMaximaTotal;
    }

    public long getPuntuacionMaximaPartida() {
        return puntuacionMaximaPartida;
    }

    public MutableLiveData<ArrayList<Mejora>> getListaMejorasMutable() {
        return listaMejorasMutable;
    }

    public ArrayList<Mejora> getListaMejoras() {
        return listaMejoras;
    }

    public MutableLiveData<ArrayList<Mejora_AutoClick>> getListaMejoraAutoClickMutable() {
        return listaMejoraAutoClickMutable;
    }

    public ArrayList<Mejora_AutoClick> getListaMejoraAutoClick() {
        return listaMejoraAutoClick;
    }

    public boolean isAutoClickComprado() {
        return autoClickComprado;
    }

    public int getDelay() {
        return delay;
    }

    public ArrayList<Mejora_Per_Maq_Her> getListaMejoraPersonal() {
        return listaMejoraPersonal;
    }

    public ArrayList<Mejora_Per_Maq_Her> getListaMejoraMaquinaria() {
        return listaMejoraMaquinaria;
    }

    public ArrayList<Mejora_Per_Maq_Her> getListaMejoraHerramientas() {
        return listaMejoraHerramientas;
    }

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> getListaMejoraPersonalMutable() {
        return listaMejoraPersonalMutable;
    }

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> getListaMejoraMaquinariaMutable() {
        return listaMejoraMaquinariaMutable;
    }

    public MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> getListaMejoraHerramientasMutable() {
        return listaMejoraHerramientasMutable;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    //Setters
    public void setSumador(long sumador) {
        this.sumador = sumador;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    public void setContadorPulsacionesPartida(long contadorPulsacionesPartida) {
        this.contadorPulsacionesPartida = contadorPulsacionesPartida;
    }

    public void setContadorPulsacionesParcial(long contadorPulsacionesParcial) {
        this.contadorPulsacionesParcial = contadorPulsacionesParcial;
    }

    public void setContadorPulsacionesTotal(long contadorPulsacionesTotal) {
        this.contadorPulsacionesTotal = contadorPulsacionesTotal;
    }

    public void setContadorMejorasTotal(long contadorMejorasTotal) {
        this.contadorMejorasTotal = contadorMejorasTotal;
    }

    public void setListaMejorasMutable(MutableLiveData<ArrayList<Mejora>> listaMejorasMutable) {
        this.listaMejorasMutable = listaMejorasMutable;
    }

    public void setListaMejoras(ArrayList<Mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }

    public void setContadorMejorasPartida(long contadorMejorasPartida) {
        this.contadorMejorasPartida = contadorMejorasPartida;
    }

    public void setContadorPuntosGastadosTotal(long contadorPuntosGastadosTotal) {
        this.contadorPuntosGastadosTotal = contadorPuntosGastadosTotal;
    }

    public void setContadorPuntosGastadosPartida(long contadorPuntosGastadosPartida) {
        this.contadorPuntosGastadosPartida = contadorPuntosGastadosPartida;
    }

    public void setContadorAluminioPartida(long contadorAluminioPartida) {
        this.contadorAluminioPartida = contadorAluminioPartida;
    }

    public void setContadorZincPartida(long contadorZincPartida) {
        this.contadorZincPartida = contadorZincPartida;
    }

    public void setContadorCobrePartida(long contadorCobrePartida) {
        this.contadorCobrePartida = contadorCobrePartida;
    }

    public void setContadorNiquelPartida(long contadorNiquelPartida) {
        this.contadorNiquelPartida = contadorNiquelPartida;
    }

    public void setContadorBroncePartida(long contadorBroncePartida) {
        this.contadorBroncePartida = contadorBroncePartida;
    }

    public void setContadorPlataPartida(long contadorPlataPartida) {
        this.contadorPlataPartida = contadorPlataPartida;
    }

    public void setContadorIridioPartida(long contadorIridioPartida) {
        this.contadorIridioPartida = contadorIridioPartida;
    }

    public void setContadorOroPartida(long contadorOroPartida) {
        this.contadorOroPartida = contadorOroPartida;
    }

    public void setContadorPlatinoPartida(long contadorPlatinoPartida) {
        this.contadorPlatinoPartida = contadorPlatinoPartida;
    }

    public void setContadorUranioPartida(long contadorUranioPartida) {
        this.contadorUranioPartida = contadorUranioPartida;
    }

    public void setContadorAluminioTotal(long contadorAluminioTotal) {
        this.contadorAluminioTotal = contadorAluminioTotal;
    }

    public void setContadorZincTotal(long contadorZincTotal) {
        this.contadorZincTotal = contadorZincTotal;
    }

    public void setContadorCobreTotal(long contadorCobreTotal) {
        this.contadorCobreTotal = contadorCobreTotal;
    }

    public void setContadorNiquelTotal(long contadorNiquelTotal) {
        this.contadorNiquelTotal = contadorNiquelTotal;
    }

    public void setContadorBronceTotal(long contadorBronceTotal) {
        this.contadorBronceTotal = contadorBronceTotal;
    }

    public void setContadorPlataTotal(long contadorPlataTotal) {
        this.contadorPlataTotal = contadorPlataTotal;
    }

    public void setContadorIridioTotal(long contadorIridioTotal) {
        this.contadorIridioTotal = contadorIridioTotal;
    }

    public void setContadorOroTotal(long contadorOroTotal) {
        this.contadorOroTotal = contadorOroTotal;
    }

    public void setContadorPlatinoTotal(long contadorPlatinoTotal) {
        this.contadorPlatinoTotal = contadorPlatinoTotal;
    }

    public void setContadorUranioTotal(long contadorUranioTotal) {
        this.contadorUranioTotal = contadorUranioTotal;
    }

    public void setPuntuacionMaximaTotal(long puntuacionMaximaTotal) {
        this.puntuacionMaximaTotal = puntuacionMaximaTotal;
    }

    public void setPuntuacionMaximaPartida(long puntuacionMaximaPartida) {
        this.puntuacionMaximaPartida = puntuacionMaximaPartida;
    }

    public void setAutoClickComprado(boolean autoClickComprado) {
        this.autoClickComprado = autoClickComprado;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setListaMejoraPersonalMutable(MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraPersonalMutable) {
        this.listaMejoraPersonalMutable = listaMejoraPersonalMutable;
    }

    public void setListaMejoraMaquinariaMutable(MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraMaquinariaMutable) {
        this.listaMejoraMaquinariaMutable = listaMejoraMaquinariaMutable;
    }

    public void setListaMejoraHerramientasMutable(MutableLiveData<ArrayList<Mejora_Per_Maq_Her>> listaMejoraHerramientasMutable) {
        this.listaMejoraHerramientasMutable = listaMejoraHerramientasMutable;
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }
}
