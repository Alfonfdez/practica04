package com.afr.medicdatafragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.afr.medicdatafragments.fragments.MenuPulsado;
import com.afr.medicdatafragments.model.Lectura;
import com.afr.medicdatafragments.model.LecturaServices;
import com.afr.medicdatafragments.model.LecturaServicesSQLite;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements MenuPulsado {

    //A - Formulario - Declarar variables
    private LecturaServices lecturaServices;

    private EditText editPeso;
    private EditText editDiastolica;
    private EditText editSistolica;

    //B - Lista - Declarar variables
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //A - Formulario
        lecturaServices = new LecturaServicesSQLite(this);

        //B - Lista
        /*Log.d("DATABASE","En onCreate de ListActivity");

        //II - Instanciar variables
        lista = (ListView) findViewById(R.id.idLista);

        Adaptador adaptador = new Adaptador(this);

        lista.setAdapter(adaptador);*/
    }

    //Método implementado de la interfaz 'MenuPulsado'
    @Override
    public void menuSeleccionado(int botonPulsado) {
        Intent intent = new Intent(this, DestinoActivity.class);

        //Pasaremos la información de qué botón ha sido pulsado: 1 o 2 o 3
        intent.putExtra("BOTON_PULSADO", botonPulsado);
        startActivity(intent);
    }

    //B - Lista
    /*public void enviarFormulario(View view){
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
    }*/

}
