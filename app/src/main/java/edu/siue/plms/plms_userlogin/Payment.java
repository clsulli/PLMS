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
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    //TAG
    public static final String TAG = "LOGGING";

    //GUI Elements
    private TextView totalDue;
    private Button c10, c25, c50, c100;
    private double tempTotal;

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

        //Attach GUI elements to Variables
        totalDue = (TextView) findViewById(R.id.totalDue);
        c10 = (Button) findViewById(R.id.c10);
        c25 = (Button) findViewById(R.id.c25);
        c50 = (Button) findViewById(R.id.c50);
        c100 = (Button) findViewById(R.id.c100);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        //Get Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Get Logged In Application User and get unique User Id
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userUid = user.getUid();

        DatabaseReference ref = mFirebaseDatabase.child("users").child(userUid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = (User) dataSnapshot.getValue(User.class);
                totalDue.setText(value.totalDue);
                tempTotal = Double.parseDouble(value.totalDue.substring(1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference valRef = mFirebaseDatabase.child("users").child(userUid).child("totalDue");
                double newValue;
                newValue = tempTotal - 0.10;
                if (newValue < 0.00) {
                    newValue = 0.00;
                }
                String newValueStr = "$" + newValue;
                valRef.setValue(newValueStr);
            }
        });

        c25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference valRef = mFirebaseDatabase.child("users").child(userUid).child("totalDue");
                double newValue;
                newValue = tempTotal - 0.25;
                if (newValue < 0.00) {
                    newValue = 0.00;
                }
                String newValueStr = "$" + newValue;
                valRef.setValue(newValueStr);
            }
        });

        c50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference valRef = mFirebaseDatabase.child("users").child(userUid).child("totalDue");
                double newValue;
                newValue = tempTotal - 0.50;
                if (newValue < 0.00) {
                    newValue = 0.00;
                }
                String newValueStr = "$" + newValue;
                valRef.setValue(newValueStr);
            }
        });

        c100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference valRef = mFirebaseDatabase.child("users").child(userUid).child("totalDue");
                double newValue;
                newValue = tempTotal - 1.00;
                if (newValue < 0.00) {
                    newValue = 0.00;
                }
                String newValueStr = "$" + newValue;
                valRef.setValue(newValueStr);
            }
        });


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

