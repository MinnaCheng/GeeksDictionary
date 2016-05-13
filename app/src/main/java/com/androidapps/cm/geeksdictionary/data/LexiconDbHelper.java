package com.androidapps.cm.geeksdictionary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 只在第一次创建数据库使用
 */
public class LexiconDbHelper extends SQLiteOpenHelper {
    public static final String CREATE_LEXICON = "CREATE TABLE lexicon ("
            + "id char(4) primary key , "
            + "word text, "
            + "meaning text)";
    private Context mContext;
    public LexiconDbHelper(Context context) {
        super(context, "lexicon.db", null, 1);
        mContext = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LEXICON);
        Log.d("createDB","createDB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+ "lexicon";
        db.execSQL(sql);
    }
}
