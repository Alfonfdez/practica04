package com.afr.restpolloloko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.Camarero;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AltaCamareroActivity extends AppCompatActivity implements  View.OnClickListener{
    private TextView textViewAltaCamarero;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Button botonAltaCamarero;
    private int codigoAleatorioCamarero;
    private String nombreAleatorioCamarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altacamarero);

        Log.d("DEBUG","Entramos en AltaCamareroActivity");

        botonAltaCamarero = (Button) findViewById(R.id.id_button_crear_camarero);
        botonAltaCamarero.setOnClickListener(this);

        textViewAltaCamarero = (TextView) findViewById(R.id.id_textView_altacamarero);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        codigoAleatorioCamarero = (int) (Math.random()*1000000);
        nombreAleatorioCamarero = "nombre"+codigoAleatorioCamarero;

        String content = "";
        content += "Codigo: " + codigoAleatorioCamarero + "\n";
        content += "Nombre: " + nombreAleatorioCamarero + "\n\n";

        textViewAltaCamarero.append(content);

    }

    @Override
    public void onClick(View v) {

        String tag = v.getTag().toString();

        Log.d("DEBUG", tag);

        switch(tag){
            case "1":

                getAltaCamarero();

                break;
        }
    }

    public void getAltaCamarero(){

        Log.d("DEBUG", "Entramos en getAltaCamarero");

        Camarero camarero = new Camarero(codigoAleatorioCamarero,nombreAleatorioCamarero);
        Call<Camarero> call = jsonPlaceHolderApi.create(camarero);

        call.enqueue(new Callback<Camarero>(){

            @Override
            public void onResponse(Call<Camarero> call, Response<Camarero> response) {
                if(!response.isSuccessful()){
                    textViewAltaCamarero.setText("Code: "+ response.code());
                    return;
                }

                Log.d("DEBUG", "Entramos en onResponse de getAltaCamarero y es 'response' es succesful");

                Intent intentCamarero = new Intent(getApplicationContext(), CamareroActivity.class);

                startActivity(intentCamarero);

            }

            @Override
            public void onFailure(Call<Camarero> call, Throwable t) {
                //textViewAltaCamarero.setText(t.getMessage());
                textViewAltaCamarero.setText(t.getCause().toString());
            }
        });

    }
}