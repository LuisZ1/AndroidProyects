package com.luisz.qrstore.Viewmodel;

import android.app.Application;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;

import java.util.ArrayList;

import androidx.lifecycle.AndroidViewModel;

public class ViewModel extends AndroidViewModel {

    private Estanteria estanteriaEscaneada;
    private Caja cajaEscaneada;
    private Objeto objetoEscaneado;
    private ArrayList<Estanteria> listadoEstanterias;
    private ArrayList<Caja> listadoCajas;
    private ArrayList<Objeto> listadoObjetos;
    private String nombreUsuario;

    public ViewModel(Application application) {
        super(application);
    }

    public void reiniciarVariables() {
        estanteriaEscaneada = null;
        cajaEscaneada = null;
        objetoEscaneado = null;
        listadoEstanterias = null;
        listadoCajas = null;
        listadoObjetos = null;
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

    public ArrayList<Estanteria> getListadoEstanterias() {
        return listadoEstanterias;
    }

    public void setListadoEstanterias(ArrayList<Estanteria> listadoEstanterias) {
        this.listadoEstanterias = listadoEstanterias;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
