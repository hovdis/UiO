package com.example.autosure.app;

import com.example.autosure.R;
import com.example.autosure.datamodel.Customer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/*
* Activity for showing customer details
*/
public class Customer_details extends AppCompatActivity implements AsyncResponse {

    private static final String TAG = "HistorySpecificClaim";
    private int _sessionId;
    private Customer _customer;
    private String _customerName;
    private int _fiscalNumber;
    private String _address;
    private String _dateOfBirth;
    private int _policyNumber;

    TextView nameField;
    TextView fiscalField;
    TextView policyField;
    TextView birthField;
    TextView addressField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Main");
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        _sessionId = intent.getExtras().getInt("com.example.autosure.SESSIONID");

        nameField = (TextView) findViewById(R.id.nameField);
        fiscalField = (TextView) findViewById(R.id.fiscalNumberField);
        policyField = (TextView) findViewById(R.id.policyNumberField);
        birthField = (TextView) findViewById(R.id.birthDateField);
        addressField = (TextView) findViewById(R.id.addressField);


        if(_customer == null){
            WSGetCustomer getCustomer = new WSGetCustomer();
            getCustomer.delegate = this;
            getCustomer.execute(Integer.toString(_sessionId));
        }
    }

    /*
    * Method called when async task is finished
    * Sets all the fields
    */
    @Override
    public void processFinish(String output){
        Log.d(TAG, "processFinish: Started.");
        if (_customer != null) {
            nameField.setText(_customerName);
            fiscalField.setText(_fiscalNumber+"");
            policyField.setText(_policyNumber+"");
            birthField.setText(_dateOfBirth);
            addressField.setText(_address);
        }
    }

    /*
    * Private class for calling the WS to get customer info
    */
    private class WSGetCustomer extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings){
            Log.d(TAG, "doInBackground: Started in background");
            _sessionId = Integer.parseInt(strings[0]);

            try{
                _customer = WSHelper.getCustomerInfo(_sessionId);
                Log.d(TAG, "doInBackground: GetCustomerInfo result => " + _customer);


                _customerName = _customer.getName();
                _fiscalNumber = _customer.getFiscalNumber();
                _policyNumber = _customer.getPolicyNumber();
                _dateOfBirth = _customer.getDateOfBirth();
                _address = _customer.getAddress();

                return _customer.toString();

            } catch (Exception e) {
                Log.d(TAG, "doInBackground: " + e.toString());
                return -1 + "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            delegate.processFinish(s);
        }
    }
}
