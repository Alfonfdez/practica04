package com.afr.restpolloloko.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoActivity extends AppCompatActivity {

    private TextView textViewProducto;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        Log.d("DEBUG","Entramos en ProductoActivity");

        textViewProducto = (TextView) findViewById(R.id.id_textView_producto);

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Log.d("** getAsJsonPrimitive:", json.getAsJsonPrimitive().toString());
                long millisecons = json.getAsJsonPrimitive().getAsLong();
                return new Date(millisecons);
            }
        });

        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getProductos();
    }

    public void getProductos(){

        Log.d("DEBUG","Entramos en getProductos() | ProductoActivity");

        Call<List<Producto>> call = jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<List<Producto>>(){

            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response){

                Log.d("DEBUG","Entramos en el método onResponse | ProductoActivity");

                if(!response.isSuccessful()){

                    Log.d("DEBUG","Fallo en 'response' | ProductoActivity");

                    textViewProducto.setText("Code: "+ response.code());
                    return;
                }

                Log.d("DEBUG","Todo OK, no ha habido fallo en la 'response' | ProductoActivity");

                List<Producto> productos = response.body();

                for(Producto producto: productos){
                    String content = "";
                    content += "Categoria: " + producto.getCategoria() + "\n";
                    content += "Codigo: " + producto.getCodigo() + "\n";
                    content += "Descatalogado: " + producto.isDescatalogado() + "\n";
                    content += "Descripcion: " + producto.getDescripcion() + "\n";
                    content += "Fecha alta: " + producto.getFechaAlta() + "\n";
                    content += "Nombre: " + producto.getNombre() + "\n";
                    content += "Precio: " + producto.getPrecio() + "\n\n";

                    textViewProducto.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t){

                Log.d("DEBUG","Entramos en onFailure por un error | ProductoActivity");

                //textViewProducto.setText(t.getMessage());
                textViewProducto.setText(t.getCause().toString());
            }
        });
    }
}
