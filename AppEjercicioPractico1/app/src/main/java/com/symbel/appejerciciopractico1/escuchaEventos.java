package com.symbel.appejerciciopractico1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.symbel.appejerciciopractico1.dao.BaseDatosGaleria;
import com.symbel.appejerciciopractico1.dao.OperacionesBaseDatos;
import com.symbel.appejerciciopractico1.dto.Favoritos;
import com.symbel.appejerciciopractico1.dto.Usuarios;

import java.lang.reflect.Array;

/**
 * Created by estefi on 09/06/2016.
 */
public class EscuchaEventos implements View.OnClickListener{

    Context context;

    public EscuchaEventos(Context context){this.context = context;}

 @Override
    public void onClick(View vista_seleccioanda)
    {

        OperacionesBaseDatos datos;
        Favoritos favorito;
        MainActivity mMainActivity;
        DisplayImages mDisplayImages = null;

        //obtengo el Id de la vista
        int id_vista_seleccionada = vista_seleccioanda.getId();

        Log.d(getClass().getCanonicalName(), "Ha pulsado un boton");

        switch (id_vista_seleccionada)
        {
            case R.id.loginBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton OK de login");
                mMainActivity = (MainActivity) this.context;
                EditText caja_usuario = (EditText) mMainActivity.findViewById(R.id.usuarioTF);
                String usuario = caja_usuario.getText().toString();
                UsuarioActivo.setUsuarioActivo(usuario);

                //Validar que se ha introducido usuario y contrasena registrados
                //Si ha introducido usuario y contrasena
                if(mMainActivity.validarUsuarioContrasena()){
                    //Intent para llamar a la actividad DisplayImages
                    Intent intentDisplay = new Intent(context, DisplayImages.class);
                    Activity a = (Activity) context;
                    a.startActivity(intentDisplay);
                //Si NO ha introducido el usuario o la contrasena correctos
                }else{
                    //Mensaje "Usuario o Password incorrecto!!"
                    Toast toast1 = Toast.makeText(this.context,"Usuario o Password incorrecto!!", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                break;

            case R.id.siBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton SI");
                datos = OperacionesBaseDatos.obtenerInstancia(this.context);
                mDisplayImages = (DisplayImages) this.context;

                //Toast me gusta
                Toast.makeText(this.context,"ME GUSTA", Toast.LENGTH_SHORT).show();

                //Cargar siguiente imagen
                mDisplayImages.nextImgae();

                favorito = new Favoritos(UsuarioActivo.getUsuarioactivo(),mDisplayImages.idImagen);
                //Borro usuario imagen en caso de que exista en tabla favoritos
                datos.borrarFavorito(favorito);
                //Inserto id de imagen en la bd
                datos.insertarFavorito(favorito);

                //Logeo contenido tabla favoritos
                datos.mostrarContenidoTabla("FAVORITOS");

                break;

            case R.id.noBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton NO");
                datos = OperacionesBaseDatos.obtenerInstancia(this.context);
                mDisplayImages = (DisplayImages) context;
                //Borro usuario imagen en caso de que exista en tabla favoritos
                favorito = new Favoritos(UsuarioActivo.getUsuarioactivo(),mDisplayImages.idImagen);
                datos.borrarFavorito(favorito);

                //Toast me gusta
                Toast.makeText(this.context,"NO ME GUSTA", Toast.LENGTH_SHORT).show();


                mDisplayImages.nextImgae();

                datos.mostrarContenidoTabla("FAVORITOS");
                break;

            case R.id.borrarBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton BORRAR");
                mMainActivity = (MainActivity) this.context;
                mMainActivity.borrarFormulario();

                break;

            case R.id.favoritosBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton favoritos");
                Intent intent2 = new Intent(context, FavoritosActivity.class);
                Activity a = (Activity) context;
                a.startActivity(intent2);

                break;

            case R.id.volverBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton volver");
                a = (Activity) context;
                a.finish();

                break;
        }
    }
}