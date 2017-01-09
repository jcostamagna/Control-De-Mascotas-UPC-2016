package com.example.juan.controldemascotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Mascota> {

    private final Activity context;
    private final List<Mascota> mascotas;
    //private final String[] itemname;
    //private final Integer[] imgid;

    public CustomListAdapter(Activity context,List<Mascota> mascotas) {
        super(context, R.layout.list, mascotas);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.mascotas = mascotas;
        //this.itemname=itemname;
        //this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(mascotas.get(position).getNombre());
        imageView.setImageResource(mascotas.get(position).getImgId());
        extratxt.setText(mascotas.get(position).getTipo()+" "+mascotas.get(position).getDataNac());
        return rowView;

    };
}