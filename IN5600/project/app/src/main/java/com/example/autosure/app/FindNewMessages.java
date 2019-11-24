package com.example.autosure.app;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.autosure.datamodel.ClaimItem;
import com.example.autosure.datamodel.ClaimMessage;

import java.util.ArrayList;

public class FindNewMessages extends Service implements AsyncResponse {
    private static final String TAG = "FindNewMessages";
    private String _sessionid;
    private ArrayList<ClaimItem> newClaims = new ArrayList<>();

    private ArrayList<ClaimMessage> tempMessages = new ArrayList<>();

    private String oldDate;
    private String newDate;
    private int newFormattedDate;

    private int _thisClaimID;

    /* Constructor */
    public FindNewMessages() { }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        _sessionid = intent.getStringExtra("com.example.autosure.SESSIONID");
        oldDate = intent.getStringExtra("com.example.autosure.OLDDATE");
        _thisClaimID = 1;
        callFillNewClaimItems();

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    * Method checks if there is any new messages
    */
    public int findIfNewMessage(){
        int found;
        for(int i = 0; i<tempMessages.size(); i++){
            newDate = tempMessages.get(i).getDate();

            /* Changes format to fit with comparison */
            String[] dateArray = newDate.split("-");
            String[] yearArray = dateArray[2].split(" ");
            String[] timeArray = yearArray[1].split(":");
            int newFormattedTime = Integer.parseInt(timeArray[0] + timeArray[0]);
            int oldFormattedTime = Integer.parseInt(oldDate.substring(8,12));
            int oldFormattedDate = Integer.parseInt(oldDate.substring(0,8));
            newFormattedDate = Integer.parseInt(yearArray[0] + dateArray[1] + dateArray[0]);

            if(newFormattedDate > oldFormattedDate){
                found = newClaims.get(i).getId();
                return found;
            }else if(newFormattedDate == oldFormattedDate && newFormattedTime > oldFormattedTime) {
                found = newClaims.get(i).getId();
                return found;
            }
        }
        return -1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
    * Method that gets all the claim items
    */
    public void callFillNewClaimItems(){
        WSClaimItemTask itemTask = new WSClaimItemTask();
        itemTask.delegate = this;
        itemTask.execute(_sessionid);
    }

    /*
    * Method that gets all the messages of a claim
    */
    public void callFillNewClaimMessage(){
        WSClaimMessageTask messageTask = new WSClaimMessageTask();
        messageTask.delegate = this;
        messageTask.execute(_sessionid);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*
    * Method that prints a new toast message
    */
    public void toastNewMessage(final int claimID){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(FindNewMessages.this.getApplicationContext(),"New message in claimID: " + claimID,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * Private class for calling the WS to get messages
     */
    private class WSClaimMessageTask extends AsyncTask<String, Void, String>{
        AsyncResponse delegate = null;
        int found;

        @Override
        protected String doInBackground(String... strings) {
            try{
                tempMessages = (ArrayList) WSHelper.listClaimMessages(Integer.parseInt(_sessionid), _thisClaimID);
                found = findIfNewMessage();
                if(found != -1){
                    toastNewMessage(found);
                }
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
    * Private class for calling the WS to get claim items
    */
    private class WSClaimItemTask extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings) {
            try {
                newClaims = (ArrayList) WSHelper.listClaims(Integer.parseInt(_sessionid));
                return "done";
            } catch (Exception e) {
                Log.d(TAG, e.toString());
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
        if(output.equalsIgnoreCase("done")){
            callFillNewClaimMessage();
        }else if(output.equalsIgnoreCase("success")){
            if(_thisClaimID >= newClaims.size()){

            }else{
                _thisClaimID += 1;
                callFillNewClaimMessage();
            }
        }
        Log.d(TAG, "processFinish: started and ended. => " + output);
    }
}