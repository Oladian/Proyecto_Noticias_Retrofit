package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.NoticiasResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasAPI {

    @GET("v2/everything")
    public Call<NoticiasResponse> getNoticias( @Query("sources") String source, @Query("apiKey") String api_key
         //   , @Query("page") int page
    );
}
