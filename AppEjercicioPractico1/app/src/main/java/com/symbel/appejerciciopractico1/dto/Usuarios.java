package com.symbel.appejerciciopractico1.dto;

/**
 * Created by estefi on 12/06/2016.
 */
public class Usuarios {

    private int id;
    private String usuario;
    private int password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String modelo) {
        this.usuario = modelo;
    }

    public Usuarios (int id, String usuario, int password)
    {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
    }

    public Usuarios (String usuario)
    {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }



}
