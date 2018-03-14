package edu.siue.plms.plms_userlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    //GUI Elements
    private EditText inputName, inputEmail, inputEID, inputPassword;
    private Button btnRegister;

    //Firebase Variables
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Displays Toolbar Icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Attach GUI Inputs to Variables
        inputName = (EditText) findViewById(R.id.studentName);
        inputEID = (EditText) findViewById(R.id.eid);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.submitButton);

        //Setup Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        mFirebaseInstance.getReference("app_title").setValue("Parking Lot Monitoring System");

        //Create Sample Users
        //createUser("johdoe", "John Doe", "johdoe@siue.edu", "abcdef");
        //createUser("jandoe", "Jane Doe", "jandoe@siue.edu", "ghijkl");

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String eid = inputEID.getText().toString();
                String password = inputPassword.getText().toString();

                createUser(eid, name, email, password);
            }
        });
    }

    //Creates new User in Firebase
    public void createUser(String eid, String name, String email, String password)
    {
        //Locates users 'directory' in Firebase
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        //Creates Unique ID for User inside of 'users'
        String userID = mDatabase.push().getKey();
        User user = new User(eid, name, email, password);
        //Creates user in database
        mDatabase.child(userID).setValue(user);
    }
}
