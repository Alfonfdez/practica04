package com.afr.medicdatafragments.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.afr.medicdatafragments.R;
import com.afr.medicdatafragments.fragments.AFragmentForm;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("PRUEBA", "1º - onCreate de MainActivity");

        //Necesario para que la Activity siempre aparezca con un Fragment, siempre por defecto.
        // A parecerá por defecto el Formulario de entrada de datos -> 'AFragmentForm'
        if(savedInstanceState == null){
            Log.d("PRUEBA", "2º - onCreate de MainActivity: dentro de 'savedInstanceState'");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.destino, new AFragmentForm());
            fragmentTransaction.commit();
        }

    }

}
