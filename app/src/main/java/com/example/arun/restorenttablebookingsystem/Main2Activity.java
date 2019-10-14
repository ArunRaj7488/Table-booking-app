package com.example.arun.restorenttablebookingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arun.restorenttablebookingsystem.Main3Activity;
 import com.example.arun.restorenttablebookingsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    EditText t1, t2;
    Button b1, b2;
    TextView t4;
    ProgressDialog progressDialog;
    FirebaseAuth f3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        f3 = FirebaseAuth.getInstance();
        t1 = findViewById(R.id.editText);
        t2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.button5);
        t4 = findViewById(R.id.textView5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Main2Activity.this);
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
        String email1 = t1.getText().toString();
        String password1 = t2.getText().toString();
        if (!email1.isEmpty() && !password1.isEmpty()) {
            f3.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Main2Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent y = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(y);
                    } else {
                        Toast.makeText(Main2Activity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent t=new Intent(Main2Activity.this,NewUserLoginActivity.class);
                    startActivity(t);
                    }
            });
            }
    }
}