package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.NoticiasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NoticiasFavoritosAPI {

    @POST("noticias")
    public Call<NoticiasResponse> postNoticias(@Body Noticia noticia);

    @GET("noticais")
    public Call<List<Noticia>> getNoticias();
}
