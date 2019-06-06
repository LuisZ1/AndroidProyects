package com.luisz.qrstore.Viewmodel;

import android.app.Application;
import android.content.Context;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;
    private Estanteria estanteriaEscaneada;
    private Caja cajaEscaneada;
    private Objeto objetoEscaneado;
    private ArrayList<Caja> listadoCajas;
    private ArrayList<Objeto> listadoObjetos;

    public ViewModel(Application application) {
        super(application);
        miAppContext = application.getApplicationContext();
    }

    public Estanteria getEstanteriaEscaneada() {
        return estanteriaEscaneada;
    }

    public void setEstanteriaEscaneada(Estanteria estanteriaEscaneada) {
        this.estanteriaEscaneada = estanteriaEscaneada;
    }

    public Caja getCajaEscaneada() {
        return cajaEscaneada;
    }

    public void setCajaEscaneada(Caja cajaEscaneada) {
        this.cajaEscaneada = cajaEscaneada;
    }

    public Objeto getObjetoEscaneado() {
        return objetoEscaneado;
    }

    public void setObjetoEscaneado(Objeto objetoEscaneado) {
        this.objetoEscaneado = objetoEscaneado;
    }

    public ArrayList<Caja> getListadoCajas() {
        return listadoCajas;
    }

    public void setListadoCajas(ArrayList<Caja> listadoCajas) {
        this.listadoCajas = listadoCajas;
    }

    public ArrayList<Objeto> getListadoObjetos() {
        return listadoObjetos;
    }

    public void setListadoObjetos(ArrayList<Objeto> listadoObjetos) {
        this.listadoObjetos = listadoObjetos;
    }
}
