package com.example.arun.restorenttablebookingsystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button mLogoutBtn;
    TextView mName, mPhone, mEmail;
    FirebaseFirestore mFire;
    ImageView addImage;
    Boolean hasImg;
    StorageReference mStorageReference;
    String docid;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!= null){
            if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK){
                    Uri uri = result.getUri();
                    addImage.setImageURI(uri);
                    hasImg = true;
                    mStorageReference.child(mAuth.getCurrentUser().getEmail())
                            .putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Profile.this, "profile updated!", Toast.LENGTH_SHORT).show();
//                            Glide.with(Profile.this).load(taskSnapshot.getDownloadUrl()).into(addImage);
//                            addImage.setImageURI(taskSnapshot.getUploadSessionUri());
                                mFire.collection("UserData").document(docid)
                                        .update("profileImage", taskSnapshot.getDownloadUrl().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Profile.this, "Image Saved", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }
                    });
                }
            }
        }else {
            hasImg = false;
            Toast.makeText(this, "Invalid Image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference("/ProfileImage/mProfileImg");
        mLogoutBtn = findViewById(R.id.mLogutBtn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        mName = findViewById(R.id.mName);
        mPhone = findViewById(R.id.mPhone);
        mEmail = findViewById(R.id.mEmail);
        addImage=findViewById(R.id.AddImage);
        mFire = FirebaseFirestore.getInstance();

        mFire.collection("UserData").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                String cemail = mAuth.getCurrentUser().getEmail();
                for(DocumentChange doc : documentSnapshots.getDocumentChanges()){
                    if(doc.getDocument().getString("email").equals(cemail)){
                        docid = doc.getDocument().getId();
                        mName.setText(doc.getDocument().getString("name"));
                        mEmail.setText(doc.getDocument().getString("email"));
                        mPhone.setText(doc.getDocument().getString("phone"));
//                        Toast.makeText(Profile.this, "docId" + docid, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(Profile.this, "URL: " + doc.getDocument().getString("profileImage"), Toast.LENGTH_SHORT).show();
                        if(!doc.getDocument().getString("profileImage").equals("default")){
                            Glide.with(Profile.this).load(doc.getDocument().getString("profileImage")).into(addImage);
                        }
                    }
                }
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Profile.this);
            }
        });
    }
}
