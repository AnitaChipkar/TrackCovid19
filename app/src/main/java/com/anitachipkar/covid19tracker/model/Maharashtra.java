package com.anitachipkar.covid19tracker.model;

import com.google.gson.annotations.SerializedName;

public class Maharashtra {

    @SerializedName("districtData")
    private DistrictData districtData;

    @SerializedName("statecode")
    private String statecode;


    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }



    public DistrictData getDistrictData() {
        return districtData;
    }

    public void setDistrictData(DistrictData districtData) {
        this.districtData = districtData;
    }


}
