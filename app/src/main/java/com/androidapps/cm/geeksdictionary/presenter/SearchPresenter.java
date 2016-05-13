package com.androidapps.cm.geeksdictionary.presenter;

import android.content.Context;

import com.androidapps.cm.geeksdictionary.data.IUserModel;
import com.androidapps.cm.geeksdictionary.data.UserModel;
//import com.androidapps.cm.geeksdictionary.ui.ISearchView;

/**
 * Created by Administrator on 2016/5/2.
 */
public class SearchPresenter {
    private IUserModel iUserModel;
    private Context context;

    public SearchPresenter(Context context){
        iUserModel = new UserModel(context);
    }
    public String loadChinese(String English){
        String Chinese = iUserModel.loadChinese(English);
        return Chinese;
    }

}
