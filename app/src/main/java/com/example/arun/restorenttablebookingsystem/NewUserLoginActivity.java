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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewUserLoginActivity extends AppCompatActivity {
    EditText email, pass, name, phone;
    Button login;
    ProgressDialog progressDialog;
    FirebaseAuth f;

    FirebaseFirestore mFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_login);
        f = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText3);
        pass = findViewById(R.id.editText4);
        login = findViewById(R.id.button9);

        mFire = FirebaseFirestore.getInstance();

        name = findViewById(R.id.mUserName);
        phone = findViewById(R.id.mUserPhone);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(NewUserLoginActivity.this);
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
                register();
            }


        });
    }
        public void register () {
           final String email = this.email.getText().toString();
            final String password = pass.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                f.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Map<String, String> userData = new HashMap<>();
                            userData.put("name", name.getText().toString());
                            userData.put("email", email);
                            userData.put("phone", phone.getText().toString());
                            userData.put("profileImage","default");

                            mFire.collection("UserData").add(userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                               if(task.isSuccessful()){

                                   Toast.makeText(NewUserLoginActivity.this, "Registration Successesful", Toast.LENGTH_SHORT).show();
                                   Intent h=new Intent(NewUserLoginActivity.this,Main3Activity.class);
                                   startActivity(h);
                               }else{
                                   Toast.makeText(NewUserLoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                                }
                            });

                        } else {
                            Toast.makeText(NewUserLoginActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
}