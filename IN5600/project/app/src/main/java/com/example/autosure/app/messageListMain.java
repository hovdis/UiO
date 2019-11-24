package com.example.autosure.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import com.example.autosure.R;
import com.example.autosure.datamodel.ClaimMessage;

import java.util.ArrayList;

public class messageListMain extends AppCompatActivity implements AsyncResponse {
    private static final String TAG = "messageListMain";

    ArrayList<ClaimMessage> claimMessages = new ArrayList<ClaimMessage>();
    private int _sessionid;
    private int _claimID;
    private String newMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list_main);

        _sessionid = getIntent().getExtras().getInt("com.example.autosure.SESSIONID");
        _claimID = getIntent().getExtras().getInt("com.example.autosure.CLAIMID");

        callMessageList();
    }

    /*
    * Method for getting message list
    */
    protected void callMessageList(){
        WSClaimMessageTask wsMessages = new WSClaimMessageTask();
        wsMessages.delegate = this;
        wsMessages.execute(Integer.toString(_sessionid), Integer.toString(_claimID));
    }

    /*
     * Method for sending message
     */
    private void sendMessageTask(String message){

        WSSendMessage sendNewMsg = new WSSendMessage();
        sendNewMsg.delegate = this;
        sendNewMsg.execute(message);
    }

    /*
     * Method that initializes recycler view
     */
    private void initRecyclerview(){
        RecyclerView recycler = findViewById(R.id.recycler_view_messages);
        RecyclerViewAdapterMessages adapter = new RecyclerViewAdapterMessages(claimMessages, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    /*
     * Method for hiding keyboard
     */
    public void hideKeyboard() {
        Activity activity = this;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void processFinish(String output){
        initRecyclerview();
        Button messagesButton = (Button) findViewById(R.id.sendMessageButton);
        messagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = findViewById(R.id.newMessageText);
                newMessage = text.getText().toString();
                text.setText("");
                sendMessageTask(newMessage);
                hideKeyboard();
                claimMessages = new ArrayList<>();
                callMessageList();
            }
        });
    }

    /*
    * Private class for making WS call to get all messages to a claim
    */
    private class WSClaimMessageTask extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings) {
            try{
                claimMessages = (ArrayList) WSHelper.listClaimMessages(_sessionid, _claimID);
                return "success";
            }catch(Exception e){
                return "-1";
            }
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            delegate.processFinish(s);
        }
    }

    /*
    * Private class for WS call send message
    */
    private class WSSendMessage extends AsyncTask<String, Void, String>{
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings){
            String _message = strings[0];

            try{
                boolean didSubmit = WSHelper.submitNewMessage(_sessionid, _claimID, _message);
                System.out.println(didSubmit + "");
                return didSubmit+"";
            }catch(Exception e){
                return "-1";
            }
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            delegate.processFinish(s);
        }
    }
}
