package com.example.misrecordatorios.Database;

import android.content.Context;

import com.example.misrecordatorios.Models.Recordatorio;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class RecordatorioRepository {

//    private final RecordatorioDAO recordatorioDAO;
//
//    @Inject
//    public RecordatorioRepository(RecordatorioDAO recordatorioDAO) {
//        this.recordatorioDAO = recordatorioDAO;
//    }

    public LiveData<List<Recordatorio>> getListaRecordatorios(Context c){
        return RecordatorioDatabase.getDatabase(c).recoDao().getListaRecordatorios();
    }

    public LiveData<List<Recordatorio>> getListaRecordatoriosPorId(Context c, int idR){
        return RecordatorioDatabase.getDatabase(c).recoDao().getListaRecordatoriosPorId(idR);
    }

    public LiveData<Recordatorio> getRecordatorio(Context c, int idR){
        return RecordatorioDatabase.getDatabase(c).recoDao().getRecordatorioById(idR);
    }

    public void deleteRecordatorio(Context c, Recordatorio r){
        RecordatorioDatabase.getDatabase(c).recoDao().deleteRecordatorio(r);
    }

    public void insertRecordatorio(Context c, Recordatorio r){
        RecordatorioDatabase.getDatabase(c).recoDao().insertRecordatorio(r);
    }

}
