package com.example.arun.restorenttablebookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    Button b1,User,Admin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Main3Activity.class));
            finish();
        }

        t1=findViewById(R.id.textView);
        //t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        //t4=findViewById(R.id.textView4);
        //t5=findViewById(R.id.textView5);
       // b1=findViewById(R.id.button2);
        User=findViewById(R.id.button3);
        Admin=findViewById(R.id.button4);
        //b4=     findViewById(R.id.button4);


        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);

            }
        });
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u=new Intent(MainActivity.this,AdminSignupActivity.class);
                startActivity(u);
                }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(MainActivity.this,NewUserLoginActivity.class);
                startActivity(g);
            }
        });
    }
}