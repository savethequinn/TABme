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
        setContentView(R.layout.activity_main);

        // this will call the constructor of the DatabaseHelper class which will create the DB
        myDb = new TransactionDB(this);

        // this section gets all the GUI widget ID's for use in the Listeners
        editCost = (EditText)findViewById(R.id.editText);
        editItem = (EditText)findViewById(R.id.editText2);
        editPayer = (EditText)findViewById(R.id.editText3);
        editTextId = (EditText)findViewById(R.id.editText_ID);

        buttonAddData = (Button)findViewById(R.id.button_data);
        updateButton = (Button)findViewById(R.id.buttonUpdate);
        deleteButton = (Button)findViewById(R.id.buttonDelete);

        // in the onCreate of the Main Activity, call all of the methods needed to manage the DB
        AddData();
        updateData();
        DeleteData();
    }

    // this section listens for the "Add Data" button event and inserts into the DB
    // must convert to String in order to input into the DB
    public void AddData() {
        buttonAddData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        float editCostfloat = Float.parseFloat(editCost.getText().toString());
                        boolean isInserted = myDb.insertData(editCostfloat, editItem.getText().toString(), editPayer.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(AddTrans.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddTrans.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
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
                        if (deletedRows > 0)
                            Toast.makeText(AddTrans.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddTrans.this, "Data NOT Deleted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    // this listens for the "update" button on the GUI to update the DB based on ID entered
    public void updateData() {
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        float editCostfloat = Float.parseFloat(editCost.getText().toString());
                        boolean isUpdated = myDb.updateData(editTextId.getText().toString(), editCostfloat, editItem.getText().toString(), editPayer.getText().toString());
                        if (isUpdated == true)
                            Toast.makeText(AddTrans.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddTrans.this, "Data NOT Updated", Toast.LENGTH_LONG).show();
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
