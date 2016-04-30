package jdo589gv4353.tabme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompleteTab extends AppCompatActivity {

    private Button AddUserBtn, TabOutButton;
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
        final ArrayList<String> UserList = new ArrayList<String>();
        UserList.addAll(Arrays.asList(Users));

        // Create ArrayAdapter using the people list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, UserList);

        // add more defaults here
        //listAdapter.add( "Ceres" );

        // Set the ArrayAdapter as the ListView's adapter.
        UserListView.setAdapter( listAdapter );

        TabOutButton = (Button) findViewById(R.id.TabOutButton);
        TabOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer listlength = UserListView.getAdapter().getCount();
                ArrayList<String> Nums = new ArrayList<String>();
                for (int i=0; i<listlength; i++){
                    Nums.add(UserListView.getAdapter().getItem(i).toString());
                }
                updateNumberList(Nums);
                startActivity(new Intent(CompleteTab.this, FinalStep.class));
            }
        });

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
                                        if(newUserNumber.length()<(4)){
                                            Toast.makeText(CompleteTab.this, "Please enter the 10 digit number associated with the user in the correct format.", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            listAdapter.add(newUserNumber);
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
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

    public void updateNumberList(List<String> Nums){
        ((AppContext) this.getApplication()).setNumbers(Nums);
//        String numbers = "";
//        Integer listlength = Nums.size();
//        for (int i = 0; i<listlength; i++){
//            numbers = numbers + (Nums.get(i));
//        }
//        Toast.makeText(CompleteTab.this, numbers, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_complete_tab);

        //BEGIN USER LIST
        // Find the ListView resource.
        UserListView = (ListView) findViewById( R.id.UserListView );

        // Create and populate a List of people names.
        String[] Users = new String[] {((AppContext) this.getApplication()).getUserPhone()};
        final ArrayList<String> UserList = new ArrayList<String>();
        UserList.addAll(Arrays.asList(Users));

        // Create ArrayAdapter using the people list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, UserList);

        // add more defaults here
        //listAdapter.add( "Ceres" );

        // Set the ArrayAdapter as the ListView's adapter.
        UserListView.setAdapter( listAdapter );

        TabOutButton = (Button) findViewById(R.id.TabOutButton);
        TabOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer listlength = UserListView.getAdapter().getCount();
                ArrayList<String> Nums = new ArrayList<String>();
                for (int i=0; i<listlength; i++){
                    Nums.add(UserListView.getAdapter().getItem(i).toString());
                }
                String strd = "";
                for (int i=0; i<listlength; i++){
                    strd = strd + Nums.get(i) + " ";
                }
//                Toast.makeText(CompleteTab.this, strd, Toast.LENGTH_LONG).show();
                updateNumberList(Nums);
                startActivity(new Intent(CompleteTab.this, FinalStep.class));
            }
        });

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
                                        if(newUserNumber.length()<(4)){
                                            Toast.makeText(CompleteTab.this, "Please enter the 10 digit number associated with the user in the correct format.", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            listAdapter.add(newUserNumber);
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
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
