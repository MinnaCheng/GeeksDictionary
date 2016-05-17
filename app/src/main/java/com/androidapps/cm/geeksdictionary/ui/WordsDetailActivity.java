package com.androidapps.cm.geeksdictionary.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidapps.cm.geeksdictionary.R;

public class WordsDetailActivity extends ActionBarActivity {
private TextView tVNewWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_detail);
        initView();
    }

    private void initView() {
        tVNewWord = (TextView)findViewById(R.id.tVNewWord);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        tVNewWord.setText(word);
    }
}
