package com.afr.restcountryflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.afr.restcountryflags.adapters.CountriesAdapter;
import com.afr.restcountryflags.model.Country;
import com.afr.restcountryflags.retrofit.ApiRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //I - Declarar variables
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //II - Asignar variables
        listView = (ListView) findViewById(R.id.idListView);

        //Configuración librería 'Retrofit'
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Interface
        ApiRest apiRest = retrofit.create(ApiRest.class);

        Call<List<Country>> call = apiRest.getAll();

        //'Callback' es un interface
        call.enqueue(new Callback<List<Country>>() {


            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                //Si no tenemos respuesta
                if(!response.isSuccessful()){
                    Log.d("DEBUG", "code: " + response.code());
                    return;
                }

                //Obtengo los datos
                List<Country> countries = response.body();

                //Instanciar un 'Adapter' y asignarlo a nuestro 'ListView'
                CountriesAdapter countriesAdapter = new CountriesAdapter(countries, MainActivity.this);

                listView.setAdapter(countriesAdapter);

            }

            //Método sobreescrito para manejar errores
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }

        });



    }





}
