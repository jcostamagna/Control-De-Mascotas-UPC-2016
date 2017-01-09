package com.example.juan.controldemascotas;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class MascotaActivity extends AppCompatActivity{

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View mHeaderLayout;
    private ImageView mImageViewHeader;
    private Fragment fragment;
    private int idMascota;
    private boolean isNew = false;
    private Mascota mascota;
    private ImageButton save, delete, back;
    private DBHandler db;

    private static final String FRAGMENT_TAG="dummyFrag";
    public static final String BACKDROP_URL_BASE_PATH="http://image.tmdb.org/t/p/w500/";

    private void mascotaVieja(DBHandler db){
        mascota = db.getMascota(idMascota);
        mCollapsingToolbarLayout.setTitle(mascota.getNombre());
    }

    private void mascotaNueva(){
        mascota = new Mascota();
        mCollapsingToolbarLayout.setTitle("Nueva Mascota");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        this.db = new DBHandler(getApplicationContext());
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        mHeaderLayout = findViewById(R.id.headerLayout);
        mImageViewHeader = (ImageView) mHeaderLayout.findViewById(R.id.header_img);
        save = (ImageButton)mCollapsingToolbarLayout.findViewById(R.id.save);
        delete = (ImageButton)mCollapsingToolbarLayout.findViewById(R.id.delete);
        back = (ImageButton)mCollapsingToolbarLayout.findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DummyFragment frag = (DummyFragment) fragment;
                frag.setAtributosMascota();
                if (!mascota.EsValida()){
                    Toast.makeText(getApplicationContext(),"Debe llenar los campos Nombre, Tipo y Nacimiento.",Toast.LENGTH_SHORT).show();
                }else{
                    if (!isNew) {db.updateMascota(mascota);}
                    else{db.agregarMascota(mascota);}
                    Intent i = new Intent();
                    setResult(0, i);
                    finish();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isNew){
                    db.eliminarMascota(idMascota);
                }
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });

        mImageViewHeader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 0);
            }
        });


        idMascota = getIntent().getIntExtra("id", -1);

        //mCollapsingToolbarLayout.addView();

        if (idMascota < 0){ isNew = true;}
        if (!isNew) {mascotaVieja(db);}
        else{ mascotaNueva();}

        mImageViewHeader.setBackground(getDrawable(mascota.getImgId()));
        //Carrega a imagem para o imageview utilizando o picasso
        String url = BACKDROP_URL_BASE_PATH+"/sLbXneTErDvS3HIjqRWQJPiZ4Ci.jpg";
        //Log.i("MascotaActivity", "Url is: " + url);
        //PicassoUtils.with(getApplicationContext()).load(url).into(mImageViewHeader);

        if(savedInstanceState!=null){
            fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
        else{
            fragment = new DummyFragment();
        }
        DummyFragment frag = (DummyFragment)fragment;
        frag.setMascota(mascota);
        getSupportFragmentManager().beginTransaction().replace(R.id.details_container,fragment,FRAGMENT_TAG).commit();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
