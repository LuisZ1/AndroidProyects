package com.example.misrecordatorios.Database;

import com.example.misrecordatorios.Models.Recordatorio;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RecordatorioDAO {

    @Query("SELECT * FROM Recordatorio")
    LiveData<List<Recordatorio>> getListaRecordatorios();

    @Query("SELECT * FROM Recordatorio WHERE idRecordatorio = :idR")
    LiveData<Recordatorio> getRecordatorioById(int idR);

    @Query("SELECT * FROM Recordatorio WHERE idRecordatorio = :idR")
    LiveData<List<Recordatorio>> getListaRecordatoriosPorId(int idR);

    @Insert(onConflict = OnConflictStrategy.ABORT) //Realmente la tiene por defecto
    Long insertRecordatorio(Recordatorio r);

    @Delete
    void deleteRecordatorio(Recordatorio r);

}
