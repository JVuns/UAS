package com.cv.dosomethingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityTypesAdapter extends RecyclerView.Adapter<ActivityTypesAdapter.ActivityTypesViewHolder> {
    private ArrayList<ActivityTypes> typesList;

    public ActivityTypesAdapter(ArrayList<ActivityTypes> typesList) {
        this.typesList = typesList;
    }

    @NonNull
    @Override
    public ActivityTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitytype_item, parent, false);
        return new ActivityTypesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityTypesViewHolder holder, int position) {
        ActivityTypes type = typesList.get(position);
        holder.activityType.setText(type.getActivityType());
        holder.activityTypeDetail.setText(type.getActivityTypeDetail());
    }

    @Override
    public int getItemCount() {
        return typesList.size();
    }

    public class ActivityTypesViewHolder extends RecyclerView.ViewHolder {
        TextView activityType, activityTypeDetail;
        public ActivityTypesViewHolder(@NonNull View itemView) {
            super(itemView);
            activityType = itemView.findViewById(R.id.type_name);
            activityTypeDetail = itemView.findViewById(R.id.type_detail);
        }
    }
}
