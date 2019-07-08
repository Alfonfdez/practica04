package com.afr.restpolloloko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afr.restpolloloko.activity.AltaCamareroActivity;
import com.afr.restpolloloko.activity.AltaProductoActivity;
import com.afr.restpolloloko.activity.CamareroActivity;
import com.afr.restpolloloko.activity.PedidoActivity;
import com.afr.restpolloloko.activity.ProductoActivity;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.Camarero;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    //I- Declarar las variables
    private Button botonCamarero;
    private Button botonProducto;
    private Button botonPedido;
    private Button botonAltaCamarero;
    private Button botonAltaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCamarero = (Button) findViewById(R.id.id_button_camarero);
        botonProducto = (Button) findViewById(R.id.id_button_producto);
        botonPedido = (Button) findViewById(R.id.id_button_pedido);
        botonAltaCamarero = (Button) findViewById(R.id.id_button_alta_camarero);
        botonAltaProducto = (Button) findViewById(R.id.id_button_alta_producto);

        botonCamarero.setOnClickListener(this);
        botonProducto.setOnClickListener(this);
        botonPedido.setOnClickListener(this);
        botonAltaCamarero.setOnClickListener(this);
        botonAltaProducto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String tag = v.getTag().toString();

        Log.d("DEBUG", tag);

        switch(tag){
            case "1":

                Intent intentCamarero = new Intent(this, CamareroActivity.class);

                startActivity(intentCamarero);

                break;

            case "2":

                Intent intentProducto = new Intent(this, ProductoActivity.class);

                startActivity(intentProducto);

                break;

            case "3":

                Intent intentPedido = new Intent(this, PedidoActivity.class);

                startActivity(intentPedido);

                break;

            case "4":

                Intent intentAltaCamarero = new Intent(this, AltaCamareroActivity.class);

                startActivity(intentAltaCamarero);

                break;

            case "5":

                Intent intentAltaProducto = new Intent(this, AltaProductoActivity.class);

                startActivity(intentAltaProducto);

                break;
        }
    }
}
