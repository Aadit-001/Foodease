package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Vendor_interface extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String restaurant_name;
    TextView viewCategories;

    public static final String EXTRA_NAME = "com.example.VendorMenu_viewer.extra.NAME";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_interface);

        TextView registerNow = findViewById(R.id.registerNow);
        Button logout = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();

        String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        Log.d("User ID:" , userID);

        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference("vendors").child(userID);
        vendorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    restaurant_name = snapshot.child("restaurant_name").getValue(String.class);
                    if (restaurant_name == null) {
                         Toast.makeText(Vendor_interface.this , "No restaurant name found for given vendorID",Toast.LENGTH_SHORT).show();
                    }else{
                        startVendorMenuViewerActivity();
                    }
                }else{
                    Toast.makeText(Vendor_interface.this , "No vendor found for given vendorID",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Vendor_interface.this , "Error in retrieving vendor information",Toast.LENGTH_SHORT).show();
            }
        });
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Vendor_interface.this,Vendor_registration.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(Vendor_interface.this, VendorLogin.class));
                    finish();
                }
            }
        });
    }
    public void startVendorMenuViewerActivity(){

        viewCategories = findViewById(R.id.viewCategories);
        viewCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Vendor_interface.this,VendorMenu_viewer.class);
                intent.putExtra(EXTRA_NAME, restaurant_name);
                Log.d("Name",restaurant_name);
                startActivity(intent);
            }
        });
    }
}