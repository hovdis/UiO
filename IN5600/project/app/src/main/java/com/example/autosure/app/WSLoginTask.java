package com.example.autosure.app;

import android.os.AsyncTask;
import android.util.Log;

public class WSLoginTask extends AsyncTask<Void, Void, String> {
    public AsyncResponse delegate = null;
    public final static String TAG = "Login Task";
    private String _username;
    private String _password;
    private int _sessionID;

    public WSLoginTask(String username, String password) {
        _username = username;
        _password = password;
    }

    @Override
    protected String doInBackground(Void ... params) {
        try {
            _sessionID = WSHelper.login(_username, _password);
            Log.d(TAG, "Login result => " + _sessionID);
            return _sessionID + "";

        } catch (Exception e) {
            Log.d(TAG, e.toString());
            return -1 + "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG,"Task finished");
        Log.d("Result: ", result);

        if (result != null) {
            delegate.processFinish(result);
        }
    }
}
