package com.example.quizapp;

import android.os.SystemClock;

public class Chrono {
    private static Chrono instance;
    private long startTime;
    private long totalTime;

    private Chrono() {
        totalTime=0;
    }

    public static synchronized Chrono getInstance() {
        if (instance == null) {
            instance = new Chrono();
        }
        return instance;
    }

    public void start() {
        startTime = SystemClock.elapsedRealtime();
    }

    public void stop() {
        totalTime += SystemClock.elapsedRealtime() - startTime;
    }

    public void reset() {
        totalTime = 0;
    }

    public long getTotalElapsedTime() {
        return totalTime;
    }
}

