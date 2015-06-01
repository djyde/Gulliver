package com.djyde.gulliver.model;

import com.djyde.gulliver.R;

/**
 * Created by randy on 15/6/1.
 */
public class Transportation {
    private String name;
    private String id;
    private int icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        switch (this.id){
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

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
