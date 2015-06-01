package com.djyde.gulliver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.djyde.gulliver.adapter.TripsAdapter;
import com.djyde.gulliver.model.Trip;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Trip> trips = new ArrayList<Trip>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

//        for (int i = 0; i<5; i++){
//
//        }
        Trip trip = new Trip();
        trip.setFrom("客村站");
        trip.setTo("磨碟砂站");
        trip.setColor("#F1B136");

        Trip trip2 = new Trip();
        trip2.setFrom("家");
        trip2.setTo("轻轨站");
        trip2.setColor("#03A679");
        this.trips.add(trip);
        this.trips.add(trip2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TripsAdapter(this,trips));


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
