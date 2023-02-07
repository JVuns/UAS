package com.cv.dosomethingapp;

import com.google.gson.annotations.SerializedName;

public class Activity {
    @SerializedName("key")
    private String key;
    @SerializedName("activity")
    private String activity;
    @SerializedName("type")
    private String type;
    @SerializedName("participants")
    private int participants;
    @SerializedName("price")
    private double price;
    @SerializedName("link")
    private String link;

    public Activity(String activity, String type, int participants) {
        this.activity = activity;
        this.type = type;
        this.participants = participants;
    }

    public String getKey() {
        return key;
    }

    public String getActivity() {
        return activity;
    }

    public String getType() {
        return type;
    }

    public int getParticipants() {
        return participants;
    }

    public double getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }
}
