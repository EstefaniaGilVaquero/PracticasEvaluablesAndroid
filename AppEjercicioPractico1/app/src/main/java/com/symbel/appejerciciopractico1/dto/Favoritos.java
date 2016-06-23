package com.symbel.appejerciciopractico1.dto;

/**
 * Created by estefi on 12/06/2016.
 */
public class Favoritos {

    private String usuario;
    private int idImage;

    public Favoritos (String usuario, int idImage)
    {
        this.usuario = usuario;
        this.idImage = idImage;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
