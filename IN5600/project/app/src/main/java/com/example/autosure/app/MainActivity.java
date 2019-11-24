package com.example.autosure.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.autosure.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private int _sessionid;
    private static final String TAG = "MainActivity";
    String oldDate;
    DateFormat dateFormat;
    String _username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        oldDate = dateFormat.format(date);

        _sessionid = getIntent().getExtras().getInt("com.example.autosure.SESSIONID");

        Log.d("sessionid", _sessionid+"");
        setTitle("AutoSure");

        Button yourDetailsButton = (Button) findViewById(R.id.detailsBtn);
        yourDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailspage = new Intent(getApplicationContext(), Customer_details.class);
                detailspage.putExtra("com.example.autosure.SESSIONID", _sessionid);
                startActivity(detailspage);
            }
        });

        Button newClaimButton = (Button) findViewById(R.id.newClaimBtn);
        newClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent claimpage = new Intent(getApplicationContext(), newClaim.class);
                claimpage.putExtra("com.example.autosure.SESSIONID", _sessionid);
                startActivity(claimpage);
            }
        });

        Button historyButton = (Button) findViewById(R.id.historyBtn);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historypage = new Intent(getApplicationContext(), History.class);
                historypage.putExtra("com.example.autosure.SESSIONID", _sessionid);

                startActivity(historypage);
            }
        });

        Button goToLoginBtn = (Button) findViewById(R.id.logOutBtn);
        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: HELLOOOOOOOOO");
                callLogOutTask();
            }
        });

    }

    /*
    * Method for WS log out call
    */
    public void callLogOutTask(){
        WSLogOut logOutClass = new WSLogOut();
        logOutClass.delegate = this;
        logOutClass.execute("Log out");
    }

    private class WSLogOut extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;
        boolean loggedOut = false;
        @Override
        protected String doInBackground(String... strings) {
            try{
                loggedOut = WSHelper.logout(_sessionid);
                return loggedOut+"";
            }catch (Exception e){
                return loggedOut+"";
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
        if(output.equalsIgnoreCase("true")){
            Intent loginScreen = new Intent(getApplicationContext(), login.class);
            _sessionid = -1;
            _username = null;
            this.finish();
            startActivity(loginScreen);
        }
    }
}
