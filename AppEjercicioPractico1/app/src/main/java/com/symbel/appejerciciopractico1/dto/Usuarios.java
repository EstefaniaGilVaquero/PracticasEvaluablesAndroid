package com.symbel.appejerciciopractico1.dto;

/**
 * Created by estefi on 12/06/2016.
 */
public class Usuarios {

    private String usuario;
    private int password;

    public Usuarios (String usuario, int password)
    {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
