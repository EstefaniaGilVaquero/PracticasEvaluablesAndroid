package com.symbel.appejerciciopractico3.activity;


    import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.KeyEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

    import com.symbel.appejerciciopractico3.R;

public class DetalleActivity extends AppCompatActivity {

    //VIEWS
    ImageView imagenIv;
    TextView nombreTxt,precioTxt, unidadesTxt, descripcionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recibimos los datos del intent
        Intent i=getIntent();

        final String nombre = i.getExtras().getString("Nombre");
        final String precio = i.getExtras().getString("Precio");
        final String unidades = i.getExtras().getString("Unidades");
        final String descripcion = i.getExtras().getString("Descripcion");
        final int imagen = i.getExtras().getInt("Imagen");

        //Referencias a las vistas del xml
        nombreTxt= (TextView) findViewById(R.id.nombreTxt);
        precioTxt= (TextView) findViewById(R.id.precioTxt);
        unidadesTxt= (TextView) findViewById(R.id.unidadesTxt);
        descripcionTxt= (TextView) findViewById(R.id.descripcionTxt);
       // imagenIv= (ImageView) findViewById(R.id.imageIv);

        //Asignamos datos a las vistas

        nombreTxt.setText("Producto :   " + nombre);
        precioTxt.setText("Precio : " + precio);
        unidadesTxt.setText("Unidades : " + unidades);
        descripcionTxt.setText("Descripcion : " + descripcion);
       // imagenIv.setImageResource(imagen);
    }
}

