package com.androidapps.cm.geeksdictionary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// +----------------------------------------------------------------------
// | CreateTime: 16/5/9 
// +----------------------------------------------------------------------
// | Author:     cm
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class NewWordsDbHelper extends SQLiteOpenHelper {
    public static final String CREATE_NEW_WORDS = "CREATE TABLE new_words ("
            + "new_words_id integer primary key autoincrement, "
            + "word text not null UNIQUE, "
            + "meaning text not null)";
    private Context mContext;

    public NewWordsDbHelper(Context context){
        super(context,"new_words.db",null,1);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEW_WORDS);
        Log.d("createDB","createDB_NEW_WORDS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
