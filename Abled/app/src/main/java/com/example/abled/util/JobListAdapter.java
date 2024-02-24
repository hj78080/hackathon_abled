package com.example.abled.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abled.R;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {

    private List<Job> jobList;

    public JobListAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.textJob.setText(job.getBusplaName());
        holder.textWork.setText(job.getJobNm());
        holder.textAddr.setText(job.getSimpleAddr());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textJob;
        TextView textWork;
        TextView textAddr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textJob = itemView.findViewById(R.id.text_job);
            textWork = itemView.findViewById(R.id.text_work);
            textAddr = itemView.findViewById(R.id.text_address);
        }
    }
}