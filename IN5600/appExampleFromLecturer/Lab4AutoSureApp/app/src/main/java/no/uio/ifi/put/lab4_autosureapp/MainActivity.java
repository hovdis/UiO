package no.uio.ifi.put.lab4_autosureapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "PUT_MainActivity";
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        resultView.setText("");
        // create separate AsynchTasks that behave differently for each request in different app
        new WSCallTask(resultView).execute();
    }
}
