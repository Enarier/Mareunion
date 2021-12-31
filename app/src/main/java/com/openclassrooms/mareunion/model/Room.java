package com.openclassrooms.mareunion.model;

import com.openclassrooms.mareunion.R;

public class Room {

    private String mName;
    private int mColor;

    public Room(String name, int color) {
        mName = name;
        mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
