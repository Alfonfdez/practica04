package com.afr.medicdatafragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afr.medicdatafragments.fragments.MenuPulsado;

public class MainActivity extends AppCompatActivity implements MenuPulsado {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Método implementado de la interfaz 'MenuPulsado'
    @Override
    public void menuSeleccionado(int botonPulsado) {
        Intent intent = new Intent(this, DestinoActivity.class);

        //Pasaremos la información de qué botón ha sido pulsado: 1 o 2 o 3
        intent.putExtra("BOTON_PULSADO", botonPulsado);
        startActivity(intent);
    }

}
