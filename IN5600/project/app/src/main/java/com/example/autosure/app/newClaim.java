package com.example.autosure.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.autosure.R;

import java.util.List;

public class newClaim extends AppCompatActivity implements AsyncResponse {
    private int _sessionId;
    private List<String> _plateList;
    private String _availablePlates = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add a new claim");
        setContentView(R.layout.activity_new_claim);

        Intent intent = getIntent();

        _sessionId = intent.getExtras().getInt("com.example.autosure.SESSIONID");

        WSGetPlates wsGetPlates = new WSGetPlates();
        wsGetPlates.execute();

        Button newClaimBtn = (Button) findViewById(R.id.newClaimFileClaimBtn);
        newClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                EditText titleInputField = findViewById(R.id.newClaimTitleInput);
                EditText platenumber = findViewById(R.id.newClaimPlateText);
                EditText occurenceDate = findViewById(R.id.newClaimOccuranceDateInput);
                EditText descriptionField = findViewById(R.id.newClaimDescriptionInput);

                String title = titleInputField.getText().toString().trim();
                String plate = platenumber.getText().toString().trim();
                String occurrence = occurenceDate.getText().toString().trim();
                String description = descriptionField.getText().toString().trim();

                callTask(title, occurrence, plate, description);
            }
        });
    }

    /*
    * Method for calling task and checking if valid values
    */
    private void callTask(String title, String occurrence, String plate, String description) {
        TextView message = findViewById(R.id.messageField);

        if (title.length() == 0 || plate.length() == 0 || occurrence.length() == 0 || description.length() == 0) {
            message.setVisibility(TextView.VISIBLE);
        } else if(!_availablePlates.contains(plate)) {
            message.setVisibility(TextView.VISIBLE);
            message.setText("Plate number is not available.");
        } else {
            message.setVisibility(TextView.INVISIBLE);
            WSFileClaim claim = new WSFileClaim();
            claim.delegate = this;
            claim.execute(title, plate, occurrence, description);
        }
    }

    /*
     * Private class for making WS call to get plates
     */
    private class WSGetPlates extends AsyncTask<String, Void, String> {

        EditText platenumber = findViewById(R.id.newClaimPlateText);
        @Override
        protected String doInBackground(String... strings) {
            try{
                _plateList = WSHelper.listPlates(_sessionId);
                for (String p : _plateList) {
                    _availablePlates += p + ", ";
                }
                platenumber.setHint("Available: " + _availablePlates);

                return _availablePlates;

            } catch (Exception e) {
                return -1 + "";
            }
        }
    }
    /*
     * Private class for making WS call to file new claim
     */
    private class WSFileClaim extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings){

            String title = strings[0];
            String plate = strings[1];
            String occurrence = strings[2];
            String description = strings[3];

            try{
                boolean didSubmit = WSHelper.submitNewClaim(_sessionId, title, occurrence, plate, description);
                return didSubmit + "";

            } catch (Exception e) {
                return -1 + "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            delegate.processFinish(s);
        }
    }

    @Override
    public void processFinish(String output){
        TextView message = findViewById(R.id.messageField);
        if (output.contains("true")) {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainIntent.putExtra("com.example.autosure.SESSIONID", _sessionId);
            startActivity(mainIntent);
        } else {
            message.setText("Failed. Please try again.");
            message.setVisibility(TextView.VISIBLE);
        }
    }

}
