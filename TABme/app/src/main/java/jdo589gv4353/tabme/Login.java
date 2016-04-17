package jdo589gv4353.tabme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class Login extends AppCompatActivity implements View.OnClickListener {

    // create an instance of the DatabaseHelper class here
    DBHelper TabMeDB;

    // define variables for the widgets
    private EditText UNeditText, PWeditText;
    private Button LoginButton, RegisterButton;

    // define the SharedPreferences object
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // this will call the constructor of the DatabaseHelper class
        TabMeDB = new DBHelper(this);

        // get references to the widgets
        UNeditText = (EditText) findViewById(R.id.UNeditText);
        PWeditText = (EditText) findViewById(R.id.PWeditText);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);

        //set on click listeners
        LoginButton.setOnClickListener(this);
        RegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginButton:
                if (TabMeDB.checkLogin(UNeditText.getText().toString(), PWeditText.getText().toString()))
                {
                    setUserInfoVar(TabMeDB.getUserData(UNeditText.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, StartTab.class);
                    startActivity(intent);
                    break;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "We were unable to log you in at this time. Please register or try again.", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.RegisterButton:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }

    // sets global variables for user info so that we can avoid making a lot of database calls
    public void setUserInfoVar(Cursor res){
        res.moveToFirst();
        ((AppContext) this.getApplication()).setUserFirst(res.getString(1));
        ((AppContext) this.getApplication()).setUserLast(res.getString(2));
        ((AppContext) this.getApplication()).setUserPhone(res.getString(4));
        ((AppContext) this.getApplication()).setUserEmail(res.getString(3));
        ((AppContext) this.getApplication()).setUserName(res.getString(0));
    }
}