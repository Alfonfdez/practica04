package com.afr.medicdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //I - Declarar variables
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //II - Instanciar variables
        lista = (ListView) findViewById(R.id.idLista);

        Adaptador adaptador = new Adaptador(this);

        lista.setAdapter(adaptador);

    }

}
