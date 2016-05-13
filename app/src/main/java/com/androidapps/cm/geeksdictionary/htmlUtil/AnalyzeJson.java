package com.androidapps.cm.geeksdictionary.htmlUtil;

import android.content.Context;
import android.util.Log;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.utils.Constants;
import com.androidapps.cm.geeksdictionary.view.NetTip;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cm.
 */
public class AnalyzeJson {
    private static final String TAG = "AnalyzeJson";
    private String YouDaoBaseUrl = "http://fanyi.youdao.com/openapi.do";
    private String YouDaoKeyFrom = "GeeksDictionary";
    private String YouDaoKey = "847229755";
    private String YouDaoType = "data";
    private String YouDaoDoctype = "json";
    private String YouDaoVersion = "1.1";
    private Context context;
    private String mContent;
    //  public ToastTextBean mToastTextBean;
    private String mWebText;
    NetTip netTip;
    static String toastText="";

    public AnalyzeJson(Context context,NetTip nettip) {
        this.context = context;
        this.netTip=nettip;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String analyzeOfJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String YouDaoSearchContent = mContent;
                String YouDaoUrl = YouDaoBaseUrl + "?keyfrom=" + YouDaoKeyFrom + "&key=" + YouDaoKey
                        + "&type=" + YouDaoType + "&doctype=" + YouDaoDoctype + "&type=" + YouDaoType
                        + "&version=" + YouDaoVersion + "&q=" + YouDaoSearchContent;
                try {
                    mWebText= analyzeOfJsonDetail(YouDaoUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return mWebText;
    }

    private String analyzeOfJsonDetail(String youDaoUrl) throws Exception {

        ArrayList<String> list = new ArrayList<>();
        // 第一步，创建HttpGet对象
        HttpGet httpGet = new HttpGet(youDaoUrl);
        // 第二步，使用execute方法发送HTTP GET请求，并返回HttpResponse对象
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == Constants.HTTP_OK) {
            // 第三步，使用getEntity方法活得返回结果
            String result = EntityUtils.toString(httpResponse.getEntity());
            Log.d("result:", result);
            JSONArray jsonArray = new JSONArray("[" + result + "]");
            String message = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    String errorCode = jsonObject.getString("errorCode");
                    if (errorCode.equals("20")) {
                        toastText=context.getString(R.string.wordsToLong);
                        Log.i(TAG, context.getString(R.string.wordsToLong));
                    } else if (errorCode.equals("30 ")) {
                            toastText = context.getString(R.string.canNotTranslate);
                        Log.i(TAG, context.getString(R.string.canNotTranslate));
                    } else if (errorCode.equals("40")) {
                        toastText = context.getString(R.string.notSupportType);
                        Log.i(TAG, context.getString(R.string.notSupportType));
                    } else if (errorCode.equals("50")) {
                        toastText=context.getString(R.string.uselessKey);
                        Log.i(TAG, context.getString(R.string.uselessKey));
                    } else {
                        // 要翻译的内容
                       // String query = jsonObject.getString("query");
                       // message = query;
                        // 翻译内容
                        //String translation = jsonObject.getString("translation");
                       // message +=translation;
                        /*if (jsonObject.has("basic")) {
                            JSONObject basic = jsonObject.getJSONObject("basic");
                            if (basic.has("phonetic")) {
                                String phonetic = basic.getString("phonetic");
                                message += "\n\t" + phonetic;
                            }
                            if (basic.has("phonetic")) {
                                String explains = basic.getString("explains");
                                message += "\n\t" + explains;
                            }
                        }*/
                        if (jsonObject.has("web")) {
                            String web = jsonObject.getString("web");
                            JSONArray webString = new JSONArray("[" + web + "]");
                            message += "\n网络释义：";
                            JSONArray webArray = webString.getJSONArray(0);
                            int count = 0;
                            while (!webArray.isNull(count)) {

                                if (webArray.getJSONObject(count).has("key")) {
                                    String key = webArray.getJSONObject(count).getString("key");
                                    message += "\n\t<" + (count + 1) + ">" + key;
                                }
                                if (webArray.getJSONObject(count).has("value")) {
                                    String value = webArray.getJSONObject(count).getString("value");
                                    message += "\n\t   " + value;
                                }
                                count++;
                            }
                        }
                    }
                }
            }
            return message.toString();
        } else {
            toastText = context.getString(R.string.extractError);
            Log.i(TAG, context.getString(R.string.extractError));
        }
        netTip.show(toastText);
        return context.getString(R.string.webFailed);
    }
}
