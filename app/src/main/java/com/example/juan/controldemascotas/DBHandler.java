package com.example.juan.controldemascotas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "Mascotas";
    // Contacts table name
    private static final String TABLE_MASCOTAS = "mascotas";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIPO = "tipo";
    private static final String KEY_IMG_ID = "imgId";
    private static final String KEY_DATA_NAC = "dataNac";
    private static final String KEY_DATA_VAC = "dataVac";
    private static final String KEY_CITA_VET = "citaVet";
    private static final String KEY_ESPECIAL = "especial";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MASCOTAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TIPO + " TEXT," + KEY_IMG_ID + " INTEGER," + KEY_DATA_NAC + " TEXT,"
                + KEY_DATA_VAC + " TEXT," + KEY_CITA_VET + " TEXT," + KEY_ESPECIAL + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS);
        // Creating tables again
        onCreate(db);
    }

    public void agregarMascota(Mascota mascota) {
        mascota.setIdMascota(this.nextId());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, mascota.getIdMascota());
        values.put(KEY_NAME, mascota.getNombre());
        values.put(KEY_TIPO, mascota.getTipo());
        values.put(KEY_IMG_ID, mascota.getImgId());
        values.put(KEY_DATA_NAC, mascota.getDataNac());
        values.put(KEY_DATA_VAC, mascota.getDataVacunacion());
        values.put(KEY_CITA_VET, mascota.getCitaVet());
        values.put(KEY_ESPECIAL, mascota.getEspecial().toString());
        // Inserting Row
        db.insert(TABLE_MASCOTAS, null, values);
        db.close(); // Closing database connection
    }

    public Mascota getMascota(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MASCOTAS, new String[] { KEY_ID,
                        KEY_NAME, KEY_TIPO, KEY_IMG_ID, KEY_DATA_NAC, KEY_DATA_VAC, KEY_CITA_VET, KEY_ESPECIAL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Mascota mascota = new Mascota(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                Boolean.parseBoolean(cursor.getString(7)));

        return mascota;
    }

    public void eliminarMascota(Mascota mascota) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MASCOTAS, KEY_ID + " = ?",
                new String[] { String.valueOf(mascota.getIdMascota()) });
        db.close();
    }

    public void eliminarMascota(int idMascota) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MASCOTAS, KEY_ID + " = ?",
                new String[] { String.valueOf(idMascota) });
        db.close();
    }

    public List<Mascota> getAllMascotas() {
        List<Mascota> mascotas = new ArrayList<Mascota>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mascota mascota = new Mascota(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        Boolean.parseBoolean(cursor.getString(7)));
                // Adding contact to list
                mascotas.add(mascota);
            } while (cursor.moveToNext());
        }
        // return contact list
        return mascotas;
    }

    public void updateMascota(Mascota mascota) {
        this.eliminarMascota(mascota.getIdMascota());
        this.agregarMascota(mascota);
    }

    public int nextId() {
        Comparator<Mascota> cmp = new Comparator<Mascota>() {
            @Override
            public int compare(Mascota m1, Mascota m2) {
                return Integer.compare(m1.getIdMascota(), m2.getIdMascota());
            }
        };
        List<Mascota> mascotas = this.getAllMascotas();
        if (mascotas.isEmpty()) return 0;
        return Collections.max(mascotas, cmp).getIdMascota()+1;
    }
}
