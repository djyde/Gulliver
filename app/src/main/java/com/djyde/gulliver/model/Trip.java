package com.djyde.gulliver.model;

import com.djyde.gulliver.R;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by randy on 15/6/1.
 */

@Table("Trips")
public class Trip extends Model {

    @Key
    @AutoIncrement
    @Column("id")
    private long id;

    @Column("trip_set_id")
    private long trip_set_id;

    @Column("trip_from")
    private String trip_from;

    @Column("trip_to")
    private String trip_to;

    @Column("color")
    private int color;

    @Column("transportation")
    private String transportation;

    @Column("past_time")
    private long past_time;

    public long getTrip_set_id() {
        return trip_set_id;
    }

    public void setTrip_set_id(long trip_set_id) {
        this.trip_set_id = trip_set_id;
    }

    public long getPast_time() {
        return past_time;
    }

    public void setPast_time(long past_time) {
        this.past_time = past_time;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTrip_from() {
        return trip_from;
    }

    public void setTrip_from(String trip_from) {
        this.trip_from = trip_from;
    }

    public String getTrip_to() {
        return trip_to;
    }

    public void setTrip_to(String trip_to) {
        this.trip_to = trip_to;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public int getTransportationBlackIcon() {
        switch (this.transportation){
            case "railway":
                return R.drawable.ic_directions_railway_black_48dp;
            case "subway":
                return R.drawable.ic_directions_subway_black_48dp;
            case "walk":
                return R.drawable.ic_directions_walk_black_48dp;
            default:
                return R.drawable.ic_directions_walk_black_48dp;
        }
    }

    public int getTransportationWhiteIcon(){
        switch (this.transportation){
            case "railway":
                return R.drawable.ic_directions_railway_white_48dp;
            case "subway":
                return R.drawable.ic_directions_subway_white_48dp;
            case "walk":
                return R.drawable.ic_directions_walk_white_48dp;
            default:
                return R.drawable.ic_directions_walk_white_48dp;
        }
    }
}
