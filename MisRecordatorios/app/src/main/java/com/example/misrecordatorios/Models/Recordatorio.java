package com.example.misrecordatorios.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity
public class Recordatorio implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int idRecordatorio;
    private String fecha;
    private String contenido;
    private String color;

    public Recordatorio(String fecha, String contenido, String color) {
        this.fecha = fecha;
        this.contenido = contenido;
        this.color = color;
    }

    protected Recordatorio(Parcel in) {
        idRecordatorio = in.readInt();
        fecha = in.readString();
        contenido = in.readString();
        color = in.readString();
    }

    public static final Creator<Recordatorio> CREATOR = new Creator<Recordatorio>() {
        @Override
        public Recordatorio createFromParcel(Parcel in) {
            return new Recordatorio(in);
        }

        @Override
        public Recordatorio[] newArray(int size) {
            return new Recordatorio[size];
        }
    };

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idRecordatorio);
        dest.writeString(fecha);
        dest.writeString(contenido);
        dest.writeString(color);
    }
}
