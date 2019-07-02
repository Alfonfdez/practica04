package com.afr.medicdatafragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afr.medicdatafragments.model.Lectura;
import com.afr.medicdatafragments.model.LecturaServices;
import com.afr.medicdatafragments.model.LecturaServicesSQLite;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adaptador extends BaseAdapter {

    //Atributos
    private SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

    private LayoutInflater inflater = null;
    private List<Lectura> lecturas;
    private Context contexto;

    //Constructor
    public Adaptador(Context contexto){
        Log.d("DATABASE","En constructor de Adaptador");

        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        LecturaServices lecturaServices = new LecturaServicesSQLite(contexto);

        lecturas = lecturaServices.getAll();

        Log.d("DATABASE", "Ya tenemos lecturas en el Adaptador");
    }

    //Métodos implementados de la superclase "BaseAdapter"
    @Override
    public int getCount() {
        return lecturas.size();
    }

    @Override
    public Object getItem(int position) {
        return lecturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lecturas.get(position).getCodigo();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        Log.d("*DATABASE", "Dentro del getView...");

        final View vista = inflater.inflate(R.layout.lectura_row, null);

        TextView fecha = (TextView) vista.findViewById(R.id.idFecha);
        TextView hora = (TextView) vista.findViewById(R.id.idHora);
        TextView peso = (TextView) vista.findViewById(R.id.idEntradaPeso);
        TextView diastolica = (TextView) vista.findViewById(R.id.idDiastolica);
        TextView sistolica = (TextView) vista.findViewById(R.id.idSistolica);

        Lectura lectura = lecturas.get(i);


        String strParteFecha = sdfFecha.format(lectura.getFecha());
        String strParteHora = sdfHora.format(lectura.getHora());

        fecha.setText(strParteFecha);
        hora.setText(strParteHora);
        peso.setText(String.valueOf(lectura.getPeso()));
        diastolica.setText(String.valueOf(lectura.getDiastolica()));
        sistolica.setText(String.valueOf(lectura.getSistolica()));

        return vista;
    }
}