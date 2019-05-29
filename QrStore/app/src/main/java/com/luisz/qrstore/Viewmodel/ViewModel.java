package com.luisz.qrstore.Viewmodel;

import android.app.Application;
import android.content.Context;

import com.luisz.qrstore.Models.Estanteria;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;
    private Estanteria estanteriaEscaneada;

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
}
