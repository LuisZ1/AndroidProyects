package com.example.misrecordatorios.Database;

import android.content.Context;

import com.example.misrecordatorios.Models.Recordatorio;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recordatorio.class}, version = 1, exportSchema = false)
public abstract class RecordatorioDatabase extends RoomDatabase {

    public abstract RecordatorioDAO recoDao();

    private static RecordatorioDatabase INSTANCE;

    public static  RecordatorioDatabase getDatabase(final Context context){

        if (INSTANCE == null){
            synchronized (RecordatorioDatabase.class){
                if (INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RecordatorioDatabase.class, "recordatorios_database.db")
                            .allowMainThreadQueries().build();
                }

            }

        }

        return INSTANCE;

    }

}
