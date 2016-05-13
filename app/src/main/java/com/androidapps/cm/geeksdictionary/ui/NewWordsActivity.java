package com.androidapps.cm.geeksdictionary.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.adapter.NewWordsAdapter;
import com.androidapps.cm.geeksdictionary.data.NewWord;
import com.androidapps.cm.geeksdictionary.presenter.NewWordsPresenter;

import java.util.ArrayList;
import java.util.List;

public class NewWordsActivity extends ActionBarActivity {
    NewWordsPresenter newWordsPresenter;
    List<NewWord> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_words);
        newWordsPresenter = new NewWordsPresenter(this);
        list = newWordsPresenter.getNewWords();
        for (NewWord newWord : list) {
            String newWords = newWord.toString();
            Log.d("new Word", newWords);
        }
        NewWordsAdapter newWordsAdapter = new NewWordsAdapter(
                NewWordsActivity.this, R.layout.new_words_item, list);
        ListView listView = (ListView)findViewById(R.id.listViewWords);
        listView.setAdapter(newWordsAdapter);
    }
}
