package com.afr.accesocontactos;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //I- Declarar variables
    private Button btnBuscarContactos;
    private TextView textViewContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscarContactos = (Button) findViewById(R.id.idButtonLeerContactos);
        textViewContactos = (TextView) findViewById(R.id.idTextView);

        //Hacer clic al botón
        btnBuscarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
            }
        });
    }

    private void obtenerDatos(){

        // Contact.Data es la tabla interna donde se guarda la información de contactos

        //Especificamos las columnas de la proyección
        // Si necesitamos otras cosas, tendremos que ir a la documentación

        String[] columnas = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };

        String filtro =
                ContactsContract.Data.MIMETYPE + "= '" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        String orden = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,  //URI de contenido para los contactos
                columnas,                               //Las columnas que nos interesan obtener
                filtro,                                 //La clausula del filtro
                null,                       //No hay parámetros
                orden                                   //Criterio de ordenación
        );

        //Iteraremos el Cursor
        while(cursor.moveToNext()){
            textViewContactos.append("Identificador: " + cursor.getString(0) + "\n");
            textViewContactos.append("Nombre: " + cursor.getString(1) + "\n");
            textViewContactos.append("Teléfono: " + cursor.getString(2) + "\n");
            textViewContactos.append("Tipo de teléfono: " + cursor.getString(3) + "\n\n\n");
        }




    }
}
