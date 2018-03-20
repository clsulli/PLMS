package edu.siue.plms.plms_userlogin;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    //TAG
    public static final String TAG = "LOGGING";

    //GUI Elements
    private EditText inputEmail, inputPassword;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //Displays Toolbar Icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        //Get Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Get Logged In Application User and get unique User Id
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = user.getUid();

        //Read database User value
//        mFirebaseDatabase.child("users").child(userUid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User value = (User) dataSnapshot.getValue(User.class);
//                Toast.makeText(MainActivity.this, value.eid, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, "Read Error", Toast.LENGTH_LONG).show();
//            }
//        });

    }

}

