package com.djyde.gulliver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.djyde.gulliver.R;
import com.djyde.gulliver.adapter.TripsAdapter;
import com.djyde.gulliver.model.Trip;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Trip> trips = new ArrayList<Trip>();
    private NavigationView navigation;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        navigation = (NavigationView)findViewById(R.id.navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerToggle.syncState();
        drawer.setDrawerListener(drawerToggle);


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.settings:
                        // TODO setting
                        startActivity(new Intent(getApplicationContext(),NewTripActivity.class));
                        return true;
                    case R.id.history:
                        // TODO history
                        Toast.makeText(getApplicationContext(),"history",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }
        });

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
    public void onBackPressed() {
        if (navigation.isShown()){
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
