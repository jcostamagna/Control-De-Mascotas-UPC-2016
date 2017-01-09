package com.example.juan.controldemascotas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class DummyFragment extends Fragment {

    private EditText nombre, tipo, dataNac,dataVac, dataVet;
    private CheckBox especial;
    private Mascota mascota;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_dummy,container,false);
        nombre = (EditText) view.findViewById(R.id.nombreInput);
        nombre.setText(mascota.getNombre());
        nombre.setEnabled(mascota.getNombre() == null);

        tipo = (EditText) view.findViewById(R.id.tipoInput);
        tipo.setText(mascota.getTipo());
        tipo.setEnabled(mascota.getTipo() == null);

        dataNac = (EditText) view.findViewById(R.id.dataNacInput);
        dataNac.setText(mascota.getDataNac());
        dataNac.setEnabled(mascota.getDataNac() == null);

        dataVac = (EditText) view.findViewById(R.id.DataVacuna);
        dataVac.setText(mascota.getDataVacunacion());

        dataVet = (EditText) view.findViewById(R.id.CitaVeterinario);
        dataVet.setText(mascota.getCitaVet());

        especial = (CheckBox) view.findViewById(R.id.especial);
        especial.setChecked(mascota.getEspecial());

        return view;
    }



    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setAtributosMascota() {
        mascota.setNombre(this.nombre.getText().toString());
        mascota.setTipo(this.tipo.getText().toString());
        mascota.setDataNac(this.dataNac.getText().toString());
        mascota.setCitaVet(this.dataVet.getText().toString());
        mascota.setDataVacunacion(this.dataVac.getText().toString());
        mascota.setEspecial(this.especial.isChecked());
    }
}

