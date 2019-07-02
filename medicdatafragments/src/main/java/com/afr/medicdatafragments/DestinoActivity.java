package com.afr.medicdatafragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afr.medicdatafragments.fragments.AFragmentForm;
import com.afr.medicdatafragments.fragments.BFragmentListView;
import com.afr.medicdatafragments.fragments.CFragmentUserForm;
import com.afr.medicdatafragments.fragments.MenuPulsado;

public class DestinoActivity extends AppCompatActivity implements MenuPulsado {

    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);

        fragments = new Fragment[3];

        fragments[0] = new AFragmentForm();
        fragments[1] = new BFragmentListView();
        fragments[2] = new CFragmentUserForm();

        Bundle extras = getIntent().getExtras();

        // Aquí llega la información de boton_pulsado 0, 1 o 2
        // Método 'menu' a implementar del interface 'ComunicaMenu'
        menuSeleccionado(extras.getInt("BOTON_PULSADO"));

    }



    @Override
    public void menuSeleccionado(int botonPulsado) {
        //Transacción -> carácter atómico: o funciona todas las cosas 100%, o no funciona nada
        FragmentManager fragmentManager = getFragmentManager(); // Ojo importarlo bien!

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // nos pide
        // 1. identificador del contenedor...
        // 2. el fragmento que queremos cargar... hay tres posibilidades.

        fragmentTransaction.replace(R.id.destino, fragments[botonPulsado]);
        fragmentTransaction.commit();
    }
}
