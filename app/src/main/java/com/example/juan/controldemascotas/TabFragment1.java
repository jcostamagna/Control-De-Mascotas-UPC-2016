package com.example.juan.controldemascotas;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;

import java.util.List;

public class TabFragment1 extends Fragment {

    ListView list;

    List<Mascota> mascotas;
    private DBHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        //setContentView(R.layout.list);

        this.db = new DBHandler(getContext().getApplicationContext());
        mascotas = db.getAllMascotas();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialice();


    }

    private void initialice(){
        CustomListAdapter adapter = new CustomListAdapter(this.getActivity(), mascotas);
        list=(ListView)getView().findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), MascotaActivity.class);
                intent.putExtra("id", mascotas.get(position).getIdMascota());
                startActivityForResult(intent, 0);
                //startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MascotaActivity.class);
                intent.putExtra("id", -1);
                startActivityForResult(intent, 0);
                //startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        mascotas = db.getAllMascotas();
        initialice();
    }

    public void setDb(DBHandler db) {
        //this.db = db;
    }
}