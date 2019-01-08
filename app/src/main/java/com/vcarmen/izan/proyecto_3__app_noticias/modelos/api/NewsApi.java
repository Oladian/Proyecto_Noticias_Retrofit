package com.vcarmen.izan.proyecto_3__app_noticias.modelos.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;

import java.io.StringReader;
import java.util.List;

/**
 * Created by luis on 9/12/17.
 */

public class NewsApi {

    private String ENDPOINT_TOP = "https://newsapi.org/v2/top-headlines?sources=%s&apiKey=%s";
    private String ENDPOINT_TODO = "https://newsapi.org/v2/everything?sources=%s&apiKey=%s";
    private String SOURCE = "marca";
    private String APIKEY = "3d9a4eb2e5ba4156ad2f38714a1337fc";
    private String url;
    public NewsApi() {
        this.url = String.format(ENDPOINT_TODO, SOURCE, APIKEY);
    }

    /**
     * Hace una consulta GET a url y parsea y devuelve mediante un callback la lista
     * de las últimas noticias deportivas del diario digital Marca.
     * <p>
     * Se hace uso de la librería Volley y Gson
     *
     * @param ctx       Contexto necesario para Volley
     * @param respuesta Cuando se ha obtenido y procesado la lista se llama al callback
     */
    public void ultimasNoticias(Context ctx, final Callback respuesta) {

        //Se obtiene la cola de peticiones. Sólo debe existir una instancia de Volley
        //para asegurar correcto funcionamiento de la cache.
        RequestQueue req = VolleySingleton.getInstance(ctx).getRequestQueue();

        ///Se hace una petición GET a la url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuesta", response);
                        //Parseo la respuesta y me quedo con el atributo articles que contiene el array de noticias
                        JsonParser parser = new JsonParser();
                        JsonElement e = parser.parse(response);
                        JsonElement articulos = e.getAsJsonObject().get("articles");
                        Log.i("Articulos", articulos.toString());

                        //Uso Gson para deserializar el array automaticamente
                        List<Noticia> noticias = new Gson().fromJson(new StringReader(articulos.toString()), new TypeToken<List<Noticia>>() {
                        }.getType());

                        //Llamo al callback para pasar la lista de noticias
                        respuesta.getLista(noticias);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        //Se añade la petición creada a la cola
        req.add(stringRequest);
    }

    /**
     * Interfaz para el callback de la consulta GET
     */
    public interface Callback {
        void getLista(List<Noticia> noticias);
    }

}
