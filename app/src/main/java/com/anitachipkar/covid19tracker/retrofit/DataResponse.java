package com.anitachipkar.covid19tracker.retrofit;

import com.anitachipkar.covid19tracker.model.Country;
import com.anitachipkar.covid19tracker.model.Hospital;
import com.anitachipkar.covid19tracker.model.State;
import com.anitachipkar.covid19tracker.model.Tested;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("cases_time_series")
    private List<Country> countryList;

    @SerializedName("statewise")
    private List<State> stateList;

    @SerializedName("tested")
    private List<Tested> testedList;

    @SerializedName("hospitals")
    private List<Hospital> hospitalList;

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public List<Tested> getTestedList() {
        return testedList;
    }

    public void setTestedList(List<Tested> testedList) {
        this.testedList = testedList;
    }

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }
}
