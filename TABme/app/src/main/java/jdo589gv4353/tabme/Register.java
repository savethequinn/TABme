package jdo589gv4353.tabme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity implements View.OnClickListener{

    // create an instance of the DatabaseHelper class here
    DBHelper TabMeDB;

    // initialize variables for register form
    private EditText FNeditText, LNeditText, EMeditText, PeditText, BeditText, UNeditText, PWeditText, CPWeditText ;
    private Button RegisterButton;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // this will call the constructor of the DatabaseHelper class
        TabMeDB = new DBHelper(this);

        // get references to the widgets
        FNeditText = (EditText) findViewById(R.id.FNeditText);
        LNeditText = (EditText) findViewById(R.id.LNeditText);
        EMeditText = (EditText) findViewById(R.id.EMeditText);
        PeditText = (EditText) findViewById(R.id.PeditText);
        BeditText = (EditText) findViewById(R.id.BeditText);
        UNeditText = (EditText) findViewById(R.id.UNeditText);
        PWeditText = (EditText) findViewById(R.id.PWeditText);
        CPWeditText = (EditText) findViewById(R.id.CPWeditText);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);

        //creates a listener to listen for button clicks
        RegisterButton.setOnClickListener(this);
    }

    // overrides the onClick function and checks for completion of form
    @Override
    public void onClick(View v) {
        // if the fields are not field out, ask user to complete
        if (!CheckFields()) {
            Toast.makeText(getApplicationContext(), "Please complete all of the fields.", Toast.LENGTH_SHORT).show();
        }
        // else check to make sure the password and confirm password match
        else {
            // if the password and confirm password match, add the user
            if (PWeditText.getText().toString().equals(CPWeditText.getText().toString())){
                if (RegisterUser()) {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Register.this, "This username has already been taken. Please try another.", Toast.LENGTH_LONG).show();
                }
            }
            // if the password and confirm password do NOT match, ask the user to try again
            else{
                Toast.makeText(Register.this, "Your Passwords don't match! Try again!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // checks to see if the fields required are empty
    public boolean CheckFields() {
        if(FNeditText.getText().toString().equals("")||LNeditText.getText().toString().equals("")||EMeditText.getText().toString().equals("")||PeditText.getText().toString().equals("")||
                BeditText.getText().toString().equals("")||UNeditText.getText().toString().equals("")||PWeditText.getText().toString().equals("")||CPWeditText.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    // add the user to the database
    public boolean RegisterUser() {
        boolean isInserted = TabMeDB.insertData(UNeditText.getText().toString(), FNeditText.getText().toString(), LNeditText.getText().toString(), EMeditText.getText().toString(), PeditText.getText().toString(), BeditText.getText().toString(), PWeditText.getText().toString());
        if (isInserted == true) {
            Toast.makeText(Register.this, "You have been registered successfully!", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }
}
