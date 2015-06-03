package com.djyde.gulliver.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by randy on 15/6/3.
 */
@Table("TripSets")
public class TripSet extends Model {

    @Key
    @AutoIncrement
    @Column("id")
    private long id;

    @Column("color")
    private int color;

    @Column("trip_from")
    private String trip_from;

    @Column("trip_to")
    private String trip_to;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
