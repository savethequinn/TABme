package jdo589gv4353.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartJoin extends AppCompatActivity {
    public Button btnStartTab;
    public TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_join);
        //declare button variables find ids
        Button btnStartTab = (Button) findViewById(R.id.btnStartTab);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        String name = ((AppContext) this.getApplication()).getUserFirst();
        String bubble = "Welcome to TABme, " + name + "! Touch 'START TAB' below to get started.";
        textView9.setText(bubble);
        btnStartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartJoin.this, StartTab.class));
            }
        });

    }


}
