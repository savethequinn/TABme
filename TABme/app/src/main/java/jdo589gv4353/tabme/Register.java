package jdo589gv4353.tabme;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class Register extends AppCompatActivity {

    private EditText FNeditText;
    private EditText LNeditText;
    private EditText EMeditText;
    private EditText PeditText;
    private EditText BeditText;
    private EditText UNeditText;
    private EditText PWeditText;
    private EditText CPWeditText;
    private Button RegisterButton;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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
    }


}
