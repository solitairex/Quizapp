package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button sign;
    TextView register;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();


        email=findViewById(R.id.EmailAdress1);
        password=findViewById(R.id.PasswordEdit1);
        sign=findViewById(R.id.signbut);
        register=findViewById(R.id.Register1);


        /*        sign.setOnClickListener(v -> {
            if(email.getText().toString().equals("adam@gmail.com")
                    && password.getText().toString().equals("123456")){
                Intent it=new Intent(getApplicationContext(),Quiz1.class);
                startActivity(it);
            }else{
                Toast.makeText(getApplicationContext(),"invalid passord or email",Toast.LENGTH_SHORT).show();
            }
        });
     */


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                         @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(MainActivity.this, Quiz1.class);
                                startActivity(intent);
                            } else {

                                Toast.makeText(MainActivity.this, "Identifiant ou mdp incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        });
            }
        });



        register.setOnClickListener(v -> {
            Intent it=new Intent(getApplicationContext(), Register.class);
            startActivity(it);
        });
    }

}