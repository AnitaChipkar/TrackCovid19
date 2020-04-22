package com.anitachipkar.covid19tracker.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("data.json")
    Call<DataResponse> getDataList();

    @GET("state_district_wise.json")
    Call<DistrictResponse> getDistrictData();


    @GET("hospitals.json")
    Call<DataResponse> getHospitalsData();
}
