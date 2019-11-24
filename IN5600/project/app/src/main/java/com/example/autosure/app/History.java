package com.example.autosure.app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.autosure.R;
import com.example.autosure.datamodel.ClaimItem;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity implements AsyncResponse {

    private int _sessionid;
    private static ArrayList<String> claimTitle = new ArrayList<>();
    private static ArrayList<Integer> claimID = new ArrayList<>();
    private List<ClaimItem> claims;
    private static final String TAG = "History";
    private String totClaimItems = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History");
        setContentView(R.layout.activity_history);
        _sessionid = getIntent().getExtras().getInt("com.example.autosure.SESSIONID");
        claimID = new ArrayList<Integer>();
        claimTitle = new ArrayList<String>();
        WSClaimTask wsClaims = new WSClaimTask();
        wsClaims.delegate = this;
        wsClaims.execute(Integer.toString(_sessionid));
    }

    /*
    * Private class for calling the WS to get all claims
    */
    private class WSClaimTask extends AsyncTask<String, Void, String> {
        AsyncResponse delegate = null;

        @Override
        protected String doInBackground(String... strings) {
            try{
                claims = WSHelper.listClaims(_sessionid);

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            delegate.processFinish(s);
        }
    }


    /*
    * Method that fills the arrays (claimTitle and claimID)
    */
    public void makeArrays(){
        Log.d(TAG, "makeArrays: preparing bitmaps.");
        for(int i = 0; i<claims.size();i++){
            claimTitle.add(claims.get(i).getTitle());
            claimID.add(claims.get(i).getId());
        }
    }


    @Override
    public void processFinish(String output){
        makeArrays();
        initRecyclerView();
    }

    /*
    * Method that initializes recycler view
    */
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(claimID, claimTitle, this, _sessionid);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}