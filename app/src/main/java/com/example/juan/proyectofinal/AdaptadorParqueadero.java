package com.example.juan.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorParqueadero extends BaseAdapter{

    private Context ctx;
    private List<Parqueadero> lista;

    public AdaptadorParqueadero(Context ctx2, List<Parqueadero> lista2)
    {
        ctx=ctx2;
        lista=lista2;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
        public Parqueadero getItem(int position) {
            return lista.get(position);
        }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)ctx).getLayoutInflater();
            v = inflater.inflate(R.layout.item_lista, null);
        }else{
            v=convertView;
        }

        Parqueadero c = getItem(position);

        TextView itemHorario = (TextView) v.findViewById(R.id.ithorario);
        TextView itemNombre = (TextView) v.findViewById(R.id.itnombres);
        TextView itemModelo = (TextView) v.findViewById(R.id.itmodelo);

        itemHorario.setText(c.getHorario());
        itemNombre.setText(c.getNombre());
        itemModelo.setText(c.getModelo());

        return v;
    }
}
