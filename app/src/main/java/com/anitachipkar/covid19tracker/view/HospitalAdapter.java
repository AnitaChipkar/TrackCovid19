package com.anitachipkar.covid19tracker.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anitachipkar.covid19tracker.R;
import com.anitachipkar.covid19tracker.model.Hospital;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {

private List<Hospital> hospitalList;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textHospitalName;

    public MyViewHolder(View view) {
        super(view);
        textHospitalName = view.findViewById(R.id.textHospitalName);
       // textHospitalName.setPaintFlags(textHospitalName.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

    }
}


    public HospitalAdapter(Context context ,List<Hospital> hospitals) {
        this.context = context;
        this.hospitalList = hospitals;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_hospital_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);
        holder.textHospitalName.setText(hospital.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(context,MapsActivity.class);
                intentMap.putExtra("HospitalName",hospitalList.get(position).getName());
                intentMap.putExtra("Latitude",hospitalList.get(position).getLatitude());
                intentMap.putExtra("Longitude",hospitalList.get(position).getLongitude());
                context.startActivity(intentMap);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }
}
