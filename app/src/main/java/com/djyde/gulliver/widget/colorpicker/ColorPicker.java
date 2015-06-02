package com.djyde.gulliver.widget.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.djyde.gulliver.ui.NewTripActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by randy on 15/6/2.
 */
public class ColorPicker extends RecyclerView {

    private List<Item> items = new ArrayList<Item>();

    public static final String[] MATERIAL_COLORS = {"#F44336","#E91E63","#9C27B0","#673AB7","#3F51B5","#2196F3","#03A9F4","#00BCD4","#009688","#4CAF50","#8BC34A","#CDDC39","#FFEB3B","#FFC107","#FF9800","#FF5722","#795548","#9E9E9E","#607D8B"};


    public static class Item{
        private int color;

        public Item(int color){
            this.color = color;
        }

        public int getColor() {
            return color;
        }
    }

    public ColorPicker(Context context) {
        super(context);


    }

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        ColorPickerAdapter adapter = new ColorPickerAdapter(context,items);
        adapter.setOnPickerSelectedListener(new ColorPickerAdapter.OnPickerSelectedListener() {
            @Override
            public void onSelected(Item selectedItem) {
                ((NewTripActivity)getContext()).relativeLayout.setBackgroundColor(selectedItem.getColor());
                ((NewTripActivity)getContext()).toolbar.setBackgroundColor(selectedItem.getColor());
                ((NewTripActivity)getContext()).color = selectedItem.getColor();

            }
        });
        setAdapter(adapter);
    }

    public ColorPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public void setItems(List<Item> items){
        this.items.addAll(items);
    }

    public void setItems(String[] colors){
        for (String color : colors){
            this.items.add(new Item(Color.parseColor(color)));
        }
    }
}
