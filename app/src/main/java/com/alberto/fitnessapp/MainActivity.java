package com.alberto.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alberto.fitnessapp.model.Fitness;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView mListViewFitness;
    ArrayList<Fitness> mListaFitness = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewFitness = findViewById(R.id.list_view_fitness);

        final ArrayAdapter<Fitness> adapter = new ArrayAdapter<Fitness>(
                this,
                R.layout.list_item_ejercicio,
                R.id.text_view_ejercicios,
                mListaFitness
        );

        mListViewFitness.setAdapter(adapter);

        mListViewFitness.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(
                        MainActivity.this,
                        DetailActivity.class);
                detailIntent.putExtra("fitness", mListaFitness.get(position));
                startActivity(detailIntent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://fitnessapp-app.herokuapp.com/api/fitness",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("MainActivity", "Algo esta fallando");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("MainActivity", "OK->Response:" + responseString);
                        Gson gson = new GsonBuilder().create();
                        Fitness[] fitne = gson.fromJson(responseString, Fitness[].class);
                        adapter.clear();
                        for (Fitness fitness : fitne){
                            adapter.add(fitness);
                        }
                    }
                });
    }
}
