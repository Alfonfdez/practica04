package com.afr.asynctaskdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText tiempo;
    private TextView resultadoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.idBotonIniciar);
        tiempo = (EditText) findViewById(R.id.idTiempo);
        resultadoFinal = (TextView) findViewById(R.id.idResultadoFinal);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MiAsyncTask miAsyncTask = new MiAsyncTask();

                String sleepTime = tiempo.getText().toString();

                miAsyncTask.execute(sleepTime);
            }
        });


    }

    //Clase interna para definir la AsyncTask
    private class MiAsyncTask extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog;
        private String respuesta;

        @Override
        protected String doInBackground(String... parametros) {

            try{
                //parsear el tiempo
                int tiempo = Integer.parseInt(parametros[0]) * 1000;
                Thread.sleep(tiempo);
                publishProgress("1");

                Thread.sleep(tiempo);
                publishProgress("2");

                Thread.sleep(tiempo);
                publishProgress("3");

                respuesta = "Hemos dormido: "+tiempo/1000+" segundos.";

            }catch (Exception e){
                respuesta = "Algo ha ido mal: "+e.getMessage();
            }

            return respuesta;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,"Esperando","Esperamos " + tiempo.getText().toString() + " segundos");

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            //Quitamos de en medio el PorgessDialog
            progressDialog.dismiss();

            //Ponemos el resultado en un TextView de la 'MainActivity'
            //La "s" recoge el valor que devuelve el método 'doInBackground'
            resultadoFinal.setText(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {

            String mensaje = "Siesta "+values[0]+ " de 3";

            progressDialog.setMessage(mensaje);
        }
    }




}
