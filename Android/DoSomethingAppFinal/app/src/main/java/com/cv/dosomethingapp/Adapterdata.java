package com.cv.dosomethingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapterdata extends RecyclerView.Adapter<Adapterdata.HolderData> {
    List<Activity> activityList;
    LayoutInflater inflater;

    public Adapterdata(Context context, ArrayList<Activity> listData) {
        this.activityList = listData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_data, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
         holder.nameText.setText(activityList.get(position).getActivity());
         holder.typeText.setText(activityList.get(position).getType());
         holder.participantText.setText(String.valueOf(activityList.get(position).getParticipants()));
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView typeText;
        TextView participantText;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.activityNameText);
            typeText = itemView.findViewById(R.id.activityTypeText);
            participantText = itemView.findViewById(R.id.activityParticipantText);
        }
    }
}
