package com.vcarmen.izan.proyecto_3__app_noticias;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vcarmen.izan.proyecto_3__app_noticias.Fragmentos.FragmentoDetalleNoticia;
import com.vcarmen.izan.proyecto_3__app_noticias.Fragmentos.FragmentoListaNoticias;
import com.vcarmen.izan.proyecto_3__app_noticias.Fragmentos.FragmentoListaNoticieros;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticia;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticiero;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.OnComunicarFragmentos;

import java.io.Serializable;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnComunicarFragmentos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_favoritos:
                return true;
            case R.id.item_listar_noticias:
                FragmentoListaNoticieros fragmento = new FragmentoListaNoticieros();
                inflarFragmento(fragmento);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inflarFragmento(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contenedor, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void pasarFuente(Noticiero noticiero) {
        FragmentoListaNoticias fragmento = new FragmentoListaNoticias();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NOTICIERO", noticiero);
        fragmento.setArguments(bundle);
        inflarFragmento(fragmento);
    }

    @Override
    public void pasarNoticia(Noticia noticia) {
        FragmentoDetalleNoticia fragmento = new FragmentoDetalleNoticia();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NOTICIA", noticia);
        fragmento.setArguments(bundle);
        inflarFragmento(fragmento);
    }
}
