package com.example.praktpm3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMovies;
    private ArrayList<Moviemodel> list = new ArrayList<>();

    private String title = "Movie List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvMovies =findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        list.addAll(Moviedata.getListData());

        showRecyclerCardView();

    }

    private void showRecyclerCardView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        cardview_item cardview_item = new cardview_item(list, this);
        rvMovies.setAdapter(cardview_item);
    }


    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}