package com.afr.gestionmultas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afr.gestionmultas.model.Agente;
import com.afr.gestionmultas.model.Multa;
import com.afr.gestionmultas.services.MultaServices;
import com.afr.gestionmultas.services.impl.MultaServicesImpl;

import java.util.Date;
import java.util.List;

public class MultaActivity extends AppCompatActivity {

    //I - Declaramos las variables
    /*private List<Multa> multas;*/

    private MultaServices multaServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multa);

        //multas = MultaServicesImpl.getInstance().getAll();
        multaServices = MultaServicesImpl.getInstance();

    }

    public void enviar(View view){
        //Comprobar si entramos

        Log.d("******","ENTRAMOS EN ENVIAR");

        RadioButton radioAgente = (RadioButton) findViewById(R.id.id_agentes);
        EditText editMotivo = (EditText) findViewById(R.id.id_motivo);
        RadioButton radioAceptada = (RadioButton) findViewById(R.id.id_aceptada);
        EditText editObservaciones = (EditText) findViewById(R.id.id_observaciones);
        EditText editImporte = (EditText) findViewById(R.id.id_importe);
        RadioButton radioTipo = (RadioButton) findViewById(R.id.id_tipo);

        Agente agente = new Agente();
        String motivo = editMotivo.getText().toString();
        String observaciones = editObservaciones.getText().toString();
        double importe = Double.parseDouble(editImporte.getText().toString());


        // 1) Vamos a instanciar una lectura
        Multa multa = new Multa(new Date(), agente, motivo, aceptada, observaciones, importe, tipo);

        // 2) Vamos a persistir una lectura
        multaServices.create(multa);

        // 3) Vamos a instanciar un 'intent'
        Intent intent = new Intent(this, MainActivity.class);

        // 4) Vamos a cambiar de 'activity'
        startActivity(intent);

    }
}
