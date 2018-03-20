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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    //TAG
    public static final String TAG = "LOGGING";

    //GUI Elements
    private EditText inputEmail, inputPassword;
    private Button btnLogin, btnRegister;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Displays Toolbar Icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Attach GUI Inputs to Variables
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnCreateUser);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        mFirebaseInstance.getReference("app_title").setValue("Parking Lot Monitoring System");

        //Get Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String email = inputEmail.getText().toString();
               final String password = inputPassword.getText().toString();

               if (TextUtils.isEmpty(email))
               {
                   Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (TextUtils.isEmpty(password))
               {
                   Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                   return;
               }

               //Authenticate User
               mAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (!task.isSuccessful())
                               {
                                   Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                               }
                               else
                               {
                                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                   finish();
                               }
                           }
                       });
           }
       });

        }
    }

