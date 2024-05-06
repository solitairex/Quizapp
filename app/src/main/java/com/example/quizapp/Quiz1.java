package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapp.Quiz2;
import com.example.quizapp.R;

public class Quiz1 extends AppCompatActivity {
    private Chrono chronometer;
    Button next;
    RadioGroup rg;
    RadioButton rb;
    String correctResponse="Spainish Civil War";
    int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz1);
        chronometer = Chrono.getInstance();
        chronometer.start();
        long totalElapsedTime = chronometer.getTotalElapsedTime();
        next=findViewById(R.id.btnNext);
        rg=findViewById(R.id.radioGroup);
        next.setOnClickListener(v -> {
            if(rg.getCheckedRadioButtonId()==-1){
                Toast.makeText(getApplicationContext(),"Veillez choisir une reponse",Toast.LENGTH_SHORT).show();
            }else{
                rb=findViewById(rg.getCheckedRadioButtonId());
                if(rb.getText().toString().equals(correctResponse)) score+=1;
                Intent intent=new Intent(getApplicationContext(), Quiz2.class);
                intent.putExtra("score",score);
                intent.putExtra("totalElapsedTime", totalElapsedTime);
                startActivity(intent);
                overridePendingTransition(R.anim.exit,R.anim.entry);
                finish();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Arrete le chrono
        chronometer.stop();
    }

}