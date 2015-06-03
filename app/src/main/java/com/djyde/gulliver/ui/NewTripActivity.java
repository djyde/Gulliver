package com.djyde.gulliver.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djyde.gulliver.R;
import com.djyde.gulliver.adapter.TransportationAdapter;
import com.djyde.gulliver.model.Transportation;
import com.djyde.gulliver.model.Trip;
import com.djyde.gulliver.model.TripSet;
import com.djyde.gulliver.utils.EasyTimer;
import com.djyde.gulliver.widget.colorpicker.ColorPicker;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Query;

public class NewTripActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ColorPicker colorPicker;
    private AppCompatSpinner spinner;
    public RelativeLayout relativeLayout;
    private TextView from;
    private TextView to;
    private TextInputLayout from_layout;
    private TextInputLayout to_layout;
    public int color = R.color.colorPrimary;
    private TextView timer;
    private String transportation;
    private EasyTimer easyTimer;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        colorPicker = (ColorPicker)findViewById(R.id.color_picker);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout);
        spinner = (AppCompatSpinner)findViewById(R.id.transportation);
        from = (TextView)findViewById(R.id.from);
        to = (TextView)findViewById(R.id.to);
        from_layout = (TextInputLayout)findViewById(R.id.from_layout);
        to_layout = (TextInputLayout)findViewById(R.id.to_layout);
        timer = (TextView)findViewById(R.id.timer);
        setSupportActionBar(toolbar);

        final List<Transportation> transportations = new ArrayList<Transportation>();

        transportations.add(new Transportation("railway"));
        transportations.add(new Transportation("subway"));
        transportations.add(new Transportation("walk"));


        spinner.setAdapter(new TransportationAdapter(this, transportations));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transportation = transportations.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        colorPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorPicker.setItems(ColorPicker.MATERIAL_COLORS);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        easyTimer = new EasyTimer();
        easyTimer.setOnTaskRunListener(new EasyTimer.OnTaskRunListener() {
            @Override
            public void onTaskRun(long past_time, String rendered_time) {
                timer.setText(rendered_time);
                time = past_time;
            }
        });
        easyTimer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        easyTimer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_trip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_done:
                newTrip();
                return true;
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newTrip(){
        if (from.getText().toString().equals("")){
            from_layout.setError("请输入出发地");
        } else if (to.getText().toString().equals("")){
            to_layout.setError("请输入目的地");
        } else {
            final Trip trip = new Trip();
            trip.setTrip_from(from.getText().toString());
            trip.setTrip_to(to.getText().toString());
            trip.setColor(color);
            TripSet tripSet = Query.one(TripSet.class, "SELECT id FROM TripSets WHERE (trip_from = ? AND trip_to = ?) OR (trip_from = ? AND trip_to = ?)", from.getText().toString(), to.getText().toString(), to.getText().toString(), from.getText().toString()).get();
            if (tripSet == null){
                final TripSet new_trip_set = new TripSet();
                new_trip_set.setTrip_to(to.getText().toString());
                new_trip_set.setTrip_from(from.getText().toString());
                new_trip_set.setColor(color);
                new_trip_set.saveAsync(new Model.OnSavedCallback() {
                    @Override
                    public void onSaved() {
                        trip.setTrip_set_id(new_trip_set.getId());
                        trip.setPast_time(time);
                        trip.setTransportation(transportation);
                        trip.saveAsync(new Model.OnSavedCallback() {
                            @Override
                            public void onSaved() {
                                finish();
                            }
                        });
                    }
                });
            } else {
                trip.setTrip_set_id(tripSet.getId());
                trip.setPast_time(time);
                trip.setTransportation(transportation);
                trip.saveAsync(new Model.OnSavedCallback() {
                    @Override
                    public void onSaved() {
                        finish();
                    }
                });
            }
        }

    }


}
