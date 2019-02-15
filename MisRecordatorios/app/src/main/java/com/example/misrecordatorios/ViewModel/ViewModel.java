package com.example.misrecordatorios.ViewModel;

import android.app.Application;
import android.content.Context;

import com.example.misrecordatorios.Models.Recordatorio;

import java.util.ArrayList;
import java.util.Date;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;

    private ArrayList<Recordatorio> listadoRecordatorios = new ArrayList<Recordatorio>();
    private MutableLiveData<ArrayList<Recordatorio>> listadoRecordatoriosMutable = new MutableLiveData<ArrayList<Recordatorio>>();



    public ViewModel(Application application) {
        super(application);
        miAppContext = application.getApplicationContext();
        rellenarListado();
    }

    private void rellenarListado(){
        listadoRecordatorios.add(new Recordatorio(new Date(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "Nullam porttitor fermentum nunc, semper condimentum sapien volutpat vel. Etiam rutrum eros at metus eleifend, nec cursus nulla lobortis.", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#795548"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota 1", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota dos", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "nota III", "#2196F3"));

        listadoRecordatoriosMutable.setValue(listadoRecordatorios);
    }

    public ArrayList<Recordatorio> getListadoRecordatorios() {
        return listadoRecordatorios;
    }

    public void setListadoRecordatorios(ArrayList<Recordatorio> listadoRecordatorios) {
        this.listadoRecordatorios = listadoRecordatorios;
    }

    public MutableLiveData<ArrayList<Recordatorio>> getListadoRecordatoriosMutable() {
        return listadoRecordatoriosMutable;
    }

    public void setListadoRecordatoriosMutable(MutableLiveData<ArrayList<Recordatorio>> listadoRecordatoriosMutable) {
        this.listadoRecordatoriosMutable = listadoRecordatoriosMutable;
    }
}
