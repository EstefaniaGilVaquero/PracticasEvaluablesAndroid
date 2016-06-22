package com.symbel.appejerciciopractico1.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by estefi on 12/06/2016.
 */
public class BaseDatosGaleria extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "galeria.db";

    private static final int VERSION_ACTUAL = 4;

    private final Context contexto;

    private final String sqlCreacionTablaUsuarios = "CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY, USUARIO TEXT, PASSWORD INTEGER)";
    private final String sqlCreacionTablaFavoritos = "CREATE TABLE FAVORITOS (USUARIO STRING, IMAGEN INTEGER REFERENCES USUARIOS(ID), PRIMARY KEY(USUARIO, IMAGEN))";


    public BaseDatosGaleria(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaUsuarios);
        db.execSQL(sqlCreacionTablaFavoritos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FAVORITOS");
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");


        onCreate(db);
    }
}
