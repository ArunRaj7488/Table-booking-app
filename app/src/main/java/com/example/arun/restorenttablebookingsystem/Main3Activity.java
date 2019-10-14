package com.example.arun.restorenttablebookingsystem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity implements MenuList.OnFragmentInteractionListener{
    Button menu,Booking, profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        menu = findViewById(R.id.button6);
        Booking = findViewById(R.id.button7);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.GONE);
                Booking.setVisibility(View.GONE);
                MenuList n = new MenuList();
                getSupportFragmentManager().beginTransaction().replace(R.id.llb, n).addToBackStack(null).commit();
            }
        });

        profileBtn = findViewById(R.id.mProfileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Booking.setVisibility(View.GONE);
                Intent l=new Intent(Main3Activity.this,DateTime.class);
                  startActivity(l);
            }
        });
        }
    public void onBackPressed() {
        super.onBackPressed();
        // Invisible or Gone Your Views here....
        menu.setVisibility(View.VISIBLE);
        Booking.setVisibility(View.VISIBLE);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
