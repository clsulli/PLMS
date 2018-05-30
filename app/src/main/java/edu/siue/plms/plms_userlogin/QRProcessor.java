package edu.siue.plms.plms_userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRProcessor extends AppCompatActivity {

    private Button enterBtn, exitBtn;
    private TextView qrResult, mode;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_processor);

        // Scanner
        enterBtn = (Button) findViewById(R.id.enterBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        qrResult = (TextView) findViewById(R.id.qrResult);
        mode = (TextView) findViewById(R.id.mode);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        //Read database User value
        DatabaseReference ref = mFirebaseDatabase.child("qr_code").child("mode");

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode.setText("Enter");
//                IntentIntegrator integrator = new IntentIntegrator(QRProcessor.this);
//                integrator.initiateScan();

                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference modeRef = mFirebaseDatabase.child("qr_code").child("signal");
                DatabaseReference uidRef = mFirebaseDatabase.child("qr_code").child("uid");
//
                modeRef.setValue(mode.getText().toString());
                uidRef.setValue("6HQPugaYw2OnVvpgtRIYqlASNHy2");
                qrResult.setText("6HQPugaYw2OnVvpgtRIYqlASNHy2");
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode.setText("Exit");
                String modeStr = mode.getText().toString();
//                IntentIntegrator integrator = new IntentIntegrator(QRProcessor.this);
//                integrator.initiateScan();

                mFirebaseDatabase = mFirebaseInstance.getReference();
                DatabaseReference modeRef = mFirebaseDatabase.child("qr_code").child("signal");
                DatabaseReference uidRef = mFirebaseDatabase.child("qr_code").child("uid");

                modeRef.setValue(mode.getText().toString());
                uidRef.setValue("6HQPugaYw2OnVvpgtRIYqlASNHy2");
                qrResult.setText("6HQPugaYw2OnVvpgtRIYqlASNHy2");
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String re = scanResult.getContents();
            Log.d("code", re);
            qrResult.setText(re);

            mFirebaseDatabase = mFirebaseInstance.getReference();
            DatabaseReference modeRef = mFirebaseDatabase.child("qr_code").child("signal");
            DatabaseReference uidRef = mFirebaseDatabase.child("qr_code").child("uid");

            modeRef.setValue(mode);
            uidRef.setValue(re);

        }
        // else continue with any other code you need in the method

    }
}