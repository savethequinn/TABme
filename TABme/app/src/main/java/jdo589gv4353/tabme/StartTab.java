package jdo589gv4353.tabme;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Application;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

public class StartTab extends Activity {

    TransactionDB myDb;

    private ListView mainListView ;
    private ListView transactionListView;
    private ArrayAdapter<String> listAdapter ;
    private Button btnAddUser ;
    private Button btnAddTran ;
    private Button btnTabOut ;
    private Button btnViewAll;
    private TextView transactionList;
    private TextView splitHeader;
    private TableLayout tableLayout;
    private TextView helpText;

    //variables for the dialog
    private EditText inputAddUser ;
    final Context context = this;
    private String m_text;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tab);

        myDb = new TransactionDB(this);

        //tabout out button
        btnTabOut = (Button) findViewById(R.id.btnTabOut);
        btnTabOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myDb.CheckEmpty()){
                startActivity(new Intent(StartTab.this, CompleteTab.class));
                }
                else {
                Toast.makeText(getApplicationContext(), "Please add some transactions before you TAB out!", Toast.LENGTH_SHORT).show();
            }}
        });
        //add transaction activity
        btnAddTran = (Button) findViewById(R.id.btnAddTran);
        btnAddTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartTab.this, AddTrans.class));
            }
        });
//        viewAll();
        helpText = (TextView) findViewById(R.id.helpText);

        updateHeader();
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayout.removeAllViews();
        BuildTable();

    }
        private void BuildTable() {
            Cursor res = myDb.getAllData();
            tableLayout = (TableLayout) findViewById(R.id.tableLayout);
            if (!res.moveToFirst()) {
                // write message
                String data = "You have not added any transactions yet! Click the 'Add Transaction' button above to start your tab!";
                helpText.setText(data);
                return;
            }
            helpText.setText("");
            int rows = res.getCount();
            int cols = res.getColumnCount();

            //create headers
            TableRow header = new TableRow(this);
            header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            for (int k = 0; k < cols; k++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);

                tv.setText(res.getColumnName(k));

                header.addView(tv);
            }
            tableLayout.addView(header);

            // outer for loop
            for (int i = 0; i < rows; i++) {

                TableRow row = new TableRow(this);
                row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));

                // inner for loop
                for (int j = 0; j < cols; j++) {

                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    tv.setBackgroundResource(R.drawable.cell_shape);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(18);
                    tv.setPadding(0, 5, 0, 5);

                    tv.setText(res.getString(j));

                    row.addView(tv);

                }

                res.moveToNext();

                tableLayout.addView(row);

            }
        }

    public void updateHeader(){
        splitHeader = (TextView) findViewById(R.id.splitHeader);
        String headerText = "Current Tab for ";
        String FN = ((AppContext) this.getApplication()).getUserFirst();
        headerText = headerText + FN + ":";
        splitHeader.setText(headerText);
    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        tableLayout.removeAllViews();
        BuildTable();
//        viewAll();
    }


    // this method listens for the "View All" button to be pressed the displays all data to the user
//    public void viewAll() {
//
//        transactionList = (TextView)findViewById(R.id.transactionList);
//
//        Cursor res = myDb.getAllData();
//        if (res.getCount() == 0) {
//            // write message
//            String data = "There are currently no transactions.";
//            transactionList.setText(data);
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()) {
//            buffer.append("Transaction ID: " + res.getString(0) + "\n");
//            buffer.append("Cost: " + res.getString(1) + "\n");
//            buffer.append("Item: " + res.getString(2) + "\n");
//            buffer.append("Payer: " + res.getString(3) + "\n\n");
//        }
//
//        String data = buffer.toString();
//        transactionList.setText(data);
//
//    }



    public void showMessage (String title, String Message){
        // create a new alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Sets whether the dialog is cancelable or not.
        builder.setCancelable(true);

        // Set the title displayed in the dialog
        builder.setTitle(title);

        // set the message to display
        builder.setMessage(Message);

        // creates an AlertDialog with the arguments supplied to this builder and immediately displays the dialog.
        builder.show();
    }
}


