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

public class Parking extends AppCompatActivity {

    //TAG
    public static final String TAG = "LOGGING";

    //GUI Elements
    private TextView currentTotal, currentLot, currentRate,
                     lotATotal, lotBTotal, lotCTotal;
//    private Button btnLogin, btnRegister;
//    private Button btnTest;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking);

        //Displays Toolbar Icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Attach GUI elements to Variables
        currentTotal = (TextView) findViewById(R.id.currentTotal);
        currentLot = (TextView) findViewById(R.id.currentLot);
        currentRate = (TextView) findViewById(R.id.currentRate);
        lotATotal = (TextView) findViewById(R.id.lotATotal);
        lotBTotal = (TextView) findViewById(R.id.lotBTotal);
        lotCTotal = (TextView) findViewById(R.id.lotCTotal);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        //Get Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Get Logged In Application User and get unique User Id
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = user.getUid();

        //Read database User value
        DatabaseReference ref = mFirebaseDatabase.child("users").child(userUid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = (User) dataSnapshot.getValue(User.class);
                Toast.makeText(Parking.this, value.totalDue, Toast.LENGTH_LONG).show();
                currentTotal.setText(value.totalDue);
                currentLot.setText("Lot: " + value.lotID);
                currentRate.setText("Rate: " + value.lotPrice + "/h");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Parking.this, "Read Error", Toast.LENGTH_LONG).show();
            }
        });

        ref = mFirebaseDatabase.child("lots");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lots value1 = (Lots) dataSnapshot.getValue(Lots.class);
                Toast.makeText(Parking.this, value1.Lot1, Toast.LENGTH_LONG).show();
                lotATotal.setText(value1.Lot1);
                lotBTotal.setText(value1.Lot2);
                lotCTotal.setText(value1.Lot3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Parking.this, "Read Error", Toast.LENGTH_LONG).show();
            }
        });
    }

}

