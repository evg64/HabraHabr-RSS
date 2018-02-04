package com.eugene.shvabr.ui.rss_feed;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eugene.shvabr.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.fragment, new RssFeedFragment())
                                       .commit();
        }
    }
}
