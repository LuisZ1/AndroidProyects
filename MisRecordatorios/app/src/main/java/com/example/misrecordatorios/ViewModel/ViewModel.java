package com.example.misrecordatorios.ViewModel;

import android.app.Application;
import android.content.Context;

import com.example.misrecordatorios.Database.RecordatorioDAO;
import com.example.misrecordatorios.Database.RecordatorioRepository;
import com.example.misrecordatorios.Models.Recordatorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;

    public LiveData<List<Recordatorio>> listadoRecordatorios ;
    private RecordatorioRepository repo;


    public ViewModel(Application application) {
        super(application);
        miAppContext = application.getApplicationContext();
        repo = new RecordatorioRepository();

        listadoRecordatorios = repo.getListaRecordatorios( miAppContext );
    }

    private void rellenarListado(){

       String fecha = new Date().toString();
       repo.insertRecordatorio( miAppContext ,new Recordatorio( fecha , "Nota de prueba", "#2196F3"));
    }

    public LiveData<List<Recordatorio>> recuperarRecordatorios(){
        return repo.getListaRecordatorios( miAppContext );
    }

    public void guardarRecordatorioROOM(Recordatorio r){
        repo.insertRecordatorio( miAppContext , r);
    }

    public LiveData<List<Recordatorio>> getListadoRecordatorios() {
        return listadoRecordatorios;
    }

    public void setListadoRecordatorios(LiveData<List<Recordatorio>> listadoRecordatorios) {
        this.listadoRecordatorios = listadoRecordatorios;
    }
}
