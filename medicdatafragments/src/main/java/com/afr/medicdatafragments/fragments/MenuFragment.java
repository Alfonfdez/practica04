package com.afr.medicdatafragments.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.afr.medicdatafragments.R;

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

                    Fragment fragment = null;

                    switch (botonPulsado){
                        case 0: fragment = new AFragmentForm();
                            break;
                        case 1: fragment = new BFragmentListView();
                            break;
                        case 2: fragment = new CFragmentUserForm();
                            break;
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.destino, fragment);
                    fragmentTransaction.commit();
                }
            });

        }

        // Inflate the layout for this fragment
        return miMenu;
    }

}
