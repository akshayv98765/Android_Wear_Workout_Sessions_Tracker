package com.example.gp9workout;

import android.util.Log;

import java.io.Serializable;

public class WorkoutSession implements Serializable {

    private int stepCount;
    private double weight;
    private double height;
    private double calories;

    public WorkoutSession(int stepCount, double weight, double height, double calories) {
        this.stepCount = stepCount;
        this.weight = weight;
        this.height = height;
        this.calories = calories;

        Log.d("WorkoutSession", "Created session: " + this.toString());
    }

    public int getStepCount() {
        return stepCount;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getCalories() {
        return calories;
    }

    public String getDetails() {
        return "Step Count: " + stepCount +
                "\nWeight: " + weight +
                "\nHeight: " + height +
                "\nCalories: " + calories;
    }

    // New method to convert a string back to a WorkoutSession
    public static WorkoutSession fromString(String sessionStr) {

        Log.d("WorkoutSession", "Parsed session: " + sessionStr);

        String[] parts = sessionStr.split(",");
        if (parts.length == 4) {
            try {
                int stepCount = Integer.parseInt(parts[0]);
                double weight = Double.parseDouble(parts[1]);
                double height = Double.parseDouble(parts[2]);
                double calories = Double.parseDouble(parts[3]);

                return new WorkoutSession(stepCount, weight, height, calories);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}