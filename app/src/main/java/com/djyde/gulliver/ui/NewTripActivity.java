package com.djyde.gulliver.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.djyde.gulliver.R;
import com.djyde.gulliver.widget.colorpicker.ColorPicker;

import java.util.ArrayList;
import java.util.List;

public class NewTripActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ColorPicker colorPicker;
    public RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        colorPicker = (ColorPicker)findViewById(R.id.color_picker);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout);
        setSupportActionBar(toolbar);

        List<ColorPicker.Item> items = new ArrayList<ColorPicker.Item>();



        colorPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorPicker.setItems(ColorPicker.MATERIAL_COLORS);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
