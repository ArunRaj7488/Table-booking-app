package com.example.arun.restorenttablebookingsystem;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class BookingActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist email and id to another activity
    public static final String ARTIST_NAME = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String ARTIST_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";

    //view objects
    EditText editTextName;
    Spinner spinnerGenre;
    Button buttonAddcs, TimeBtn;
    ListView listViewcs;
    int bookdate, bookTimeHour, year, hour, month, min;
    Bundle b;


    //a list to store all the artist from firebase database
    List<CustmerName> css;

    //our database reference
    //
    // object
    DatabaseReference databasetb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        Calendar calendar = Calendar.getInstance();
        bookdate = calendar.getTime().getDate();
        bookTimeHour = calendar.getTime().getHours();
        year = calendar.getTime().getYear();
        month = calendar.getTime().getMonth();
        min = calendar.getTime().getMinutes();
        //getting the reference of artists node
        databasetb = FirebaseDatabase.getInstance().getReference("css");


//        "year_" + String.valueOf(year))
//                    .child("month_" + String.valueOf(month))
//                .child("date_" +String.valueOf(bookdate))
//                .child("table_no

        //getting views
        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenres);
        listViewcs = (ListView) findViewById(R.id.listViewArtists);

        buttonAddcs = (Button) findViewById(R.id.buttonAddArtist);
        TimeBtn = findViewById(R.id.bookingTimeBtn);

        TimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DateTime.class));
            }
        });

        b = getIntent().getExtras();

        TextView time = findViewById(R.id.bookingTimeTv);
        time.setText("Date: " + b.getString("dateDay") + "/" + b.getString("dateMonth") + "\n" +
        "Time: " +b.getString("time") + ":" + b.getString("timeMin"));



        //list to store artists
        css = new ArrayList<>();


        //adding an onclicklistener to button
        buttonAddcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                fetchdata();
            }
        });

        //attaching listener to listview

    }

    @Override
    protected void onResume() {
        super.onResume();


   }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener

        databasetb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                css.clear();

                //iterating through all the n
                // des
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    CustmerName artist = postSnapshot.getValue(CustmerName.class);
                    //adding artist to the list
                    css.add(artist);
                }
//                Log.d("cssarray", "onDataChange: " + css);
                //creating adapter
                TableList artistAdapter = new TableList(BookingActivity.this, css);
                //attaching adapter to the listview
                listViewcs.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }

    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addCustmer() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenre.getSelectedItem().toString();
//        String time = ;
//        Log.d("TmeMy", "addCustmer: " + time);


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist

            String id = databasetb.push().getKey();

//            time.setText("Date: " + b.getString("dateDay" + "/" + b.getString("dateMonth")));


            //creating an Artist Object
            CustmerName artist = new CustmerName(id, name, genre, Integer.parseInt(b.getString("dateDay")),
                    Integer.parseInt(b.getString("time")), Integer.parseInt(b.getString("timeMin")) );

            //Saving the Artist
            databasetb
                    .child(id).setValue(artist);
            //setting edittext to blank again
            editTextName.setText("");
            //displaying a success toast
            Toast.makeText(this, "Booked", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
        }
    }
        public void fetchdata(){
            ArrayList<String> table = new ArrayList<>();
            ArrayList<String> hour = new ArrayList<>();

            for(int i=0;i<css.size();i++){
                table.add(css.get(i).getCsGenre());
                hour.add(String.valueOf(css.get(i).getCsHour()));
            }

            String tableno = spinnerGenre.getSelectedItem().toString();
            Calendar ccalendar = Calendar.getInstance();

            int flag=0;

            for(int i=0;i<css.size();i++)
            {
                if(css.get(i).getCsGenre().toString().equals(tableno)){
                    if(String.valueOf(css.get(i).getCsHour()).equals(b.getString("time"))){
                        flag = 1;
                    }
                }
            }
            if(flag == 0){
                addCustmer();
            }else{
                Toast.makeText(this, "Aready Booked!", Toast.LENGTH_SHORT).show();
            }
        }
}