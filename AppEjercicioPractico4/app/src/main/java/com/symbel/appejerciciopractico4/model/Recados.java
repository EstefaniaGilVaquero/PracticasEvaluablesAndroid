package com.symbel.appejerciciopractico4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by estefi on 13/09/2016.
 */
public class Recados{

    private String fechaHora;
    private String nombreCliente;
    private String telefono;
    private String direccionRecogida;
    private String direccionEntrega;
    private String descripcion;
    private String fechaHoraMaxima;



    public Recados(String fechaHora, String nombreCliente, String telefono, String direccionRecogida,
                   String direccionEntrega, String descripcion, String fechaHoraMaxima)
    {
        this.fechaHora = fechaHora;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.direccionRecogida = direccionRecogida;
        this.direccionEntrega = direccionEntrega;
        this.descripcion = descripcion;
        this.fechaHoraMaxima = fechaHoraMaxima;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccionRecogida() {
        return direccionRecogida;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaHoraMaxima() {
        return fechaHoraMaxima;
    }
}
