package jdo589gv4353.tabme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConfirmComplete extends AppCompatActivity implements View.OnClickListener {

    private Button YesButton;
    private Button NoButton;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_complete);

        YesButton = (Button) findViewById(R.id.YesButton);
        NoButton = (Button) findViewById(R.id.NoButton);

        NoButton.setOnClickListener(this);
        YesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.NoButton:
                Intent intent = new Intent(this, StartJoin.class);
                startActivity(intent);
                break;
            case R.id.YesButton:
                Toast.makeText(getApplicationContext(), "This button will eventually allow you to post a status to Facebook!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, FacebookPage.class);
//                startActivity(intent);
                break;
        }
    }
}
