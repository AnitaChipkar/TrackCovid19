package com.anitachipkar.covid19tracker.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.covid19india.org/";
    public static final String HOSPITALS_URL = "https://anitachipkar.github.io/";
    private static Retrofit retrofitCountry = null;
    private static Retrofit retrofitHospitals = null;


    public static Retrofit getClientCountry() {
        if (retrofitCountry==null) {
            retrofitCountry = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitCountry;
    }

    public static Retrofit getClientHospitals() {
        if (retrofitHospitals==null) {
            retrofitHospitals = new Retrofit.Builder()
                    .baseUrl(HOSPITALS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitHospitals;
    }
}
