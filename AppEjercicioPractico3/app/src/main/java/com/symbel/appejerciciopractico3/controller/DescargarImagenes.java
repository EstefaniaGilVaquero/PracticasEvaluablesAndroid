package com.symbel.appejerciciopractico3.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DescargarImagenes extends AsyncTask<Void, Void, Bitmap> {

    private String url;

    public DescargarImagenes(String url) {
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap myBitmap = null;
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
           // return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
    }

}