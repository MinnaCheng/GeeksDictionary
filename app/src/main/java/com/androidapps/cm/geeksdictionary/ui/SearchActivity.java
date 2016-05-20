package com.androidapps.cm.geeksdictionary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.data.LexiconDbHelper;
import com.androidapps.cm.geeksdictionary.presenter.NewWordsPresenter;
import com.androidapps.cm.geeksdictionary.presenter.SearchPresenter;
import com.androidapps.cm.geeksdictionary.presenter.WebSearchPresenter;
import com.androidapps.cm.geeksdictionary.view.NetTip;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener, ISearchView, NetTip {
    private EditText mETSearch;
    private ImageButton mIBSearch;
    private TextView mTVWord;
    private TextView mTVWordExp;
    private TextView mTVWordWebExp;
    private Button mBtnNewWords;
    private Button mBtnSearchWebMean;
    private ScrollView mSVWordExp;
    private ScrollView mSVWordWebExp;
    private LexiconDbHelper mDBHelper;
    private SearchPresenter presenter;
    private InputMethodManager mInputManager;
    private int insertStatus = 0;//1为插入成功,0为插入失败
    WebSearchPresenter webSearchPresenter;
    NewWordsPresenter newWordsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        webSearchPresenter = new WebSearchPresenter(this, this);
        presenter = new SearchPresenter(this);
        newWordsPresenter = new NewWordsPresenter(this);
    }


    private void initView() {
        mETSearch = (EditText) findViewById(R.id.searchET);
        mIBSearch = (ImageButton) findViewById(R.id.iBSearch);
        mTVWord = (TextView) findViewById(R.id.tVWord);
        mTVWordExp = (TextView) findViewById(R.id.tVWordExp);
        mTVWordWebExp = (TextView) findViewById(R.id.tVWebMeaning);
        mSVWordExp = (ScrollView) findViewById(R.id.scrollView);
        mSVWordWebExp = (ScrollView) findViewById(R.id.scrollView2);
        mBtnNewWords = (Button) findViewById(R.id.btnAddToNewWords);
        mBtnSearchWebMean = (Button) findViewById(R.id.btnSearchWeb);
        mBtnNewWords.setOnClickListener(this);
        mBtnSearchWebMean.setOnClickListener(this);
        mIBSearch.setOnClickListener(this);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus()
                    .getWindowToken() != null) {
                mInputManager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iBSearch:
                //搜索单词本地释义
                clearText();
                getResult();
                break;
            case R.id.btnSearchWeb:
                //搜索网络释义
                setWebMean(mTVWord.getText().toString());
                break;
            case R.id.btnAddToNewWords:
                //加入生词本操作
                //addOrDeleteNewWords();
                insertNewWords();
                break;


        }
    }

    private void addOrDeleteNewWords() {
        String text = mBtnNewWords.getText().toString();
        if (text.equals(R.string.addToNewWords)) {
            Log.d("----", "addNewWords");
            insertNewWords();
            mBtnNewWords.setText(R.string.delete_new_words);
        } else if (text.equals(R.string.delete_new_words)) {
            Log.d("----", "deleteNewWords");
            deleteNewWords();
            mBtnNewWords.setText(R.string.addToNewWords);
        }

    }

    private void deleteNewWords() {
        if (insertStatus == 1) {
            Log.d("----", "deleteWords");
            String word = mTVWord.getText().toString();
            newWordsPresenter.deleteWords(word);
            Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
        }
    }

    private void insertNewWords() {
        String word = mTVWord.getText().toString();
        String meaning = presenter.loadChinese(word);
        if (null != meaning && !meaning.isEmpty()) {
            try {
                newWordsPresenter.insertWords(word, meaning);
            } catch (Exception e) {
                Toast.makeText(this, R.string.addFailed, Toast.LENGTH_LONG).show();
                return;
            }
            insertStatus = 1;
            Toast.makeText(this, R.string.add_new_words_succeed, Toast.LENGTH_SHORT).show();
        } else {
            insertStatus = 0;
        }

    }

    private void clearText() {
        String webText = mTVWordWebExp.getText().toString();
        if (webText != null && !webText.isEmpty()) {
            mTVWordWebExp.setText("");
        }
    }

    private void getResult() {
        String English = getWords();
        if (English.isEmpty() || English == null) {
            Toast.makeText(this, R.string.notEmpty, Toast.LENGTH_SHORT)
                    .show();
        } else {
            setEnglish(English);
            setChinese(English);
        }
    }

    public String getWords() {
        return mETSearch.getText().toString().trim();
    }

    @Override
    public void setChinese(String English) {

        String Chinese = presenter.loadChinese(English);
        mTVWordExp.setText(Chinese);
    }

    @Override
    public void setEnglish(String English) {
        String EnglishLowerCase = English.toLowerCase();
        mTVWord.setText(EnglishLowerCase);
    }

    @Override
    public void setWebMean(String English) {
        setWords(English);
        String text = webSearchPresenter.analizingOfJson();
        if (null == text || text.isEmpty() || text.equals("")) {
            mTVWordWebExp.setText(R.string.nowebMean);
            Toast.makeText(SearchActivity.this, R.string.nowebMean, Toast.LENGTH_SHORT).show();
        }
        mTVWordWebExp.setText(text);
    }


    public void setWords(String words) {
        String content = words;
        if (content.isEmpty()) {
            Toast.makeText(this, R.string.notEmpty, Toast.LENGTH_LONG).show();
        }
        webSearchPresenter.setContent(content);
    }

    @Override
    public void show(String msg) {
        Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
