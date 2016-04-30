package jdo589gv4353.tabme;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrans extends AppCompatActivity {

    // create an instance of the DatabaseHelper class here
    TransactionDB myDb;

    // declare the variables needed to manipulate the widgets
    EditText editCost, editItem, editPayer, editTextId;
    Button buttonAddData;
    Button updateButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);

        // this will call the constructor of the DatabaseHelper class which will create the DB
        myDb = new TransactionDB(this);

        // this section gets all the GUI widget ID's for use in the Listeners
        editCost = (EditText)findViewById(R.id.editText);
        editItem = (EditText)findViewById(R.id.editText2);
        editPayer = (EditText)findViewById(R.id.editText3);
        editTextId = (EditText)findViewById(R.id.editText_ID);

        buttonAddData = (Button)findViewById(R.id.button_data);
        deleteButton = (Button)findViewById(R.id.buttonDelete);

        // in the onCreate of the Main Activity, call all of the methods needed to manage the DB
        AddData();
        DeleteData();
    }

    public boolean checkEntry() {
        if (editCost.getText().toString().equals("") || editItem.toString().equals("") || editPayer.toString().equals("")) {
            Toast.makeText(AddTrans.this, "Make sure to fill out all fields in the correct format. Try again!", Toast.LENGTH_LONG).show();
            return false;
        }
//        if (!editCost.getText().toString().contains(".")){
//            return false;
//        }
        return true;
    }

    // this section listens for the "Add Data" button event and inserts into the DB
    // must convert to String in order to input into the DB
    public void AddData() {

        buttonAddData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if (checkEntry()) {
                            float editCostfloat = Float.parseFloat(editCost.getText().toString());
                            boolean isInserted = myDb.insertData(editCostfloat, editItem.getText().toString(), editPayer.getText().toString());
//                            if (isInserted)
//                                Toast.makeText(AddTrans.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                            else
//                                Toast.makeText(AddTrans.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                }
        );
    }
    // this section listens for the "delete" button event and deletes the data with the ID specified
    public void DeleteData() {
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                        if (deletedRows > 0)
//                            Toast.makeText(AddTrans.this, "Data Deleted", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(AddTrans.this, "Data NOT Deleted", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
        );
    }



    // this method uses the AlertDialog method to create the data
    // Creates a builder for an alert dialog that uses the default alert dialog theme.
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
