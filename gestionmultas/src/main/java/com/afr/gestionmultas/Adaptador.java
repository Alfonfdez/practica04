package com.afr.gestionmultas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afr.gestionmultas.model.Multa;
import com.afr.gestionmultas.services.MultaServices;
import com.afr.gestionmultas.services.impl.MultaServicesImpl;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Multa> multas;
    //private List<Lectura> lecturas;
    private Context contexto;


    public Adaptador(Context contexto){
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        multas = MultaServicesImpl.getInstance().getAll();
        //lecturas = LecturaServicesImpl.getInstance().getAll();
    }

    //Métodos implementados de la superclase "BaseAdapter"
    @Override
    public int getCount() {
        return multas.size();
    }
    /*public int getCount() {
        return lecturas.size();
    }*/

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

        //Asignamos las variables
        TextView fecha = (TextView) vista.findViewById(R.id.id_tvFecha);
        TextView hora = (TextView) vista.findViewById(R.id.id_tvHora);

        TextView nombre = (TextView) vista.findViewById(R.id.id_tvNombre);
        TextView apellido1 = (TextView) vista.findViewById(R.id.id_tvApellido1);
        TextView apellido2 = (TextView) vista.findViewById(R.id.id_tvApellido2);

        TextView motivo = (TextView) vista.findViewById(R.id.id_tvMotivo);
        TextView importe = (TextView) vista.findViewById(R.id.id_tvImporte);
        TextView aceptada = (TextView) vista.findViewById(R.id.id_tvAceptada);

        TextView tipo = (TextView) vista.findViewById(R.id.id_tvTipo);
        TextView observaciones = (TextView) vista.findViewById(R.id.id_tvObservaciones);


        Multa multa = multas.get(i);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        String strParteFecha = sdf.format(multa.getFechaHora());
        String strParteHora = sdf2.format(multa.getFechaHora());

        fecha.setText(strParteFecha);
        hora.setText(strParteHora);

        nombre.setText(String.valueOf(multa.getAgente().getNombre()));
        apellido1.setText(String.valueOf(multa.getAgente().getApellido1()));
        apellido2.setText(String.valueOf(multa.getAgente().getApellido2()));

        motivo.setText(String.valueOf(multa.getMotivo()));
        importe.setText(String.valueOf(multa.getImporte()));
        aceptada.setText(String.valueOf(multa.isAceptada()));

        tipo.setText(String.valueOf(multa.getTipo()));
        observaciones.setText(String.valueOf(multa.getObservaciones()));

        return vista;
    }
}
