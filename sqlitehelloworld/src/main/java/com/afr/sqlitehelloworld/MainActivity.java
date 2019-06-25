package com.afr.sqlitehelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    private DatabaseHelper myDB;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // II - Asignar las variables
        editText1 = (EditText) findViewById(R.id.idTexto1);
        editText2 = (EditText) findViewById(R.id.idTexto2);
        editText3 = (EditText) findViewById(R.id.idTexto3);

        button1 = (Button) findViewById(R.id.idBoton1);
        button2 = (Button) findViewById(R.id.idBoton2);

        myDB = new DatabaseHelper(this);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre = editText1.getText().toString();
                String apellido1 = editText2.getText().toString();
                String apellido2 = editText3.getText().toString();

                String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
                Toast.makeText(MainActivity.this, nombreCompleto, Toast.LENGTH_SHORT).show();

                myDB.insertData(nombre, apellido1, apellido2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}
