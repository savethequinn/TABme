package jdo589gv4353.tabme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class CompleteTab extends AppCompatActivity {

    private Button AddUserBtn;
    private ListView UserListView ;
    private ArrayAdapter<String> listAdapter ;

    final Context context = this;
    private String newUserNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_tab);

        //BEGIN USER LIST
        // Find the ListView resource.
        UserListView = (ListView) findViewById( R.id.UserListView );

        // Create and populate a List of people names.
        String[] Users = new String[] {((AppContext) this.getApplication()).getUserPhone()};
        ArrayList<String> UserList = new ArrayList<String>();
        UserList.addAll(Arrays.asList(Users));

        // Create ArrayAdapter using the people list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, UserList);

        // add more defaults here
        //listAdapter.add( "Ceres" );

        // Set the ArrayAdapter as the ListView's adapter.
        UserListView.setAdapter( listAdapter );

        AddUserBtn = (Button) findViewById(R.id.AddUserBtn);
        AddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteTab.this, AddUser.class));
            }
        });

        AddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Add User",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id) {
                                        newUserNumber = userInput.getText().toString();
                                        listAdapter.add(newUserNumber);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                //create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                //show it
                alertDialog.show();
            }
        });
    }
}
