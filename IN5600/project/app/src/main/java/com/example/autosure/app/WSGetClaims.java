package com.example.autosure.app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.autosure.R;
import com.example.autosure.datamodel.ClaimItem;

import java.util.List;

public class WSGetClaims extends AsyncTask<Void, Void, String> {
    public AsyncResponse delegate = null;
    public final static String TAG = "Get claims";
    private int _sessionid;
    private ListView historyListView;
    private List<ClaimItem> claims;
    private Intent historyIntent;
    private String totClaimItems = "";
    Context con;
    private String[] claimTitle;
    private int[] claimID;

    public WSGetClaims(int _sessionid, ListView historyListView, Context con){
        this.con = con;
        this._sessionid = _sessionid;
        this.historyListView = historyListView;
    }

    @Override
    protected String doInBackground(Void... params) {
        try{
            claims = WSHelper.listClaims(_sessionid);
            Log.d(TAG, "getClaims result => " + claims);

            makeArrays();


            for(ClaimItem i : claims){
                totClaimItems += i.toString();
            }
            return totClaimItems;
        }catch (Exception e){
            Log.d(TAG, e.toString());
            return -1 + "";
        }
    }


    @Override
    protected void onPostExecute(String result){
        Log.d(TAG, "getClaims finished");
        Log.d(TAG, totClaimItems);

        if(result != null){
            delegate.processFinish(result);
        }
    }

    //Makes the arrays from List<ClaimItems>
    public void makeArrays(){
        claimTitle = new String[claims.size()];
        claimID = new int[claims.size()];

        for(int i = 0; i< claims.size();i++){
            claimTitle[i] = claims.get(i).getTitle();
            claimID[i] = claims.get(i).getId();
        }
    }


}