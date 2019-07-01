package com.afr.fragmentshelloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afr.fragmentshelloworld.fragments.ComunicaMenu;

public class MainActivity extends AppCompatActivity implements ComunicaMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método implementado de la interface 'ComunicaMenu'
    //Nos iremos a la Activity 'DestActivity' porque nos encontramos en 'MainActivity' aquí.

    @Override
    public void menu(int botonPulsado) {

        Intent intent = new Intent(this, DestActivity.class);

        //Pasaremos la información de qué botón ha sido pulsado: 1 o 2 o 3
        intent.putExtra("BOTON_PULSADO", botonPulsado);

        startActivity(intent);
    }
}
