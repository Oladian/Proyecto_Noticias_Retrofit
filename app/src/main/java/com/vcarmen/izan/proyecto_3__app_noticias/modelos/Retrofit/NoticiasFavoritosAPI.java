package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NoticiasFavoritosAPI {

    @POST("noticias")
    public Call<Noticia> postNoticias(@Body Noticia noticia);

    @GET("noticias")
    public Call<List<Noticia>> getNoticias();

    @DELETE("noticias/{id}")
    public Call<Noticia> deleteNoticia(@Path("id") String id); // Posiblemente mal
}
