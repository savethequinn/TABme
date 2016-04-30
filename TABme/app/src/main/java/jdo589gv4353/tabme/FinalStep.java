package jdo589gv4353.tabme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.List;

public class FinalStep extends Activity {

    TransactionDB myDb;
    TransactionDB myDb2;
    private Button compTabBtn ;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private TextView TxtContent;

    //variables for the dialog
    final Context context = this;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_step);

        myDb = new TransactionDB(this);
        myDb2 = new TransactionDB(this);

//        tabme out button
        compTabBtn = (Button) findViewById(R.id.compTabBtn);
        BuildText();
        TxtContent = (TextView) findViewById(R.id.TxtContent);
        String sms = ((AppContext) this.getApplication()).getTextContent();
        TxtContent.setText(sms);
        compTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Do you want to complete the tab? This will delete your current data and text your friends the information show on this page.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int hasWriteContactsPermission = 0;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    hasWriteContactsPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
                                }
                                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                                            showMessageOKCancel("You need to allow access to send sms",
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                                requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                                                                        REQUEST_CODE_ASK_PERMISSIONS);
                                                            }
                                                        }
                                                    });
                                            return;
                                        }
                                    }
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                    return;
                                }
                                sendText();
                                startActivity(new Intent(FinalStep.this, StartJoin.class));
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                } else {
                    // Permission Denied
                    Toast.makeText(FinalStep.this, "Send SMS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(FinalStep.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void sendText(){
        //send texts
        BuildText();
        List<String> currentNums = ((AppContext) this.getApplication()).getNumbers();
        String sms = ((AppContext) this.getApplication()).getTextContent();
        SmsManager smsManager = SmsManager.getDefault();
        for (int i = 0; i< currentNums.size(); i++){
            smsManager.sendTextMessage(currentNums.get(i), null, sms, null, null);
        }
        Toast.makeText(FinalStep.this,"SMS sent! Thanks for using TABme!", Toast.LENGTH_LONG).show();
        deleteTable();
    }

    private void deleteTable(){
        myDb.deleteTable("Transactions");
    }

    private void BuildText() {
        String txtContent = "";
        Cursor avgc = myDb.getAverageCost();

        if (!avgc.moveToFirst()) {
            // write message
            Toast.makeText(getApplicationContext(), "Uh-oh, something went wrong.", Toast.LENGTH_SHORT).show();
            return;
        }

        Float avg = avgc.getFloat(0);
        myDb.close();
        Cursor res = myDb2.getOwed(avg);
        if (!res.moveToFirst()) {
            // write message
            Toast.makeText(getApplicationContext(), "Uh-oh, something went wrong.", Toast.LENGTH_SHORT).show();
            return;
        }

        int rows = res.getCount();


        // outer for loop
        for (int i = 0; i < rows; i++) {


            txtContent = txtContent + res.getString(0) + " ";

            if (res.getFloat(1) < 0){
                Float dif = (res.getFloat(1) * -1);
                String difstr = "$" + String.format("%.2f", dif);

                txtContent = txtContent + "owes " + difstr + " to those who overpaid.\n";
            }
            else if (res.getFloat(1) == 0){
                txtContent = txtContent + "has paid their share.\n";
            }
            else{
                Float dif = (res.getFloat(1));
                String difstr = "$" + String.format("%.2f", dif);
                txtContent = txtContent + "has paid " + difstr + " more than they need.\n";
            }


            res.moveToNext();

        }
        txtContent = txtContent + "Thanks for using TABme!";
        ((AppContext) this.getApplication()).setTextContent(txtContent);

    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        BuildText();
        String sms = ((AppContext) this.getApplication()).getTextContent();
        TxtContent.setText(sms);
    }
}
