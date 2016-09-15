package com.symbel.appejerciciopractico4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.symbel.appejerciciopractico4.model.Recados;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Llamada a descargaTareas pasando url como parametro
        Log.d(getClass().getCanonicalName(), "Servicio iniciado");
        List<String[]> l_tareas = null;
        DescargaTareas descargaTareas = new DescargaTareas();
        try {
            l_tareas = descargaTareas.execute(MainActivity.mURL).get();
            stopSelf();//finalizo
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.d(getClass().getCanonicalName(), l_tareas.toString());



        return Service.START_REDELIVER_INTENT; //Si el servicio se destruye se reinicia el intent original
    }
}
