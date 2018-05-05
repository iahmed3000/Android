package com.example.android.jsonmysql2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditInvoice extends AppCompatActivity {
    EditText invoiceNumber,invoiceAmount,invoiceAmountPaid ;
    TextView txtName;
    String GetTxtname,GetInvoiceNumber,GetInvoiceAmount,GetInvoiceAmountPaid ;
    Button save ;
    Button confirm_credit ;
    String DataParseUrl = "http://forandroid3000.esy.es/insert-credit-invoice.php" ;
    Boolean CheckEditText ;
    String Response;
    HttpResponse response ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_invoice);
        Bundle b2 = getIntent().getExtras();
        if(b2!=null) {

            TextView txtCustomerName = (TextView) findViewById(R.id.textViewCreditCustomerName);

            Intent i = getIntent();
            // getting attached intent data
            String cName2 = i.getStringExtra("paramName");
            // displaying selected product name
            txtCustomerName.setText(cName2);
        }
        txtName = (TextView) findViewById(R.id.textViewCreditCustomerName);
        invoiceNumber = (EditText) findViewById(R.id.editText_credit_invoiceNumber);
        invoiceAmount = (EditText) findViewById(R.id.editText_credit_invoice_amount);
        invoiceAmountPaid=(EditText) findViewById(R.id.editText_credit_invoice_amount_paid);

        save = (Button) findViewById(R.id.credit_button_save);
        confirm_credit = (Button) findViewById(R.id.button_confirm_credit);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GetCheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    SendDataToServer(GetTxtname, GetInvoiceNumber, GetInvoiceAmount,GetInvoiceAmountPaid);

                } else {

                    Toast.makeText(CreditInvoice.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });

        confirm_credit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                String url = "http://forandroid3000.esy.es/latestcredit.php";
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
        });
    }

    public void GetCheckEditTextIsEmptyOrNot(){

        GetTxtname = txtName.getText().toString();
        GetInvoiceNumber = invoiceNumber.getText().toString();
        GetInvoiceAmount = invoiceAmount.getText().toString();
        GetInvoiceAmountPaid=invoiceAmountPaid.getText().toString();

        if(TextUtils.isEmpty(GetTxtname) || TextUtils.isEmpty(GetInvoiceNumber) || TextUtils.isEmpty(GetInvoiceAmount)||TextUtils.isEmpty(GetInvoiceAmountPaid))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void SendDataToServer(final String txtName, final String invoiceNumber, final String invoiceAmount,final String invoiceAmountPaid){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickName = txtName ;
                String QuickInvoiceNumber = invoiceNumber ;
                String QuickInvoiceAmount = invoiceAmount;
                String QuickinvoiceAmountPaid=invoiceAmountPaid;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("txtName", QuickName));
                nameValuePairs.add(new BasicNameValuePair("invoiceNumber", QuickInvoiceNumber));
                nameValuePairs.add(new BasicNameValuePair("invoiceAmount", QuickInvoiceAmount));
                nameValuePairs.add(new BasicNameValuePair("invoiceAmountPaid", QuickinvoiceAmountPaid));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(CreditInvoice.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(txtName, invoiceNumber, invoiceAmount,invoiceAmountPaid);
    }

}
