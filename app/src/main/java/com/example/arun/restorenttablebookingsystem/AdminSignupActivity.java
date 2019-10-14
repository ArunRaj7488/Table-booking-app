package com.example.arun.restorenttablebookingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class  AdminSignupActivity extends AppCompatActivity {
    Button sgn;
    EditText email, pass;
    FirebaseAuth f4;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        f4 = FirebaseAuth.getInstance();
        sgn = findViewById(R.id.button10);
        email = findViewById(R.id.editText5);
        pass = findViewById(R.id.editText6);
        sgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(AdminSignupActivity.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setTitle("Plese Wait..."); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
                login();
            }


        });
    }

    public void login() {
        String email1 = email.getText().toString();
        String password1 = pass.getText().toString();
        if (email1.equals("arunraj1735169@gmail.com") && password1.equals("9721125338")) {
            f4.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AdminSignupActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(AdminSignupActivity.this, BookingActivity.class);
                        startActivity(j);
                    } else {
                        Toast.makeText(AdminSignupActivity.this, "You are Not Admin", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
