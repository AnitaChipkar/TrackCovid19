package com.anitachipkar.covid19tracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.anitachipkar.covid19tracker.R;
import com.anitachipkar.covid19tracker.model.Hospital;
import com.anitachipkar.covid19tracker.retrofit.ApiClient;
import com.anitachipkar.covid19tracker.retrofit.ApiInterface;
import com.anitachipkar.covid19tracker.retrofit.DataResponse;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalsActivity extends AppCompatActivity {

    RecyclerView recyclerViewHospitals;
    HospitalAdapter hospitalAdapter;
    ACProgressFlower dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerViewHospitals = findViewById(R.id.recyclerViewHospitals);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewHospitals.setLayoutManager(mLayoutManager);
        recyclerViewHospitals.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHospitals.setHasFixedSize(false);
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .text("Please Wait...")
                .bgColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        if (isNetworkConnected())
        {
            callHospitalsDataApi();
        }
        else
        {
            Toast.makeText(this,getResources().getString(R.string.no_internet),Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void shuffle() {
            Collections.shuffle(hospitalList, new Random(System.currentTimeMillis()));
            hospitalAdapter = new HospitalAdapter(this, hospitalList);
            recyclerViewHospitals.setAdapter(hospitalAdapter);
    }

    private void callHospitalsDataApi() {
        ApiInterface apiService = ApiClient.getClientHospitals().create(ApiInterface.class);
        Call<DataResponse> call  = apiService.getHospitalsData();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                dialog.dismiss();
                int statusCode = response.code();
                assert response.body() != null;
                hospitalList = response.body().getHospitalList();

                Log.d("TAG",hospitalList.toString());

                 hospitalAdapter = new HospitalAdapter(HospitalsActivity.this,hospitalList);
                 recyclerViewHospitals.setAdapter(hospitalAdapter);
                 hospitalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                // Log error here since request failed
                dialog.dismiss();
                Log.e("TAG", t.toString());
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);

    }
}
