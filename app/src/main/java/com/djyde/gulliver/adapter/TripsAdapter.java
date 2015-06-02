package com.djyde.gulliver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djyde.gulliver.R;
import com.djyde.gulliver.model.Trip;

import java.util.List;

/**
 * Created by randy on 15/6/1.
 */
public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder>{

    private List<Trip> trips;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView from;
        TextView to;
        TextView info;
        ImageView icon;
        RelativeLayout background;
        public ViewHolder(View itemView) {
            super(itemView);
            background = (RelativeLayout)itemView.findViewById(R.id.background);
            from = (TextView)itemView.findViewById(R.id.from);
            to = (TextView)itemView.findViewById(R.id.to);
            info = (TextView)itemView.findViewById(R.id.info);
            icon = (ImageView)itemView.findViewById(R.id.icon);
        }
    }

    public TripsAdapter(Context context, List<Trip> trips) {
        this.trips = trips;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.from.setText(trips.get(i).getTrip_from());
        viewHolder.to.setText(trips.get(i).getTrip_to());
        viewHolder.info.setText("大约 44 分钟");
        viewHolder.background.setBackgroundColor(trips.get(i).getColor());
        switch (trips.get(i).getTransportation()){
            case "railway":
                viewHolder.icon.setBackgroundResource(R.drawable.ic_directions_railway_white_48dp);
                break;
            case "walk":
                viewHolder.icon.setBackgroundResource(R.drawable.ic_directions_walk_white_48dp);
                break;
            case "subway":
                viewHolder.icon.setBackgroundResource(R.drawable.ic_directions_subway_white_48dp);
                break;
            default:
                viewHolder.icon.setBackgroundResource(R.drawable.ic_directions_walk_white_48dp);
                break;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip,null,false);
        return new ViewHolder(view);
    }
}
