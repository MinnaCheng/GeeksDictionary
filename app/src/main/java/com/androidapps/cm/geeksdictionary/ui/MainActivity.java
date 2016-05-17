package com.androidapps.cm.geeksdictionary.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.utils.Constants;

public class MainActivity extends TabActivity {
   // private NewWordsDbHelper newWordsDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // newWordsDbHelper = new NewWordsDbHelper(this);
       // newWordsDbHelper.getWritableDatabase();
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this)
                .inflate(R.layout.activity_main, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec(Constants.TAB_SEARCH)
                .setIndicator(getString(R.string.searchWord))
                .setContent(new Intent(this, SearchActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(Constants.TAB_NEW_WORDS)
                .setIndicator(getString(R.string.newWordsBook))
                .setContent(new Intent(this, NewWordsActivity.class)));
        tabHost.setCurrentTab(0);

    }
}
