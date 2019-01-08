package com.vcarmen.izan.proyecto_3__app_noticias;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticiero;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        ft.add(R.id.contenedor, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
