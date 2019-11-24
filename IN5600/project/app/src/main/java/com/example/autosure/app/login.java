package com.example.autosure.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.autosure.R;

public class login extends AppCompatActivity implements AsyncResponse {

    private String _username;
    private String _password;
    private int _sessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button goToMainBtn = (Button) findViewById(R.id.loginButton);
        goToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameInputField = findViewById(R.id.inUsername);
                EditText userPassInputField = findViewById(R.id.inPassword);
                _username = userNameInputField.getText().toString();
                _password = userPassInputField.getText().toString();

                callLogin();
            }
        });
    }

    /*
    * Calls login task
    * */
    public void callLogin() {
        WSLoginTask loginTask = new WSLoginTask(_username, _password);
        loginTask.delegate = this;

        loginTask.execute();
    }

    // Disable the back button on login screen
    @Override
    public void onBackPressed() {
        // If you uncomment this line under, the back button works again.
        // super.onBackPressed();
    }

    /*
    * Recieves result from async WSLoginTask class
    * */
    @Override
    public void processFinish(String output){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);

        Resources res = getResources();

        if (output != null) {
            _sessionID = Integer.parseInt(output);
            Log.d("SessionID: ", _sessionID+"");
            if(_sessionID != -1 && _sessionID != 0) {
                mainIntent.putExtra("com.example.autosure.SESSIONID", _sessionID);
                _sessionID = -1;
                _username = null;
                _password = null;
                startActivity(mainIntent);
            } else {
                TextView failedToLoginText = (TextView) findViewById(R.id.wrongLoginCredentials);
                failedToLoginText.setVisibility(TextView.VISIBLE);
            }
        }
    }
}
