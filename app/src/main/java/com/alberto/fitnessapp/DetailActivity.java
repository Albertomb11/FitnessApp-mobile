package com.alberto.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alberto.fitnessapp.model.Fitness;
import com.squareup.picasso.Picasso;

/**
 * Created by Alberto on 14/12/2017.
 */
public class DetailActivity extends AppCompatActivity{

    ImageView mImageViewFitnessLogo;
    TextView mTextViewFitnessName;
    TextView mTextViewFitnessCategoria;
    TextView mTextViewFitnessPosicion;
    TextView mTextViewFitnessEjecucion;
    Fitness fitness;

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fitness = new Fitness();

        mImageViewFitnessLogo = findViewById(R.id.fitness_logo_imagen_view);
        mTextViewFitnessName = findViewById(R.id.fitness_name_text_view);
        mTextViewFitnessCategoria = findViewById(R.id.categoria_text_view);
        mTextViewFitnessPosicion = findViewById(R.id.posicion_text_view);
        mTextViewFitnessEjecucion = findViewById(R.id.ejecucion_text_view);

        Intent detailIntent = DetailActivity.this.getIntent();

        if (detailIntent.hasExtra("fitness")){
            fitness = (Fitness)detailIntent.getSerializableExtra("fitness");
        }

        Log.d("DetailActivity", "Imagen Src: " + fitness.getImagen());

        Picasso.with(this).load(fitness.getImagen()).into(mImageViewFitnessLogo);
        mTextViewFitnessName.setText(fitness.getNombre());
        mTextViewFitnessCategoria.setText(fitness.getCategoria());
        mTextViewFitnessPosicion.setText(fitness.getPosicion());
        mTextViewFitnessEjecucion.setText(fitness.getEjecucion());
    }

}
