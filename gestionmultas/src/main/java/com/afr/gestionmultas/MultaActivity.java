package com.afr.gestionmultas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afr.gestionmultas.model.Multa;
import com.afr.gestionmultas.services.impl.MultaServicesImpl;

import java.util.List;

public class MultaActivity extends AppCompatActivity {

    //I - Declaramos las variables
    private List<Multa> multas;

    private RadioGroup radioGroupAgente;
    private RadioGroup radioGroupAceptada;
    private RadioGroup radioGroupTipo;

    private RadioButton radioButtonAgente;
    private RadioButton radioButtonAceptada;
    private RadioButton radioButtonTipo;




    private EditText editTextMotivo;
    private EditText editTextObservacion;
    private EditText editTextImporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multa);

        multas = MultaServicesImpl.getInstance().getAll();

        String motivo = editTextMotivo.getText().toString();
        String observacion = editTextObservacion.getText().toString();
        double importe = Double.parseDouble(editTextImporte.getText().toString());

    }
}
