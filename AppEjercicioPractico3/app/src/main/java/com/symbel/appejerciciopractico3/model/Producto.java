package com.symbel.appejerciciopractico3.model;

import android.media.Image;

import java.util.UUID;

/**
 * Created by estefi on 21/07/2016.
 */

        /*Desarrollar una práctica que como actividad principal muestre una pantalla con dos tabs
        (el whatspp tiene 3 -llamadas contactos y chats) llamados productos y registro.

        En productos se debe mostrar una lista que recupere una información remota que deberéis traer de un servidor.
        Debéis hacer la parte remota (servlet de java o php que os proporcione la información - no es necesaria la base de datos -)
        La lista muestra la información de productos (los que queráis) y de cada producto se obtiene

        Imagen, Nombre, Precio, Unidades y Descripción

        En la lista, se mostrará sólo la imagen y el nombre del producto.. Cuando el usuario clique un item de la lista,
        se mostrará otra ventana, que informará de todos los atributos del producto a pantalla completa: imagen, Nombre,
        Precio, Unidades y Descripción

        El otro tab, Registro, mostrará una lista de CardView relativos al histórico de productos consultados por el usuario.
        Es decir, por cada ítem clickado ( que se ha entrado a ver su detalle ),
        se generará una CardView que contendrá: Nombre del producto visualizado, foto y momento (fecha y hora)
        de la visualización del detalle de ese producto*/

public class Producto {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer unidades;
    private String descripcion;
    private Image imagen;

    //    public Producto() {
    //        // Generar identificador unico
    //        id = UUID.randomUUID();
    //
    //    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
