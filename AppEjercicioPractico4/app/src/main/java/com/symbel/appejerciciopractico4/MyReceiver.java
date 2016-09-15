package com.symbel.appejerciciopractico4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Se ejecuta al iniciar el dispositivo
        Log.d(getClass().getCanonicalName(), "Receiver lanzado con el encendido del dispositivo");

        //Iniciamos MainActivity
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
