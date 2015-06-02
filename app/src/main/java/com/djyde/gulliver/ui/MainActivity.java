package com.djyde.gulliver.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.djyde.gulliver.R;
import com.djyde.gulliver.adapter.TripsAdapter;
import com.djyde.gulliver.model.Trip;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Sprinkles;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NavigationView navigation;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;

    public List<Trip> trips = new ArrayList<Trip>();
    public TripsAdapter tripsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sprinkles sprinkles = Sprinkles.init(this);
        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE Trips (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "trip_from TEXT," +
                                "trip_to TEXT," +
                                "color int," +
                                "transportation TEXT" +
                                ")"
                );
            }
        });

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
                switch (menuItem.getItemId()) {
                    case R.id.settings:
                        // TODO setting
                        startActivity(new Intent(getApplicationContext(), NewTripActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.history:
                        // TODO history
                        drawer.closeDrawers();
                        return true;
                    default:
                        return true;
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripsAdapter = new TripsAdapter(this,trips);
        recyclerView.setAdapter(tripsAdapter);





    }

    @Override
    public void onBackPressed() {
        if (navigation.isShown()){
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        trips.clear();
        trips.addAll(Query.many(Trip.class,"SELECT * FROM Trips ORDER BY id DESC").get().asList());
        tripsAdapter.notifyDataSetChanged();
    }
}
