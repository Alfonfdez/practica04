package com.afr.restpolloloko.apirest;

import com.afr.restpolloloko.model.Camarero;
import com.afr.restpolloloko.model.LineaPedido;
import com.afr.restpolloloko.model.Pedido;
import com.afr.restpolloloko.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    //Camarero
    @GET("camareros")
    Call<List<Camarero>> getCamareros();
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/camareros

    @GET("camareros/{id}")
    Call<List<Camarero>> getCamarero(@Path("id") int codigo);
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/camareros/100

    /*@POST("camareros/{id}")
    Call<Camarero> createCamarero(@Body Camarero camarero);*/

    @Headers("Content-type: application/json")
    @POST("camareros")
    public Call<Camarero> create(@Body Camarero camarero);


    //***********************************


    //Producto
    @GET("productos")
    Call<List<Producto>> getProductos();
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/productos

    @GET("productos/{id}")
    Call<List<Producto>> getProductoById(@Path("id") int codigo);
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/productos/1

    /*@POST("productos/{id}")
    Call<Producto> createProducto(@Body Producto producto);*/
    @Headers("Content-type: application/json")
    @POST("productos")
    public Call<Producto> create(@Body Producto producto);


    //***********************************


    //Pedido
    @GET("pedidos")
    Call<List<Pedido>> getPedidos();
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/pedidos

    @GET("pedidos/{id}")
    Call<List<Pedido>> getPedidoById(@Path("id") int id);
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/pedidos/1

    @POST("pedidos/{id}")
    Call<Pedido> createPedido(@Body Pedido pedido);


    //***********************************


    //Línea Pedidos
    @GET("lineas")
    Call<List<LineaPedido>> getLineaPedidos();
    //Resuelve la dirección URL: https://pedi-gest.herokuapp.com/api/lineas
}
