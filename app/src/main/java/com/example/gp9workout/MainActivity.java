package com.example.gp9workout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gp9workout.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<WorkoutSession> workoutSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide(); // Hide the action bar

        workoutSessions = new ArrayList<>();

        // Set initial text for step count
        binding.txtStepCount.setText(getString(R.string.step_count_text, 0));

        binding.stepSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the step count text as the seek bar is changed
                binding.txtStepCount.setText(getString(R.string.step_count_text, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.btnAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkoutSession();
            }
        });

        binding.btnViewSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkoutSessions();
            }
        });
    }

    private void addWorkoutSession() {
        // Extract data from UI
        int stepCount = binding.stepSpeed.getProgress();
        double weight = Double.parseDouble(binding.edtWeight.getText().toString());
        double height = Double.parseDouble(binding.edtHeight.getText().toString());

        // Calculate calories
        double stride = height * 0.414;
        double distance = stride * stepCount;
        double speed;

        if (stepCount >= 0 && stepCount <= 79) {
            speed = 0.9;
        } else if (stepCount >= 80 && stepCount <= 99) {
            speed = 1.34;
        } else {
            speed = 1.79;
        }

        double time = distance / speed;
        double calories = time * 3.5 * weight / (200 * 60);

        // Save the workout session
        WorkoutSession session = new WorkoutSession(stepCount, weight, height, calories);
        workoutSessions.add(session);

        // Save workout sessions in SharedPreferences
        saveWorkoutSessions();

        // Clear input fields
        clearInputFields();
    }

    private void saveWorkoutSessions() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Convert the list to a string for saving in SharedPreferences
        StringBuilder sessionsString = new StringBuilder();
        for (WorkoutSession session : workoutSessions) {
            sessionsString.append(session.toString()).append(",");
        }

        editor.putString("workout_sessions", sessionsString.toString());
        editor.apply();
    }

    private void showWorkoutSessions() {
        // Start the ViewSessionsActivity
        Intent intent = new Intent(this, ViewSessionsActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StringFormatInvalid")
    private void clearInputFields() {
        binding.txtStepCount.setText(getString(R.string.step_count_text, 0));
        binding.edtWeight.setText("");
        binding.edtHeight.setText("");
        binding.stepSpeed.setProgress(0);
    }
}