package com.afr.medicdatafragments.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afr.medicdatafragments.R;
import com.afr.medicdatafragments.model.Lectura;
import com.afr.medicdatafragments.services.LecturaServices;
import com.afr.medicdatafragments.services.impl.LecturaServicesSQLite;

import java.util.Date;

public class AFragmentForm extends Fragment {

    //I - Declarar variables
    private LecturaServices lecturaServices;

    private EditText editPeso;
    private EditText editDiastolica;
    private EditText editSistolica;

    //Constructor vacío requerido explícitamente
    public AFragmentForm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("PRUEBA", "3º - onCreate de AFragmentForm");
        View view = inflater.inflate(R.layout.fragment_a_form, container, false);;

        editPeso = (EditText) view.findViewById(R.id.idEntradaPeso);
        editDiastolica = (EditText) view.findViewById(R.id.idEntradaDiastolica);
        editSistolica = (EditText) view.findViewById(R.id.idEntradaSistolica);

        lecturaServices = new LecturaServicesSQLite(getActivity());


        view.findViewById(R.id.idBotonEnviar).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 1.- Instanciamos objeto Lectura

                double peso = Double.parseDouble(editPeso.getText().toString());
                double diastolica = Double.parseDouble(editDiastolica.getText().toString());
                double sistolica = Double.parseDouble(editSistolica.getText().toString());

                Lectura lectura = new Lectura(new Date(), new Date(), peso, diastolica, sistolica);

                // 2.- Persistimos objeto Lectura
                lecturaServices.create(lectura);

                // 3.- Substituyo el fragmento actual por el de la lista...
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.destino, new BFragmentListView());
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
