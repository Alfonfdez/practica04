package com.afr.medicdatafragments.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.afr.medicdatafragments.R;
import com.afr.medicdatafragments.adapters.LecturasAdapter;

public class BFragmentListView extends Fragment {

    //I - Declarar variables
    ListView lista;

    //Constructor vacío requerido explícitamente
    public BFragmentListView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b_list_view, container, false);

        lista = (ListView) view.findViewById(R.id.idLista);
        lista.setAdapter(new LecturasAdapter(getActivity()));

        return view;
    }

}
