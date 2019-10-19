package com.example.yamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.FragmentActivity;

public class StatusActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            StatusFragment fragment = new StatusFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();
        }


        //setContentView(R.layout.new_activity_status);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//
//            case R.id.action_settings:
//                startActivity(new Intent(this, SettingsActivity.class));
//                return true;
//            case R.id.action_tweet:
//                startActivity(new Intent("com.example.yamba.action.tweet"));
//                return true;
//            default:
//                return false;
//
//
//        }
//    }

}