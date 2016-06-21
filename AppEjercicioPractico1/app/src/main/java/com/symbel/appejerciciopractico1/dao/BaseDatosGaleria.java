package com.symbel.appejerciciopractico1.dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.symbel.appejerciciopractico1.dto.Usuarios;

/**
 * Created by estefi on 12/06/2016.
 */
public class BaseDatosGaleria extends SQLiteOpenHelper {

    private final String sqlCreacionTablaUsuarios = "CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY, USUARIO TEXT, PASSWORD INTEGER)";
    private final String sqlCreacionTablaFavoritos = "CREATE TABLE FAVORITOS (USUARIO STRING PRIMARY KEY, IMAGE STRING PRIMARY KEY, FOREIGN KEY (USUARIO) REFERENCES USUARIOS (ID))";

    public BaseDatosGaleria(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //En caso de que al constructor le pasemos un número de versión distinto a
        // la base de datos existente, este métdo es invocado. Esto sería necesario
        //cuando modificamos la estrucutura de la base de datos

        //Aquí, deberíamos
        // 1 - Extraer los datos de la vieja versión y copiarlos a la nueva instancia
        // 2 - Crear la nueva versión
        // 3 - Cargar los datos en las tablas de la nueva versión
    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }

    public void insertarUsuario (Usuarios usuarios)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO USUARIOS (ID, USUARIO, PASSWORD ) VALUES ('"+usuarios.getId()+"' , '"+usuarios.getUsuario()+"', '"+usuarios.getPassword()+"')");
        this.cerrarBaseDatos(database);
    }

    public boolean validarUsuarioPassword(String usuario, int password){
        boolean usuarioOK = false;

        String consulta = "SELECT COUNT(*) FROM USUARIOS WHERE USUARIO ='"+usuario+"' AND PASSWORD = "+ password;

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        //Si ha devuelto registros es que el usuario esta dado de alta.
        if( cursor != null || cursor.getCount() <=0){
            cursor.moveToFirst();
            if (cursor.getInt(0) >= 1){
                usuarioOK = true;
            }
            cursor.close();
        }
        this.cerrarBaseDatos(basedatos);
        return usuarioOK;
    }

    public void setLikeDislike(String usuario, String imagen){
        String consulta = "INSERT INTO FAVORITOS ( USUARIO, IMAGEN ) VALUES( '"+usuario+"',"+imagen ;
        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);

    }

    public boolean tableIsEmpty(String tableName){

        boolean tablaVacia = false;
        int numReg = 0;

        String consulta = "SELECT COUNT(*) FROM "+tableName;
        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);
        if( cursor != null || cursor.getCount() <=0){
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0){
                tablaVacia = true;
            }
            cursor.close();
        }

        return tablaVacia;

    }

}
