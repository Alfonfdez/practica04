package com.afr.restpolloloko.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.LineaPedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstadisticaPedidoActivity extends AppCompatActivity {

    //I - Declaración variables
    private TextView textViewEstadisticaPedido;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TreeMap<String,Integer> estadisticaPedidoTreeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_pedido);

        Log.d("DEBUG","Entramos en EstadisticaPedidoActivity");

        textViewEstadisticaPedido = (TextView) findViewById(R.id.id_textView_estadistica_pedido);

        estadisticaPedidoTreeMap = new TreeMap<>();

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

        getEstadisticaPedidos();
    }

    public void getEstadisticaPedidos(){

        Call<List<LineaPedido>> call = jsonPlaceHolderApi.getLineaPedidos();

        call.enqueue(new Callback<List<LineaPedido>>(){

            @Override
            public void onResponse(Call<List<LineaPedido>> call, Response<List<LineaPedido>> response){

                if(!response.isSuccessful()){
                    textViewEstadisticaPedido.setText("Code: "+ response.code());
                    return;
                }

                List<LineaPedido> lineaPedidos = response.body();


                for(LineaPedido lineaPedido: lineaPedidos){

                    String categoria = lineaPedido.getProducto().getCategoria();

                    if(estadisticaPedidoTreeMap.containsKey(categoria)){
                        Log.d("DEBUG","TreeMap SÍ contiene el 'key'");
                        Log.d("DEBUG", "Hashmap: Key: "+categoria+ " Value: "+estadisticaPedidoTreeMap.get(categoria)+" + "+lineaPedido.getCantidad());
                        estadisticaPedidoTreeMap.put(categoria,estadisticaPedidoTreeMap.get(categoria) + lineaPedido.getCantidad());
                        Log.d("DEBUG", "Hashmap: Key: "+categoria+ " Value: "+estadisticaPedidoTreeMap.get(categoria));
                    }else{
                        estadisticaPedidoTreeMap.put(categoria,lineaPedido.getCantidad());
                        Log.d("DEBUG","TreeMap NO contiene el 'key'");
                        Log.d("DEBUG", "Hashmap: Key: "+categoria+ " Value: "+estadisticaPedidoTreeMap.get(categoria));
                    }

                }

                String content = "";

                Set<Map.Entry<String, Integer>> set = estadisticaPedidoTreeMap.entrySet();

                for(Map.Entry<String,Integer> me : set)
                    content += me.getKey()+": " + me.getValue() + "\n";

                textViewEstadisticaPedido.append(content);
            }

            @Override
            public void onFailure(Call<List<LineaPedido>> call, Throwable t){

                //textViewPedido.setText(t.getMessage());
                textViewEstadisticaPedido.setText(t.getCause().toString());
            }
        });


    }

}
