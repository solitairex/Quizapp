package com.example.quizapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email, password, password01;
    Button register;
    FirebaseAuth firebaseAuth;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password01 = findViewById(R.id.confpassword);
        register = findViewById(R.id.registerbut);
        firebaseAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String pass01 = password.getText().toString();
                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass01)) {
                    Toast.makeText(Register.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length() < 6) {
                    Toast.makeText(Register.this, "Le mot de passe doit contenir au moins 6 caractere", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(pass01)) {
                    Toast.makeText(Register.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                signUp(mail, pass);
            }
        });
    }

    public void signUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        Register.this, task -> {
                            if (task.isSuccessful()) {

                                Toast.makeText(Register.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(Register.this, "Registration failed. " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
    }
}
