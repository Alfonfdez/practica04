package com.afr.restcountryflags.retrofit;

import com.afr.restcountryflags.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiRest {

    @GET("all")
    public Call<List<Country>> getAll();    //La web en concreto: https://restcountries.eu/rest/v2/all

    @GET("alpha/{code}")
    public Call<Country> getCountryById(@Path("code") String codigo);  //La web en concreto: https://restcountries.eu/rest/v2/alpha/{code}

}
