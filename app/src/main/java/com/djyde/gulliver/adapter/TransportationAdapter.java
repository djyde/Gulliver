package com.djyde.gulliver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.djyde.gulliver.R;
import com.djyde.gulliver.model.Transportation;

import java.util.List;

/**
 * Created by randy on 15/6/2.
 */
public class TransportationAdapter extends BaseAdapter {

    private List<Transportation> transportations;
    private Context context;

    public TransportationAdapter(Context context, List<Transportation> transportations) {
        super();
        this.transportations = transportations;
        this.context = context;
    }

    @Override
    public int getCount() {
        return transportations.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tranportation,parent,false);
        TextView name = (TextView)v.findViewById(R.id.name);
        ImageView icon = (ImageView)v.findViewById(R.id.icon);
        icon.setBackgroundResource(transportations.get(position).getBlackIcon());
        name.setText(transportations.get(position).getName());
        return v;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
