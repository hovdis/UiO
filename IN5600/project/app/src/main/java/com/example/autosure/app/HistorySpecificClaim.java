package com.example.autosure.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.autosure.R;
import com.example.autosure.datamodel.ClaimRecord;

public class HistorySpecificClaim extends AppCompatActivity implements AsyncResponse {
    private int _sessionid;
    private int _claimID;
    private String _title;
    private String _plate;
    private String _description;
    private String _claimStatus;
    private ClaimRecord specificClaim;

    TextView claimPlateSpecificTextView;
    TextView claimDescSpecificTextView;
    TextView claimStatusSpecificTextView;
    TextView claimDateSpecificTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_specific_claim);

        Intent intent = getIntent();

        _claimID = intent.getExtras().getInt("com.example.autosure.POSITION");
        _sessionid = intent.getExtras().getInt("com.example.autosure.SESSIONID");
        _title = intent.getExtras().getString("com.example.autosure.TITLE");

        TextView claimIDTextView = (TextView) findViewById(R.id.claimIDSpecific);
        TextView claimTitleSpecificTextView = (TextView) findViewById(R.id.claimTitleSpecific);
        claimPlateSpecificTextView = (TextView) findViewById(R.id.claimPlateSpecific);
        claimDescSpecificTextView = (TextView) findViewById(R.id.claimDescSpecific);
        claimStatusSpecificTextView = (TextView) findViewById(R.id.claimStatusSpecific);
        claimDateSpecificTextView = (TextView) findViewById(R.id.claimSateSpecific);

        claimIDTextView.setText(_claimID+"");
        claimTitleSpecificTextView.setText(_title);

        if(_plate == null){
            WSSpecificClaim wsSpecific = new WSSpecificClaim();
            wsSpecific.delegate = this;
            wsSpecific.execute(Integer.toString(_sessionid), Integer.toString(_claimID));
        }
    }

    /*
    * Private class for calling the WS to get information about claim
    */
    private class WSSpecificClaim extends AsyncTask<String, Void, String>{
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings){
            String sessionID = strings[0];
            String claimID = strings[1];

            try{
                specificClaim = WSHelper.getClaimInfo(Integer.parseInt(sessionID), Integer.parseInt(claimID));
                return specificClaim.toString();

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
        claimPlateSpecificTextView.setText(specificClaim.getPlate());
        claimDescSpecificTextView.setText(specificClaim.getDescription());
        claimStatusSpecificTextView.setText(specificClaim.getStatus());
        claimDateSpecificTextView.setText(specificClaim.getOccurrenceDate());

        Button messagesButton = (Button) findViewById(R.id.messagesButton);
        messagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messagesPage = new Intent(getApplicationContext(), messageListMain.class);
                messagesPage.putExtra("com.example.autosure.SESSIONID", _sessionid);
                messagesPage.putExtra("com.example.autosure.CLAIMID", _claimID);
                startActivity(messagesPage);
            }
        });
    }

}
