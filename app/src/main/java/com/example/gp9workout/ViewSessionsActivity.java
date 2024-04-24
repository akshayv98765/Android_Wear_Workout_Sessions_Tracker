// ViewSessionsActivity.java
package com.example.gp9workout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import com.example.gp9workout.WorkoutSessionAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewSessionsActivity extends AppCompatActivity {

    private List<WorkoutSession> workoutSessions;
    private RecyclerView recyclerView;
    private WorkoutSessionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sessions);

        getSupportActionBar().hide(); // Hide the action bar

        workoutSessions = loadWorkoutSessions();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkoutSessionAdapter(workoutSessions);
        recyclerView.setAdapter(adapter);

        Log.d("ViewSessionsActivity", "Adapter item count: " + adapter.getItemCount());

    }

    private List<WorkoutSession> loadWorkoutSessions() {
        List<WorkoutSession> sessions = new ArrayList<>();

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        String sessionsString = preferences.getString("workout_sessions", "");

        if (!sessionsString.isEmpty()) {
            String[] sessionArray = sessionsString.split(",");
            for (String sessionStr : sessionArray) {
                WorkoutSession session = WorkoutSession.fromString(sessionStr);
                if (session != null) {
                    sessions.add(session);
                }
            }
        }

        // Log the number of loaded sessions
        Log.d("ViewSessionsActivity", "Number of loaded sessions: " + sessions.size());
        return sessions;
    }
}