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

import com.example.quizapp.R;

public class Quiz3 extends AppCompatActivity {
    Button next;
    RadioGroup rg;
    RadioButton rb;
    private Chrono chronometer;
    String correctResponse="battle of three kings";
    int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz3);
        Intent itGet=getIntent();
        score = itGet.getIntExtra("score", 0);
        chronometer = Chrono.getInstance();
        chronometer.start();
        long totalElapsedTime = itGet.getLongExtra("totalElapsedTime", 0);
        next=findViewById(R.id.btnNext);
        rg=findViewById(R.id.radioGroup);
        next.setOnClickListener(v -> {
            if(rg.getCheckedRadioButtonId()==-1){
                Toast.makeText(getApplicationContext(),"Veillez choisir une reponse",Toast.LENGTH_SHORT).show();
            }else{
                rb=findViewById(rg.getCheckedRadioButtonId());
                if(rb.getText().toString().equals(correctResponse)) score+=1;
                Intent intent=new Intent(getApplicationContext(), Quiz4.class);
                intent.putExtra("score",score);
                intent.putExtra("totalElapsedTime", totalElapsedTime + chronometer.getTotalElapsedTime());
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