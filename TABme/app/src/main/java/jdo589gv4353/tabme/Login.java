package jdo589gv4353.tabme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    // define variables for the widgets
    private EditText UNeditText;
    private EditText PWeditText;
    private Button LoginButton;
    private Button   RegisterButton;

    // define the SharedPreferences object
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get references to the widgets
        UNeditText = (EditText) findViewById(R.id.UNeditText);
        PWeditText = (EditText) findViewById(R.id.PWeditText);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);

        //set on click listeners
//        LoginButton.setOnClickListener(this);
//        RegisterButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginButton:
                if (UNeditText.toString().equalsIgnoreCase("JDO589")||UNeditText.toString().equalsIgnoreCase("GV4353")&& PWeditText.toString().equals("cs329e"))
                {
                    Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(this, HomeMenu.class);
                    //startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login NOT successfull.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.RegisterButton:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }
}