package com.symbel.appejerciciopractico1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.symbel.appejerciciopractico1.dao.BaseDatosGaleria;

import java.lang.reflect.Array;

/**
 * Created by estefi on 09/06/2016.
 */
public class escuchaEventos implements View.OnClickListener{

    Context context;
    DisplayImages mDisplayImages;
    MainActivity mMainActivity;
    BaseDatosGaleria mBaseDatosGaleria = new BaseDatosGaleria(context, "MiDB", null, 1);


    public escuchaEventos(Context context){this.context = context;}




    @Override
    public void onClick(View vista_seleccioanda)
    {
        //obtengo el Id de la vista
        int id_vista_seleccionada = vista_seleccioanda.getId();

        Log.d(getClass().getCanonicalName(), "Ha pulsado un boton");

        switch (id_vista_seleccionada)
        {
            case R.id.loginBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton OK de login");
                mMainActivity = (MainActivity) this.context;

                //Validar que se ha introducido usuario y contrasena registrados
                //Si ha introducido usuario y contrasena
                if(mMainActivity.validarUsuarioContrasena()){
                    //Intent para llamar a la actividad DisplayImages
                    Intent intent1 = new Intent(context, DisplayImages.class);
                    Activity a = (Activity) context;
                    //Enviamos el usuario al la siguiente actividad
                    //TODO: Enviar usuario en el intent, no funciona
                    intent1.putExtra("Usuario", mMainActivity.getUsuario());
                    a.startActivity(intent1);
                //Si NO ha introducido el usuario o la contrasena correctos
                }else{
                    //Mensaje "Usuario o Password incorrecto!!"
                    Toast toast1 = Toast.makeText(this.context,"Usuario o Password incorrecto!!", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                break;

            case R.id.siBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton SI");
                //TODO: Guardar en BD la respuesta

             //   String imagen = mDisplayImages.arraySelec.getDrawable(mDisplayImages.contadorImagenes()).toString();

               mBaseDatosGaleria.setLikeDislike(mMainActivity.getUsuario(), "imagen1");

                //Cargar siguiente imagen
                mDisplayImages = (DisplayImages) this.context;
                mDisplayImages.nextImgae();

                break;

            case R.id.noBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton NO");
                //TODO: Guardar en BD la respuesta
                mDisplayImages = (DisplayImages) this.context;
                mDisplayImages.nextImgae();
                break;

            case R.id.borrarBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton BORRAR");
                mMainActivity = (MainActivity) this.context;
                mMainActivity.borrarFormulario();

                break;

            case R.id.favoritosBTN:
                Log.d(getClass().getCanonicalName(), "Ha pulsado boton BORRAR");
                Intent intent2 = new Intent(context, FavoritosActivity.class);
                Activity a = (Activity) context;
                a.startActivity(intent2);

                break;

        }


    }
}