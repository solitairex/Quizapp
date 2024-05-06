package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tvscore;
    TextView tvtotal;
    Button logoutbtn;
    Button tvbutton;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        progressBar = findViewById(R.id.progressBar);
        tvscore = findViewById(R.id.textView);
        logoutbtn = findViewById(R.id.logoutbtn);
        tvtotal = findViewById(R.id.chrono);
        tvbutton = findViewById(R.id.trybtn);

        Intent i = getIntent();
        score = i.getIntExtra("score", 0);
        long totalElapsedTime = i.getLongExtra("totalElapsedTime", 0);

        tvscore.setText(score * 100 / 5 + "%");
        progressBar.setProgress(score * 100 / 5);
        tvtotal.setText("Total Time: " + totalElapsedTime + " seconds");

        logoutbtn.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(it);
            finish();
        });
        tvbutton.setOnClickListener(v -> {
            // Reset the score
            score = 0;
            // Start Quiz 1
            Intent intent = new Intent(getApplicationContext(), Quiz1.class);
            startActivity(intent);
            // Finish the Score activity
            finish();
        });
    }
}
