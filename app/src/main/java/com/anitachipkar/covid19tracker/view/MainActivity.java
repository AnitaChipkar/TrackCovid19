package com.anitachipkar.covid19tracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anitachipkar.covid19tracker.model.DistrictData;
import com.anitachipkar.covid19tracker.retrofit.ApiClient;
import com.anitachipkar.covid19tracker.retrofit.ApiInterface;
import com.anitachipkar.covid19tracker.retrofit.DataResponse;
import com.anitachipkar.covid19tracker.retrofit.DistrictResponse;
import com.anitachipkar.covid19tracker.R;
import com.anitachipkar.covid19tracker.model.Country;
import com.anitachipkar.covid19tracker.model.State;
import com.anitachipkar.covid19tracker.model.Tested;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView lastUpdatedTime, countryConfirm, countryRecovered, countryDeceased,
            stateConfirmed, stateRecovered, stateDeceased,
            cityConfirmed, cityRecovered, cityDeceased,
            testReported, totalTested, totalPositive;

    MaterialButton buttonHospitals;
    ACProgressFlower dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (isNetworkConnected()) {
            callCountryDataApi();
            callDistrictDataApi();
        } else {

            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }


    }

    private void initViews() {
        lastUpdatedTime = findViewById(R.id.textUpdatedTime);
        countryConfirm = findViewById(R.id.confirmedValue);
        countryRecovered = findViewById(R.id.recoveredValue);
        countryDeceased = findViewById(R.id.deceasedValue);
        stateConfirmed = findViewById(R.id.confirmedMhValue);
        stateRecovered = findViewById(R.id.recoveredMhValue);
        stateDeceased = findViewById(R.id.deceasedMhValue);
        cityConfirmed = findViewById(R.id.confirmedCityValue);
        cityRecovered = findViewById(R.id.recoveredCityValue);
        cityDeceased = findViewById(R.id.deceasedCityValue);
        testReported = findViewById(R.id.reportedValue);
        totalTested = findViewById(R.id.testedValue);
        totalPositive = findViewById(R.id.positiveValue);
        buttonHospitals = findViewById(R.id.buttonHospitals);
        buttonHospitals.setOnClickListener(this);
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .text("Please Wait...")
                .bgColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
    }

    private void callCountryDataApi() {
        ApiInterface apiService = ApiClient.getClientCountry().create(ApiInterface.class);
        Call<DataResponse> call = apiService.getDataList();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                dialog.dismiss();
                int statusCode = response.code();
                assert response.body() != null;
                List<Country> countries = response.body().getCountryList();
                List<State> stateList = response.body().getStateList();
                List<Tested> testedList = response.body().getTestedList();
                Log.d(TAG, countries.toString());

                for (Country country : countries) {

                    countryConfirm.setText(country.getTotalconfirmed());
                    countryRecovered.setText(country.getTotalrecovered());
                    countryDeceased.setText(country.getTotaldeceased());
                }


                for (State state : stateList) {
                    if ("MH".equals(state.getStatecode())) {
                        stateConfirmed.setText(state.getConfirmed());
                        stateRecovered.setText(state.getRecovered());
                        stateDeceased.setText(state.getDeaths());

                        String updatedTime = state.getLastupdatedtime();

                        TimeZone utc = TimeZone.getTimeZone("etc/UTC");
                        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                                Locale.US);
                        inputFormat.setTimeZone(utc);
                        DateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy hh:mm aa",
                                Locale.US);
                        outputFormat.setTimeZone(utc);

                        Date date = null;
                        try {
                            date = inputFormat.parse(updatedTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String output = "Updated On " + outputFormat.format(date);

                        lastUpdatedTime.setText(output);
                    }
                }
                for (Tested tested : testedList) {

                    testReported.setText(tested.getSamplereportedtoday());
                    totalTested.setText(tested.getTotalsamplestested());
                    totalPositive.setText(tested.getTotalpositivecases());
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                // Log error here since request failed
                dialog.dismiss();
                Log.e(TAG, t.toString());
            }
        });
    }

    private void callDistrictDataApi() {
        ApiInterface apiService = ApiClient.getClientCountry().create(ApiInterface.class);
        Call<DistrictResponse> call = apiService.getDistrictData();
        call.enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                dialog.dismiss();
                int statusCode = response.code();
                DistrictResponse districts = response.body();
                if (districts != null) {
                    Log.d(TAG, districts.toString());
                    if ("MH".equals(districts.getMaharashtra().getStatecode()))
                    {
                        cityConfirmed.setText(districts.getMaharashtra().getDistrictData().getPune().getConfirmed());
                        cityRecovered.setText(districts.getMaharashtra().getDistrictData().getPune().getRecovered());
                        cityDeceased.setText(districts.getMaharashtra().getDistrictData().getPune().getDeceased());

                    }




                }


            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                dialog.dismiss();
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intentHospital = new Intent(MainActivity.this, HospitalsActivity.class);
        startActivity(intentHospital);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
