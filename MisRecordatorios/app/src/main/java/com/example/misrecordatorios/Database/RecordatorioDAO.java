package com.example.misrecordatorios.Database;

import com.example.misrecordatorios.Models.Recordatorio;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

public interface RecordatorioDAO {

    @Query("SELECT * FROM Recordatorio")
    LiveData<ArrayList<Recordatorio>> getListaRecordatorios();

    @Query("SELECT * FROM Recordatorio WHERE idRecordatorio = :idR")
    LiveData<Recordatorio> getRecordatorioById(int idR);

    @Insert(onConflict = OnConflictStrategy.ABORT) //Realmente la tiene por defecto
    Long insertRecordatorio(Recordatorio r);

    @Delete
    void deleteRecordatorio(Recordatorio r);

}
