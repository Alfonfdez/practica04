package com.afr.medicdata;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afr.medicdata.database.DatabaseHelper;
import com.afr.medicdata.model.Lectura;
import com.afr.medicdata.model.LecturaServices;
import com.afr.medicdata.model.LecturaServicesImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioActivity extends AppCompatActivity {

    //I - Declarar variables
    private LecturaServices lecturaServices;

    private DatabaseHelper myDB;

    private EditText editPeso;
    private EditText editDiastolica;
    private EditText editSistolica;

    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        lecturaServices = LecturaServicesImpl.getInstance();

        // II - Asignar las variables
        editPeso = (EditText) findViewById(R.id.idEntradaPeso);
        editDiastolica = (EditText) findViewById(R.id.idEntradaDiastolica);
        editSistolica = (EditText) findViewById(R.id.idEntradaSistolica);

        button1 = (Button) findViewById(R.id.idBotonEnviar);
        button2 = (Button) findViewById(R.id.idBotonListar);

        myDB = new DatabaseHelper(this);


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Date fecha = new Date();
                Date hora = new Date();

                SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

                String strParteFecha = sdfFecha.format(fecha);
                String strParteHora = sdfHora.format(hora);

                double peso = Double.parseDouble(editPeso.getText().toString());
                double diastolica = Double.parseDouble(editDiastolica.getText().toString());
                double sistolica = Double.parseDouble(editSistolica.getText().toString());

                String queryCompleta = fecha + " " + hora + " " + peso + " " + diastolica + " " + sistolica;
                Toast.makeText(FormularioActivity.this, queryCompleta, Toast.LENGTH_SHORT).show();

                myDB.insertData(strParteFecha, strParteHora, peso, diastolica, sistolica);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Cursor cursor = myDB.getAll();

                // ¿Qué hace un cursor aquí? (no debería, pero va a funcionar)

                //Lo que debería SER
                //List<Amigo> amigos = interface.getAll();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){

                    int codigo = cursor.getInt(0);
                    String fecha = cursor.getString(1);
                    String hora = cursor.getString(2);
                    double peso = cursor.getDouble(3);
                    double diastolica = cursor.getDouble(4);
                    double sistolica = cursor.getDouble(5);

                    String query = codigo + ": " + fecha + " " + hora + " " + peso + " " + diastolica + " " + sistolica;

                    Log.d("DATABASE", query);
                }

            }


        });

    }

    /*public void enviar(View view){
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
    }*/
}
