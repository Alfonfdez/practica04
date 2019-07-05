package com.afr.restpolloloko.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.Camarero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CamareroActivity extends AppCompatActivity {

    private TextView textViewCamarero;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarero);

        Log.d("DEBUG","Entramos en CamareroActivity");

        textViewCamarero = (TextView) findViewById(R.id.id_textView_camarero);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pedi-gest.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getCamareros();
    }

    public void getCamareros(){

            Call<List<Camarero>> call = jsonPlaceHolderApi.getCamareros();

                call.enqueue(new Callback<List<Camarero>>(){

                    @Override
                    public void onResponse(Call<List<Camarero>> call, Response<List<Camarero>> response){

                        if(!response.isSuccessful()){
                            textViewCamarero.setText("Code: "+ response.code());
                            return;
                        }

                        List<Camarero> camareros = response.body();

                        for(Camarero camarero: camareros){
                            String content = "";
                            content += "Codigo: " + camarero.getCodigo() + "\n";
                            content += "Nombre: " + camarero.getNombre() + "\n\n";

                            textViewCamarero.append(content);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Camarero>> call, Throwable t){
                        textViewCamarero.setText(t.getMessage());
                    }
                });
    }
}
