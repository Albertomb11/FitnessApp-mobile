package com.alberto.fitnessapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alberto.fitnessapp.model.Ejercicio;
import com.squareup.picasso.Picasso;

/**
 * Created by Alberto on 14/12/2017.
 */
public abstract class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView mImageViewFitnessLogo;
    TextView mTextViewFitnessName;
    TextView mTextViewFitnessCategoria;
    TextView mTextViewFitnessPosicion;
    TextView mTextViewFitnessEjecucion;
    Ejercicio ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ejercicio = new Ejercicio();

        mImageViewFitnessLogo = findViewById(R.id.fitness_logo_imagen_view);
        mTextViewFitnessName = findViewById(R.id.fitness_name_text_view);
        mTextViewFitnessCategoria = findViewById(R.id.categoria_text_view);
        mTextViewFitnessPosicion = findViewById(R.id.posicion_text_view);
        mTextViewFitnessEjecucion = findViewById(R.id.ejecucion_text_view);

        Intent detailIntent = DetailActivity.this.getIntent();

        if (detailIntent.hasExtra("ejercicio")){
            ejercicio = (Ejercicio)detailIntent.getSerializableExtra("ejercicio");
        }

        Log.d("DetailActivity", "Image Src: " + ejercicio.getImagen());

        Picasso.with(this).load(ejercicio.getImagen()).into(mImageViewFitnessLogo);
        mTextViewFitnessName.setText(ejercicio.getNombre());
        mTextViewFitnessCategoria.setText(ejercicio.getCategoria());
        mTextViewFitnessPosicion.setText(ejercicio.getPosicion());
        mTextViewFitnessEjecucion.setText(ejercicio.getEjecucion());
    }

}
