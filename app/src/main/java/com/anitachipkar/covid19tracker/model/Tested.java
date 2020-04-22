package com.anitachipkar.covid19tracker.model;

import com.google.gson.annotations.SerializedName;

public class Tested {

    @SerializedName("samplereportedtoday")
    private String samplereportedtoday;

    @SerializedName("totalpositivecases")
    private String totalpositivecases;

    @SerializedName("totalsamplestested")
    private String totalsamplestested;

    @SerializedName("updatetimestamp")
    private String updatetimestamp;


    public String getSamplereportedtoday() {
        return samplereportedtoday;
    }

    public void setSamplereportedtoday(String samplereportedtoday) {
        this.samplereportedtoday = samplereportedtoday;
    }

    public String getTotalpositivecases() {
        return totalpositivecases;
    }

    public void setTotalpositivecases(String totalpositivecases) {
        this.totalpositivecases = totalpositivecases;
    }

    public String getTotalsamplestested() {
        return totalsamplestested;
    }

    public void setTotalsamplestested(String totalsamplestested) {
        this.totalsamplestested = totalsamplestested;
    }

    public String getUpdatetimestamp() {
        return updatetimestamp;
    }

    public void setUpdatetimestamp(String updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }



}
