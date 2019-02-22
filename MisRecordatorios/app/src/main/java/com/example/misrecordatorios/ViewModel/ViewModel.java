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
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private static Context miAppContext;

    public LiveData<List<Recordatorio>> listadoRecordatorios ;
    //public MutableLiveData<ArrayList<Recordatorio>> listadoRecordatoriosMutable = new MutableLiveData<ArrayList<Recordatorio>>();
    private RecordatorioRepository repo;


    public ViewModel(Application application) {
        super(application);
        miAppContext = application.getApplicationContext();
        repo = new RecordatorioRepository();

        rellenarListado();

        listadoRecordatorios = repo.getListaRecordatoriosPorId( miAppContext , 0);

        listadoRecordatorios = repo.getListaRecordatorios( miAppContext );
    }

    private void rellenarListado(){
       /* listadoRecordatorios.add(new Recordatorio(new Date(), "0 Azul Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "1 Rojo Nullam porttitor fermentum nunc, semper condimentum sapien volutpat vel. Etiam rutrum eros at metus eleifend, nec cursus nulla lobortis.", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "2 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "3 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "4 Amarillo", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "5 Amarillo", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "6 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "7 Amarillo", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "8 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "9 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "10 Amarillo", "#FFC107"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "11 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "12 Marrón", "#795548"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "13 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "14 Rojo", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "15 Rojo", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "16 Rojo", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "17 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "18 Rojo", "#f44336"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "19 Azul", "#2196F3"));
        listadoRecordatorios.add(new Recordatorio(new Date(), "20 Azul", "#2196F3"));*/

       String fecha = new Date().toString();
       repo.insertRecordatorio( miAppContext ,new Recordatorio( fecha , "0 Azul Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "#2196F3"));

        //listadoRecordatoriosMutable.setValue(listadoRecordatorios);
    }

    public LiveData<List<Recordatorio>> recuperarRecordatorios(){
        return repo.getListaRecordatorios( miAppContext ); /*= repo.getListaRecordatorios( miAppContext );*/
    }

    public LiveData<List<Recordatorio>> getListadoRecordatorios() {
        return listadoRecordatorios;
    }

    public void setListadoRecordatorios(LiveData<List<Recordatorio>> listadoRecordatorios) {
        this.listadoRecordatorios = listadoRecordatorios;
    }
}
