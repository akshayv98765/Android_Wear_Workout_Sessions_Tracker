package com.example.gp9workout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutSessionAdapter extends RecyclerView.Adapter<WorkoutSessionAdapter.ViewHolder> {

    private List<WorkoutSession> workoutSessions;

    public WorkoutSessionAdapter(List<WorkoutSession> workoutSessions) {
        this.workoutSessions = workoutSessions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_session, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutSession session = workoutSessions.get(position);
        holder.bind(session);
    }

    @Override
    public int getItemCount() {
        return workoutSessions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSessionDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSessionDetails = itemView.findViewById(R.id.txtSessionDetails);
        }

        public void bind(WorkoutSession session) {
            Log.d("WorkoutSessionAdapter", "Binding session: " + session.getDetails());
            txtSessionDetails.setText(session.getDetails());
        }

    }
}