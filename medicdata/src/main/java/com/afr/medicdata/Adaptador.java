package com.afr.medicdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afr.medicdata.model.Lectura;
import com.afr.medicdata.model.LecturaServicesImpl;
import com.afr.medicdata.model.LecturaServicesSQLite;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Lectura> lecturas;
    private Context contexto;
    private LecturaServicesSQLite lecturaServicesSQLite;


    public Adaptador(Context contexto){
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        lecturaServicesSQLite = new LecturaServicesSQLite(contexto);
        //lecturas = LecturaServicesImpl.getInstance().getAll();
        //lecturas = LecturaServicesSQLite.getInstance().getAll();
        lecturas = lecturaServicesSQLite.getAll();
    }

    //Métodos implementados de la superclase "BaseAdapter"
    @Override
    public int getCount() {
        return lecturas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.lectura_row, null);

        TextView fecha = (TextView) vista.findViewById(R.id.idFecha);
        TextView hora = (TextView) vista.findViewById(R.id.idHora);
        TextView peso = (TextView) vista.findViewById(R.id.idEntradaPeso);
        TextView diastolica = (TextView) vista.findViewById(R.id.idDiastolica);
        TextView sistolica = (TextView) vista.findViewById(R.id.idSistolica);

        Lectura lectura = lecturas.get(i);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        String strParteFecha = sdf.format(lectura.getFecha());
        String strParteHora = sdf2.format(lectura.getHora());

        fecha.setText(strParteFecha);
        hora.setText(strParteHora);
        peso.setText(String.valueOf(lectura.getPeso()));
        diastolica.setText(String.valueOf(lectura.getDiastolica()));
        sistolica.setText(String.valueOf(lectura.getSistolica()));

        return vista;
    }
}
