package com.djyde.gulliver.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.djyde.gulliver.R;
import com.djyde.gulliver.adapter.TransportationAdapter;
import com.djyde.gulliver.model.Transportation;
import com.djyde.gulliver.widget.colorpicker.ColorPicker;

import java.util.ArrayList;
import java.util.List;

public class NewTripActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ColorPicker colorPicker;
    private AppCompatSpinner spinner;
    public RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        colorPicker = (ColorPicker)findViewById(R.id.color_picker);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout);
        spinner = (AppCompatSpinner)findViewById(R.id.transportation);
        setSupportActionBar(toolbar);

        final List<Transportation> transportations = new ArrayList<Transportation>();

        transportations.add(new Transportation("railway"));
        transportations.add(new Transportation("subway"));
        transportations.add(new Transportation("walk"));


        spinner.setAdapter(new TransportationAdapter(this, transportations));

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                transportations.get(position);
            }
        });

        colorPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorPicker.setItems(ColorPicker.MATERIAL_COLORS);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                spinner.getSelectedItemId();
                return true;
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
