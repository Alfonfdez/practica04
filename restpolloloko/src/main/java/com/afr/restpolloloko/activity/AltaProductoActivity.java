package com.afr.restpolloloko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afr.restpolloloko.R;
import com.afr.restpolloloko.apirest.JsonPlaceHolderApi;
import com.afr.restpolloloko.model.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaProductoActivity extends AppCompatActivity implements View.OnClickListener{
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Button botonAltaProducto;

    private EditText editTextCodigo;
    private EditText editTextNombre;
    private EditText editTextPrecio;
    private EditText editTextDescripcion;
    private CheckBox checkBoxEsDescatalogado;
    private EditText editTextCategoria;

    private int codigo;
    private String nombre;
    private double precio;
    private String descripcion;
    private Date fechaAlta;
    private boolean esDescatalogado;
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaproducto);

        Log.d("DEBUG","Entramos en AltaProductoActivity");

        editTextCodigo = (EditText) findViewById(R.id.id_editText_producto_codigo);
        editTextNombre = (EditText) findViewById(R.id.id_editText_producto_nombre);
        editTextPrecio = (EditText) findViewById(R.id.id_editText_producto_precio);
        editTextDescripcion = (EditText) findViewById(R.id.id_editText_producto_descripcion);
        checkBoxEsDescatalogado = (CheckBox)  findViewById(R.id.id_checkBox_producto_esDescatalogado);
        editTextCategoria = (EditText) findViewById(R.id.id_editText_producto_categoria);

        botonAltaProducto = (Button) findViewById(R.id.id_button_crear_producto);
        botonAltaProducto.setOnClickListener(this);

        //Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        //Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                long milliseconds = json.getAsJsonPrimitive().getAsLong();
                return new Date(milliseconds);
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                long l = src.getTime();

                return new JsonPrimitive(l);
            }
        });

        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    }

    @Override
    public void onClick(View v) {

        Log.d("DEBUG","Entramos en AltaProductoActivity");

        String tag = v.getTag().toString();
        switch(tag){
            case "1":

                getAltaProducto();

                break;
        }
    }

    public void getAltaProducto(){

        Log.d("DEBUG", "Entramos en getAltaProducto");

        codigo = Integer.parseInt(editTextCodigo.getText().toString());
        nombre = editTextNombre.getText().toString();
        precio = Double.parseDouble(editTextPrecio.getText().toString());
        descripcion = editTextDescripcion.getText().toString();
        fechaAlta = new Date();
        esDescatalogado = checkBoxEsDescatalogado.isChecked();
        categoria = editTextCategoria.getText().toString();

        Producto producto = new Producto(codigo, nombre, precio, descripcion, fechaAlta, esDescatalogado, categoria);
        Log.d("DEBUG", producto.toString());
        Call<Producto> call = jsonPlaceHolderApi.create(producto);

        call.enqueue(new Callback<Producto>(){

            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(!response.isSuccessful()){
                    //textViewAltaCamarero.setText("Code: "+ response.code());
                    //Toast.makeText(getApplicationContext(),"Code: "+ response.code(), Toast.LENGTH_SHORT);
                    Log.d("DEBUG", "Code: "+ response.code());

                    return;
                }

                Log.d("DEBUG", "Entramos en onResponse de getAltaProducto y es 'response' es succesful");

                Intent intentProducto = new Intent(getApplicationContext(), ProductoActivity.class);

                startActivity(intentProducto);

            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                //textViewAltaCamarero.setText(t.getMessage());
                //textViewAltaCamarero.setText(t.getCause().toString());
                //Toast.makeText(getApplicationContext(),t.getCause().toString(), Toast.LENGTH_SHORT);
                Log.d("DEBUG", t.getCause().toString());
            }
        });

    }
}
