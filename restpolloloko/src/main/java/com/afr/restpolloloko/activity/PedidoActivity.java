package com.afr.restpolloloko.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.LineaPedido;
import com.afr.restpolloloko.model.Pedido;
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

public class PedidoActivity extends AppCompatActivity {

    private TextView textViewPedido;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Log.d("DEBUG","Entramos en PedidoActivity");

        textViewPedido = (TextView) findViewById(R.id.id_textView_pedido);

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

        getPedidos();
    }

    public void getPedidos(){

        Call<List<Pedido>> call = jsonPlaceHolderApi.getPedidos();

        call.enqueue(new Callback<List<Pedido>>(){

            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response){

                if(!response.isSuccessful()){
                    textViewPedido.setText("Code: "+ response.code());
                    return;
                }

                List<Pedido> pedidos = response.body();
                //List<LineaPedido> lineaPedidos = response.body();

                for(Pedido pedido: pedidos){
                    String content = "";
                    content += "Id: " + pedido.getId() + "\n";
                    content += "Fecha: " + pedido.getFecha() + "\n";
                    content += "Mesa: " + pedido.getMesa() + "\n";

                    content += "Camarero codigo: " + pedido.getCamarero().getCodigo() + "\n";
                    content += "Camarero nombre: " + pedido.getCamarero().getNombre() + "\n";

                    /*for(LineaPedido lineaPedido : lineasPedidos){
                        String contentLineasPedido = "";

                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";
                        contentLineasPedido+= "Producto codigo: " + lineaPedido.getProducto().getCodigo() + "\n";

                        contentLineasPedido+= "LineaPedido cantidad: " + lineaPedido.getCantidad() + "\n";
                        contentLineasPedido+= "LineaPedido precio: " + lineaPedido.getPrecio() + "\n";

                    }*/

                    //textViewPedido.append(content).append(contentLineasPedido);
                }

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t){

                //textViewPedido.setText(t.getMessage());
                textViewPedido.setText(t.getCause().toString());
            }
        });
    }
}
