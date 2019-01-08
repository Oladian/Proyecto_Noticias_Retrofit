package com.vcarmen.izan.proyecto_3__app_noticias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vcarmen.izan.proyecto_3__app_noticias.adaptadores.NoticiasAdapter;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.api.NewsApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticiasActivity extends AppCompatActivity {

    public static final String EXTRA_NOTICIA = "EXTRA_NOTICIA";

    @BindView(R.id.noticias)
    ListView listViewNoticias;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<Noticia> listaNoticias;
    private NoticiasAdapter noticiasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        cargaDatos();
        setLayout();
    }

    /**
     * Configuración de la vista
     */
    private void setLayout() {
        //Establece la toolbar de la libreria de soporte
       // setSupportActionBar(toolbar);

        //Evento click en item de la lista
        listViewNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se lanza la Activity de detalles de la noticia
                Intent intent = new Intent(NoticiasActivity.this, NoticiasDetalleActivity.class);
                intent.putExtra(EXTRA_NOTICIA, listaNoticias.get(i));
                startActivity(intent);
            }
        });
    }

    /**
     * Obtiene una lista de noticias usando la API pública newsapi.org
     * <p>
     * Se usa un callback para recibir la lista.
     */
    private void cargaDatos() {
        NewsApi api = new NewsApi();
        api.ultimasNoticias(this, new NewsApi.Callback() {
            @Override
            public void getLista(List<Noticia> noticias) {
                listaNoticias = noticias;
                //Se instancia adaptador
                noticiasAdapter = new NoticiasAdapter(NoticiasActivity.this, listaNoticias);
                listViewNoticias.setAdapter(noticiasAdapter);
            }
        });

    }




}
