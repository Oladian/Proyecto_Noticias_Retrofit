package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;

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
                .baseUrl("http://192.168.1.36:3000/")
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

        api.postNoticias(noticia).enqueue(new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                callbackNoticia.onPostNoticia();
            }

            @Override
            public void onFailure(Call<Noticia> call, Throwable t) {
                callbackNoticia.onNoticiaError(t.getMessage());
            }
        });
    }


    public void getNoticias(final CallbackNoticias callbackNoticias) {
        api.getNoticias().enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                List<Noticia> listaNoticias = response.body();
                callbackNoticias.onNoticias(listaNoticias);
            }
            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                callbackNoticias.onNoticiasError(t.getMessage());
            }
        });
    }

    public void deleteNoticia(Noticia noticia, final CallbackDeleteNoticia callbackDeleteNoticia){
        api.deleteNoticia(noticia.getId()).enqueue(new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                callbackDeleteNoticia.onDeleteNoticia();
            }

            @Override
            public void onFailure(Call<Noticia> call, Throwable t) {
                callbackDeleteNoticia.onDeleteNoticia();
            }
        });
    }


    public interface CallbackNoticia {
        void onPostNoticia();
        void onNoticiaError(String mensajeError);
    }

    public interface CallbackNoticias {
        void onNoticias(List<Noticia> noticias);
        void onNoticiasError(String mensajeError);
    }

    public interface CallbackDeleteNoticia{
        void onDeleteNoticia();
        void onDeleteNoticiaError();
    }
}
