package com.afr.restcountryflags;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.afr.restcountryflags.model.Country;
import com.afr.restcountryflags.retrofit.ApiRest;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisorDetalles extends AppCompatActivity {

    //I- Declarar variables
    TextView textViewCountryName;
    TextView textViewCountryAlpha2Code;
    TextView textViewCountryCapital;
    TextView textViewCountryRegion;
    TextView textViewCountrySubregion;
    TextView textViewCountryPopulation;
    TextView textViewCountryBorders;

    ImageView imageViewFlag;

    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        //II-Asignae variables
        textViewCountryName = (TextView) findViewById(R.id.idTextViewName);
        textViewCountryAlpha2Code = (TextView) findViewById(R.id.idTextViewAlphaCode);
        textViewCountryCapital = (TextView) findViewById(R.id.idTextViewCapital);
        textViewCountryRegion = (TextView) findViewById(R.id.idTextViewRegion);
        textViewCountrySubregion = (TextView) findViewById(R.id.idTextViewSubRegion);
        textViewCountryPopulation = (TextView) findViewById(R.id.idTextViewPopulation);
        textViewCountryBorders = (TextView) findViewById(R.id.idTextViewBorders);

        imageViewFlag = (ImageView) findViewById(R.id.idImageViewFlag);

        Intent intent = getIntent();

        //Los datos extras vienen a través del bundle
        Bundle b = intent.getExtras();

        //Si el bundle NO es null
        if(b != null){
            codigo = b.getString("CODIGO");
        }

        //Configuración librería 'Retrofit'
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Interface
        ApiRest apiRest = retrofit.create(ApiRest.class);

        Call<Country> call = apiRest.getCountryById(codigo);

        //'Callback' es un interface
        call.enqueue(new Callback<Country>() {

            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                //Si no tenemos respuesta
                if(!response.isSuccessful()){
                    Log.d("DEBUG", "code: " + response.code());
                    return;
                }

                //Obtengo los datos
                Country country = response.body();

                textViewCountryName.setText(country.getName());
                textViewCountryAlpha2Code.setText(country.getAlpha2Code());
                textViewCountryCapital.setText(country.getCapital());
                textViewCountryRegion.setText(country.getRegion());
                textViewCountrySubregion.setText(country.getSubregion());
                textViewCountryPopulation.setText(Integer.toString(country.getPopulation()));


                String[] borders = country.getBorders();
                int ArraySize = borders.length;
                int i = 0;

                String allBorders = "";

                while(i < ArraySize){
                    allBorders += borders[i] + " ";
                    i++;
                }

                textViewCountryBorders.setText(allBorders);

                String imageURL = "https://www.countryflags.io/" + country.getAlpha2Code() + "/flat/64.png";

                Picasso.get().load(imageURL).placeholder(R.drawable.placeholder).into(imageViewFlag);

            }

            //Método sobreescrito para manejar errores
            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }

        });

    }
}
