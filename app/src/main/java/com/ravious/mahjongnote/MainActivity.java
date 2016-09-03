package com.ravious.mahjongnote;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (savedInstanceState == null) {
            MainResultFragment fragmentMainResult = new MainResultFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragmentMainResult).commit();
        }


    }

    public void onNewGameButtonClicked() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        GameFragment fragment = new GameFragment();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
