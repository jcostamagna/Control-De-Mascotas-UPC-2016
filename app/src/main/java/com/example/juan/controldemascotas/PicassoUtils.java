package com.example.juan.controldemascotas;

import android.content.Context;

import com.squareup.picasso.Picasso;


public class PicassoUtils {
    private static Picasso instance;

    public static Picasso with(Context context) {
        if (instance == null) {
            instance = new Picasso.Builder(context.getApplicationContext())
                    .build();
        }
        return instance;
    }

    private PicassoUtils() {
        throw new AssertionError("No instances.");
    }
}