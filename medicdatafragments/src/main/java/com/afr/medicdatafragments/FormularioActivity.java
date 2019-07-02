package com.afr.medicdatafragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afr.medicdatafragments.model.Lectura;
import com.afr.medicdatafragments.model.LecturaServices;
import com.afr.medicdatafragments.model.LecturaServicesSQLite;

import java.util.Date;

public class FormularioActivity extends AppCompatActivity {

    //I - Declarar variables
    private LecturaServices lecturaServices;

    private EditText editPeso;
    private EditText editDiastolica;
    private EditText editSistolica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_a_form);

        lecturaServices = new LecturaServicesSQLite(this);
    }

    public void enviarFormulario(View view){
        //Comprobar si entramos

        Log.d("DATABASE","ENTRAMOS EN ENVIAR");

        // II - Asignar las variables
        editPeso = (EditText) findViewById(R.id.idEntradaPeso);
        editDiastolica = (EditText) findViewById(R.id.idEntradaDiastolica);
        editSistolica = (EditText) findViewById(R.id.idEntradaSistolica);

        double peso = Double.parseDouble(editPeso.getText().toString());
        double diastolica = Double.parseDouble(editDiastolica.getText().toString());
        double sistolica = Double.parseDouble(editSistolica.getText().toString());

        // 1) Vamos a instanciar una lectura
        Lectura lectura = new Lectura(new Date(), new Date(), peso, diastolica, sistolica);

        // 2) Vamos a persistir una lectura
        lecturaServices.create(lectura);

        // 3) Vamos a instanciar un 'intent'
        //Intent intent = new Intent(this, ListActivity.class);

        // 4) Vamos a cambiar de 'activity'
        //startActivity(intent);
    }
}
