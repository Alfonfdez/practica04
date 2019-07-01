package com.afr.fragmentshelloworld;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.afr.fragmentshelloworld.fragments.ComunicaMenu;


public class MenuFragment extends Fragment {

    private final int[] BOTONES_MENU = {R.id.boton1, R.id.boton2, R.id.boton3};

    //Constructor
    public MenuFragment() {
        // Required empty public constructor
    }

    //Método a implementar
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Se genera la 'view' a partir del xml
        View miMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        // Declaro una variable del tipo ImageButton
        // Vamos a iterar todos y cada uno de los botones y en cada vuelta del 'for' añadiremos un 'Listener' al botón de turno (botónMenu)
        ImageButton botonMenu;

        for(int i = 0; i < BOTONES_MENU.length ; i++){

            // almacenamos en botonMenu todos y cada unos de los botones...
            botonMenu = (ImageButton) miMenu.findViewById(BOTONES_MENU[i]);

            final int botonPulsado = i;

            //Clase anónima
            botonMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // Hemos de detectar en que actividad nos encontramos...
                    Activity estaActividad = getActivity();

                    //Hay que enviar la información al interface ComunicaMenu
                    Log.d("**","Pulsamos y enviamos info del botón: " + botonPulsado);

                    //Hacemos un 'upcasting'. Se podría escribir así paso por paso:
                    //ComunicaMenu cm = (ComunicaMenu) estaActividad;
                    //cm.menu(botonPulsado);

                    ((ComunicaMenu) estaActividad).menu(botonPulsado);

                }
            });

        }

        // Inflate the layout for this fragment
        return miMenu;

    }

}
