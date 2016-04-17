package jdo589gv4353.tabme;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class StartTab extends Activity {

    private ListView mainListView ;
    private ListView transactionListView;
    private ArrayAdapter<String> listAdapter ;
    private Button btnAddUser ;
    private Button btnAddTran ;
    private Button btnTabOut ;

    //variables for the dialog
    private EditText inputAddUser ;
    final Context context = this;
    private String m_text;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //tabout out button
        btnTabOut = (Button) findViewById(R.id.btnTabOut);
        btnTabOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartTab.this, CompleteTab.class));
            }
        });


        //BEGIN USER LIST
        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.mainListView );

        // Create and populate a List of people names.
        String[] people = new String[] { "James", "Jessie", "Justin", "Garner",
                "Robert"};
        ArrayList<String> peopleList = new ArrayList<String>();
        peopleList.addAll(Arrays.asList(people));

        // Create ArrayAdapter using the people list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, peopleList);

        // add more defaults here
        //listAdapter.add( "Ceres" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartTab.this, AddUser.class));
            }
        });

        btnAddTran = (Button) findViewById(R.id.btnAddTran);
        btnAddTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartTab.this, AddTrans.class));
            }
        });

//        btnAddTran.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //do something
//                //Toast.makeText(getApplicationContext(), "Add a user", Toast.LENGTH_SHORT).show()
//                //get prompts.xml view
//                LayoutInflater li = LayoutInflater.from(context);
//                View promptsView = li.inflate(R.layout.prompts, null);
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//                //set prompts.xml to alertdialog builder
//                alertDialogBuilder.setView(promptsView);
//
//                final EditText userInput = (EditText) promptsView
//                        .findViewById(R.id.editTextDialogUserInput);
//
//                alertDialogBuilder
//                        .setCancelable(false)
//                        .setPositiveButton("ADD",
//                                new DialogInterface.OnClickListener(){
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        m_text = userInput.getText().toString();
//                                        listAdapter.add(m_text);
//                                    }
//                                })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                //create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                //show it
//                alertDialog.show();
//
//
//            }
//        });


        //BEGIN SECTION IS FOR THE TRANSACTIONS

        // Find the ListView resource.
        transactionListView = (ListView) findViewById( R.id.transactionListView );

        // Create and populate a List of planet names.
        String[] transactions = new String[] { "Pizza, $12", "8 Shots, $55", "4 Beers, $32"};
        ArrayList<String> transactionList = new ArrayList<String>();
        transactionList.addAll( Arrays.asList(transactions) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, transactionList);

        // add more defaults here
        //listAdapter.add( "Ceres" );


        // Set the ArrayAdapter as the ListView's adapter.
        transactionListView.setAdapter( listAdapter );





    }
}


