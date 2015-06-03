package com.djyde.gulliver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djyde.gulliver.R;
import com.djyde.gulliver.model.Trip;
import com.djyde.gulliver.model.TripSet;
import com.djyde.gulliver.utils.EasyTimer;

import java.util.List;

import se.emilsjolander.sprinkles.ModelList;
import se.emilsjolander.sprinkles.Query;

/**
 * Created by randy on 15/6/3.
 */
public class TripSetAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder>{

    private List<TripSet> tripSets;
    private Context context;
    private long past_time = 0;

    public TripSetAdapter(Context context, List<TripSet> tripSets) {
        super();
        this.tripSets = tripSets;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return tripSets.size();
    }

    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip,parent,false);
        return new TripsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TripsAdapter.ViewHolder holder, int position) {
        holder.icon.setBackgroundResource(R.drawable.ic_swap_horiz_white_48dp);
        holder.from.setText(tripSets.get(position).getTrip_from());
        holder.to.setText(tripSets.get(position).getTrip_to());
        holder.background.setBackgroundColor(tripSets.get(position).getColor());
        past_time = 0;
        List<Trip> trips = new ModelList<Trip>();
        trips = Query.many(Trip.class, "SELECT past_time FROM Trips WHERE trip_set_id=?", tripSets.get(position).getId()).get().asList();
        for (Trip trip : trips){
            past_time += trip.getPast_time();
        }
        holder.info.setText("平均 " + EasyTimer.time_render_chn((int)past_time / 1000 / trips.size()));
    }
}
