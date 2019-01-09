package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import android.util.Log;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.NoticiasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticasFavoritosRetrofit {

    private NoticiasFavoritosAPI api;
    private static NoticasFavoritosRetrofit instance;

    private NoticasFavoritosRetrofit() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.16.131:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(NoticiasFavoritosAPI.class);
    }

    public static NoticasFavoritosRetrofit getInstance() {
        if (instance == null) {
            instance = new NoticasFavoritosRetrofit();
        }
        return instance;
    }

    public void postNoticias(Noticia noticia, final CallbackNoticia callbackNoticia) {
        api.postNoticias(noticia).enqueue(new Callback<NoticiasResponse>() {
            @Override
            public void onResponse(Call<NoticiasResponse> call, Response<NoticiasResponse> response) {
                callbackNoticia.onNoticia();
            }

            @Override
            public void onFailure(Call<NoticiasResponse> call, Throwable t) {

            }
        });
    }


    public void getNoticias(final CallbackNoticias callbackNoticias) {
        api.getNoticias().enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                List<Noticia> listaNoticias = response.body();
                Log.e("Size noticias", listaNoticias.size()+"");
                callbackNoticias.onNoticias(listaNoticias);
            }
            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callbackNoticias.onNoticiasError(t.getMessage());
            }
        });
    }

    public interface CallbackNoticia {
        void onNoticia();
        void onNoticiaError(String mensajeError);
    }

    public interface CallbackNoticias {
        void onNoticias(List<Noticia> noticias);
        void onNoticiasError(String mensajeError);
    }
}
