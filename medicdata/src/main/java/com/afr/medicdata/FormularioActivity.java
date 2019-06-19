package com.afr.medicdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afr.medicdata.model.Lectura;
import com.afr.medicdata.model.LecturaServices;
import com.afr.medicdata.model.LecturaServicesImpl;

import java.util.Date;

public class FormularioActivity extends AppCompatActivity {

    //I - Declarar variables
    private LecturaServices lecturaServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        lecturaServices = LecturaServicesImpl.getInstance();
    }

    public void enviar(View view){
        //Comprobar si entramos

        Log.d("******","ENTRAMOS EN ENVIAR");

        EditText editPeso = (EditText) findViewById(R.id.idEntradaPeso);
        EditText editDiastolica = (EditText) findViewById(R.id.idEntradaDiastolica);
        EditText editSistolica = (EditText) findViewById(R.id.idEntradaSistolica);

        double peso = Double.parseDouble(editPeso.getText().toString());
        double diastolica = Double.parseDouble(editDiastolica.getText().toString());
        double sistolica = Double.parseDouble(editSistolica.getText().toString());

        // 1) Vamos a instanciar una lectura
        Lectura lectura = new Lectura(new Date(), new Date(), peso, diastolica, sistolica);

        // 2) Vamos a persistir una lectura
        lecturaServices.create(lectura);

        // 3) Vamos a instanciar un 'intent'
        Intent intent = new Intent(this, MainActivity.class);

        // 4) Vamos a cambiar de 'activity'
        startActivity(intent);
    }
}
