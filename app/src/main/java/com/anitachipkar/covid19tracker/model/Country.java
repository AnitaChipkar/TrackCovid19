package com.anitachipkar.covid19tracker.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("date")
    private String date;

    @SerializedName("totalconfirmed")
    private String totalconfirmed;


    @SerializedName("totalrecovered")
    private String totalrecovered;

    @SerializedName("totaldeceased")
    private String totaldeceased;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(String totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }


}
