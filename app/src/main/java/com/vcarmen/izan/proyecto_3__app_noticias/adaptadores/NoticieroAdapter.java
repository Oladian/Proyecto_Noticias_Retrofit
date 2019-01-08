package com.vcarmen.izan.proyecto_3__app_noticias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcarmen.izan.proyecto_3__app_noticias.R;
import com.vcarmen.izan.proyecto_3__app_noticias.modelos.Noticiero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticieroAdapter extends BaseAdapter {

    private Context context;
    private List<Noticiero> noticieros;

    public NoticieroAdapter(Context context, List<Noticiero> noticieros) {
        this.context = context;
        this.noticieros = noticieros;
    }

    @Override
    public int getCount() {
        return noticieros.size();
    }

    @Override
    public Object getItem(int position) {
        return noticieros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Noticiero noticiero = noticieros.get(position);

        ViewHolder holder;

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items_noticieros, parent, false);
        }

        holder = new ViewHolder(convertView);
        convertView.setTag(holder);

        holder.imagen.setImageResource(noticiero.getImagen());
        holder.noticiero.setText(noticiero.getNombre());

        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.imageView_noticiero)
        ImageView imagen;

        @BindView(R.id.tv_noticiero)
        TextView noticiero;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
