package com.androidapps.cm.geeksdictionary.presenter;

import android.content.Context;

import com.androidapps.cm.geeksdictionary.htmlUtil.AnalyzeJson;
import com.androidapps.cm.geeksdictionary.view.NetTip;

/**
 * Created by Administrator on 2016/5/3.
 */
public class WebSearchPresenter {
    private Context context;
    private AnalyzeJson mJson;
    NetTip netTip;


    public WebSearchPresenter(Context context,NetTip nettip) {
        this.context=context;
        this.netTip=nettip;
        mJson = new AnalyzeJson(context,nettip);
    }

    public void setContent(String content) {
        mJson.setContent(content);
    }
    public String analizingOfJson(){
        return mJson.analyzeOfJson();
    }

    /*public String sendToast(){
        return mJson.sendToast();
    }*/

}
