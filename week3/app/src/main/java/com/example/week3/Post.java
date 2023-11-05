package com.example.week3;

import android.graphics.Bitmap;
import android.location.Location;

public class Post {
    private Bitmap image;

    private Location location;

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Bitmap getImage(Bitmap image) {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
