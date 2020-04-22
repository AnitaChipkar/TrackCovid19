package com.anitachipkar.covid19tracker.model;

import com.google.gson.annotations.SerializedName;

public class DistrictData {

    @SerializedName("Pune")
    private District Pune;





    public District getPune() {
        return Pune;
    }

    public void setPune(District pune) {
        Pune = pune;
    }

}



