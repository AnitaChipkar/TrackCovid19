package com.anitachipkar.covid19tracker.model;

import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("confirmed")
    private String confirmed;

    @SerializedName("deceased")
    private String deceased;

    @SerializedName("recovered")
    private String recovered;



    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }



}



