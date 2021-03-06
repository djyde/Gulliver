package com.djyde.gulliver.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.djyde.gulliver.R;
import com.djyde.gulliver.adapter.TripSetAdapter;
import com.djyde.gulliver.model.TripSet;

import se.emilsjolander.sprinkles.Query;

public class AnalyzeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TripSetAdapter tripSetAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tripSetAdapter = new TripSetAdapter(this, Query.all(TripSet.class).get().asList());

        recyclerView.setAdapter(tripSetAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
