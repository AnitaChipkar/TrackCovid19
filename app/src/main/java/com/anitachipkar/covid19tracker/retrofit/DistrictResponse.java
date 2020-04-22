package com.anitachipkar.covid19tracker.retrofit;

import com.anitachipkar.covid19tracker.model.District;
import com.anitachipkar.covid19tracker.model.DistrictData;
import com.anitachipkar.covid19tracker.model.Maharashtra;
import com.google.gson.annotations.SerializedName;


public class DistrictResponse {


    @SerializedName("Maharashtra")
    private Maharashtra maharashtra;

    public Maharashtra getMaharashtra() {
        return maharashtra;
    }

    public void setMaharashtra(Maharashtra maharashtra) {
        this.maharashtra = maharashtra;
    }




}
