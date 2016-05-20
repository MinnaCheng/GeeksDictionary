package com.androidapps.cm.geeksdictionary.presenter;

import android.content.Context;

import com.androidapps.cm.geeksdictionary.data.INewWordsModel;
import com.androidapps.cm.geeksdictionary.data.NewWord;
import com.androidapps.cm.geeksdictionary.data.NewWordsModel;

import java.util.List;

// +----------------------------------------------------------------------
// | CreateTime: 16/5/9 
// +----------------------------------------------------------------------
// | Author:     cm

public class NewWordsPresenter {
    private INewWordsModel iNewWordsModel;
    private Context mContext;

    public NewWordsPresenter(Context context) {
        this.mContext = context;
        iNewWordsModel = new NewWordsModel(context);
    }

    public void insertWords(String English, String Chinese) {
        iNewWordsModel.insertWords(English, Chinese);
    }

    public void deleteWords(String English) {
        iNewWordsModel.deleteWords(English);
    }

    public List<NewWord> getNewWords() {
        return iNewWordsModel.getNewWords();
    }
}
