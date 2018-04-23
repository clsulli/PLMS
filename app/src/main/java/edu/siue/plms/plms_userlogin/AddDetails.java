package edu.siue.plms.plms_userlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddDetails extends AppCompatActivity {

    //GUI Elements
    private EditText inputName, inputEID;
    private Button btnAddDetails;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddetails);

        //Displays Toolbar Icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Attach GUI Inputs to Variables
        inputName = (EditText) findViewById(R.id.name);
        inputEID = (EditText) findViewById(R.id.EID);
        btnAddDetails = (Button) findViewById(R.id.submitButton2);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Parking Lot Monitoring System");
        mAuth = FirebaseAuth.getInstance();


        //Get Logged In Application User and get unique User Id
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userUid = user.getUid();

        //Creates Application User on Firebase when Register is Clicked.
        btnAddDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString().trim();
                String eid = inputEID.getText().toString().trim();

                //Updates Value
                Map<String, Object> update = new HashMap<>();
                update.put("filledDetails", "Yes");
                mFirebaseDatabase.child(userUid).updateChildren(update);
                update.put("name", name);
                mFirebaseDatabase.child(userUid).updateChildren(update);
                update.put("eid", eid);
                mFirebaseDatabase.child(userUid).updateChildren(update);

                startActivity(new Intent(AddDetails.this, MainActivity.class));

            }
        });

        //Detect if user needs to fill out additional details
        //Get Logged In Application User and get unique User Id
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String userUid = user.getUid();
//
//        mFirebaseDatabase.child("users").child(userUid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User value = (User) dataSnapshot.getValue(User.class);
//                Toast.makeText(AddDetails.this, value.eid, Toast.LENGTH_LONG).show();
//                Toast.makeText(AddDetails.this, value.name, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(AddDetails.this, "Read Error", Toast.LENGTH_LONG).show();
//            }
//
//        });


    }
}

