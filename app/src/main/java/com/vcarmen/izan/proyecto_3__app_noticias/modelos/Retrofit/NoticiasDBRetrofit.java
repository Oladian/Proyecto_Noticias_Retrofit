package com.vcarmen.izan.proyecto_3__app_noticias.modelos.Retrofit;

import android.util.Log;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.ApiConfig;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.DataSource;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.NoticiasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiasDBRetrofit {
    private static NoticiasDBRetrofit instance;
    private NoticiasAPI api;

    private NoticiasDBRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.APIURLBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(NoticiasAPI.class);
    }

    public static NoticiasDBRetrofit getInstance(){
        if (instance==null){
            instance = new NoticiasDBRetrofit();
        }
        return instance;
    }

    public void getNoticias(String source, final DataSource.NoticiasCallback noticiasCallback){

        Call<NoticiasResponse> call = api.getNoticias( source, ApiConfig.APIKEY
          //      , 1
        );
        //Log.e("Request", call.request().toString());
        call.enqueue(new Callback<NoticiasResponse>() {
            @Override
            public void onResponse(Call<NoticiasResponse> call, Response<NoticiasResponse> response) {
                NoticiasResponse noticiasResponse = response.body();
                List<Noticia> noticias = noticiasResponse.getNoticias();
                noticiasCallback.onNoticiasCargadas(noticias);
            }

            @Override
            public void onFailure(Call<NoticiasResponse> call, Throwable t) {
                Log.e("Error descarga Noticias", t.getMessage());
                noticiasCallback.onNoticiasError();
            }
        });

    }
}
