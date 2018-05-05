package com.example.android.jsonmysql2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class TransactionType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_type);
        Bundle b2 = getIntent().getExtras();
        if(b2!=null) {

            int position = b2.getInt("customername");
            TextView txtTransactionType = (TextView) findViewById(R.id.textView_customerName);

            Intent i = getIntent();
            // getting attached intent data
            String strTransactionType = i.getStringExtra("customername");
            // displaying selected product name
            txtTransactionType.setText(strTransactionType);
        }
    }

    public void sendMessage(View view) {
        final RadioButton cash = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton credit = (RadioButton) findViewById(R.id.radioButton2);
        TextView txtCustomerName = (TextView) findViewById(R.id.textView_customerName);
        if (cash.isChecked()) {

            Intent Intents= new Intent(TransactionType.this, CashInvoice.class); // <----- START "BEACHES" ACTIVITY
            //Intents.putExtra("result",txtCustomerName.getText());
            Intents.putExtra("paramName", txtCustomerName.getText().toString());
            startActivity(Intents);

        }
        else if(credit.isChecked())
        {
            Intent Intents= new Intent(TransactionType.this, CreditInvoice.class); // <----- START "BEACHES" ACTIVITY
            Intents.putExtra("paramName", txtCustomerName.getText().toString());
            startActivity(Intents);
        }
        else
        {
            String url = "http://forandroid3000.esy.es/php_insert_data_in_mysql_database.php";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setPackage("com.android.chrome");
            try {
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                // Chrome is probably not installed
                // Try with the default browser
                i.setPackage(null);
                startActivity(i);
            }
        }

       // Intent intent = new Intent(this, CashInvoice.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
       // startActivity(intent);
    }
}




