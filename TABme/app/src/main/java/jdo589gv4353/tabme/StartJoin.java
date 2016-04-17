package jdo589gv4353.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartJoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_join);
        //declare button variables find ids
        Button btnJoinTab = (Button) findViewById(R.id.btnJoinTab);
        Button btnStartTab = (Button) findViewById(R.id.btnStartTab);


        btnStartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartJoin.this, StartTab.class));
            }
        });

    }


}
