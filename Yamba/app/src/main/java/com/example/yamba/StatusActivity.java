package com.example.yamba;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "StatusActivity";
    private EditText editStatus;
    private Button bottonTweet;
    private TextView textCount;
    private int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editStatus = (EditText) findViewById(R.id.editStatus);
        bottonTweet = (Button) findViewById(R.id.bottonTweet);
        textCount = (TextView) findViewById(R.id.textCount);

        bottonTweet.setOnClickListener(this);

        defaultTextColor = textCount.getTextColors().getDefaultColor();
        editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = 140 - editStatus.length();
                textCount.setText(Integer.toString(count));
                textCount.setTextColor(Color.GREEN);
                if(count < 10){
                    textCount.setTextColor(Color.RED);
                } else {
                    textCount.setTextColor(defaultTextColor);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String status = editStatus.getText().toString();
        Log.d(TAG,"onClicked with status " + status);

        new PostTask().execute(status);
    }

    private final class PostTask extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... params) {
            YambaClient yambaCloud =
            new YambaClient("student","password", "http://yamba.newcircle.com/api");
            try {
                yambaCloud.postStatus( params[0]);
                return "Successfully posted!";

            } catch (YambaClientException e) {
                e.printStackTrace();
                return "Failed to post to yamba service...";
            }
        }

        protected void onPostExecute (String result){
            super.onPostExecute(result);

            Toast.makeText(StatusActivity.this,result,Toast.LENGTH_LONG).show();
        }
    }
}
