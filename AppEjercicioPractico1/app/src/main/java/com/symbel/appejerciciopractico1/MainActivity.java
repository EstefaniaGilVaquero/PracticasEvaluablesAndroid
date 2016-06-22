package com.symbel.appejerciciopractico1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.symbel.appejerciciopractico1.dao.OperacionesBaseDatos;
import com.symbel.appejerciciopractico1.dto.Usuarios;

public class MainActivity extends AppCompatActivity {

    Context context;

    //creo el objeto de la base de datos
    OperacionesBaseDatos datos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());


        //inserto los usuarios si la tabla está vacia
        if(datos.tableIsEmpty("USUARIOS")){

            Usuarios usuario1 = new Usuarios(1, "Estefi", 123);
            Usuarios usuario2 = new Usuarios(2, "Merche", 1234);
            Usuarios usuario3 = new Usuarios(3, "Henar", 12345);

            datos.insertarUsuario(usuario1);
            datos.insertarUsuario(usuario2);
            datos.insertarUsuario(usuario3);
        }

        //Listener
        View.OnClickListener objetoEscuchador = new escuchaEventos(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        Button botonOK = (Button)findViewById(R.id.loginBTN);
        botonOK.setOnClickListener(objetoEscuchador);
        Button botonBorrar = (Button)findViewById(R.id.borrarBTN);
        botonBorrar.setOnClickListener(objetoEscuchador);
 }

    public boolean validarUsuarioContrasena(){

        boolean validado = false;

        EditText usuarioTF = (EditText) findViewById(R.id.usuarioTF);
        EditText passwordTF = (EditText) findViewById(R.id.passwordTF);

        if ( usuarioTF.getText().length()!=0 && passwordTF.getText().length()!=0){
            //Validamos contra BD
            validado = datos.validarUsuarioPassword(usuarioTF.getText().toString(),Integer.parseInt(passwordTF.getText().toString()));

        }

        return validado;

    }

    public void borrarFormulario(){

        //Resetea el formulario a vacio
        EditText usuarioTF = (EditText) findViewById(R.id.usuarioTF);
        EditText passwordTF = (EditText) findViewById(R.id.passwordTF);

        usuarioTF.setText("");
        passwordTF.setText("");

    }

    //TODO: PREGUNTAR A VAL DIFERENCIA ENTRE PUTEXTRA Y ESTOS METODOS PARA PASAR INFOR DE UNA ACTIVIDAD A OTRA

    public String getUsuario(){
        EditText usuarioTF = (EditText) findViewById(R.id.usuarioTF);
        Log.d(getClass().getCanonicalName(), "Usuario: " + usuarioTF.getText().toString());
        return usuarioTF.getText().toString();
    }



}
