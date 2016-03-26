package jdo589gv4353.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompleteTab extends AppCompatActivity {

    private Button btnCloseTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_tab);

        //tabout out button
        btnCloseTab = (Button) findViewById(R.id.btnCloseTab);
        btnCloseTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteTab.this, ConfirmComplete.class));
            }
        });
    }
}
