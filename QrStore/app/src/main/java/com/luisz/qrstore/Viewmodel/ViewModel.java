package com.luisz.qrstore.Viewmodel;

import android.app.Application;
import android.content.Context;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;
    private Estanteria estanteriaEscaneada;
    private Caja cajaEscaneada;
    private Objeto objetoEscaneado;

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
}
