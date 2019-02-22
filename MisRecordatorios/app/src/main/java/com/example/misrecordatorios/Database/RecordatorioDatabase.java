package com.example.misrecordatorios.Database;

import com.example.misrecordatorios.Models.Recordatorio;

import androidx.room.Database;

@Database(entities = {Recordatorio.class}, version = 1)
public abstract class RecordatorioDatabase {

    public abstract RecordatorioDAO recordatorioDAO();

}
