package com.symbel.appejerciciopractico4.model;


import java.util.Date;

/**
 * Created by estefi on 13/09/2016.
 */
public class Recados{

    private Date fecha_hora;
    private String nombre_cliente;
    private String telefono;
    private String direccion_recogida;
    private String direccion_entrega;
    private String descripcion;
    private Date fecha_hora_maxima;



    public Recados(Date fecha_hora, String nombre_cliente, String telefono, String direccion_recogida,
                   String direccion_Entrega, String descripcion, Date fecha_hora_maxima)
    {
        this.fecha_hora = fecha_hora;
        this.nombre_cliente = nombre_cliente;
        this.telefono = telefono;
        this.direccion_recogida = direccion_recogida;
        this.direccion_entrega = direccion_Entrega;
        this.descripcion = descripcion;
        this.fecha_hora_maxima = fecha_hora_maxima;
    }

    public Date getFechaHora() {
        return fecha_hora;
    }

    public String getNombreCliente() {
        return nombre_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccionRecogida() {
        return direccion_recogida;
    }

    public String getDireccionEntrega() {
        return direccion_entrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaHoraMaxima() {
        return fecha_hora_maxima;
    }
}
