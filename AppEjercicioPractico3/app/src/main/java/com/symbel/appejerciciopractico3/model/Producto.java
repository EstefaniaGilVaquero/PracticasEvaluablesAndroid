package com.symbel.appejerciciopractico3.model;

import android.graphics.Bitmap;
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

    private String id;
    private String nombre;
    private String precio;
    private String unidades;
    private String descripcion;
    private String fechaVisto;
    private String imagen;

    public Bitmap getImagenBitMap() {
        return imagenBitMap;
    }

    public void setImagenBitMap(Bitmap imagenBitMap) {
        this.imagenBitMap = imagenBitMap;
    }

    private Bitmap imagenBitMap;

    public Producto(String id, String nombre, String precio, String unidades, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(String nombre, String precio, String unidades, String descripcion, Bitmap imagenBitMap) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.imagenBitMap = imagenBitMap;
    }

    public Producto(String nombre, Bitmap imagenBitMap, String fechaVisto) {
        this.nombre = nombre;
        this.imagenBitMap = imagenBitMap;
        this.fechaVisto = fechaVisto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(String fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public String getId() {
        return id;
    }



    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }



    public String getPrecio() {
        return precio;
    }



    public String getUnidades() {
        return unidades;
    }



    public String getDescripcion() {
        return descripcion;
    }

}
