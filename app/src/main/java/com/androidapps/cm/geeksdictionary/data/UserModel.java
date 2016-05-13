package com.androidapps.cm.geeksdictionary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapps.cm.geeksdictionary.R;

/**
 * Created by cm.
 */
public class UserModel implements IUserModel {
    private LexiconDbHelper dbHelper;
    private Context context;
    private DatabasePath mDatabasePath;
    private SQLiteDatabase mSqlDB;

    public UserModel(Context context) {
        this.context = context;
        // this.dbHelper = new LexiconDbHelper(context);
        mDatabasePath = new DatabasePath(context);
        insertDb();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String loadChinese(String English) {
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        //根据表的中的英文来查询中文
        String str = "";
        Cursor cursor = mSqlDB.rawQuery("select meaning from Lexicon where word='" + English + "' COLLATE NOCASE", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                str = cursor.getString(cursor.getColumnIndex("meaning"));
            }
            cursor.close();
        }
        if (str.equals(null) || str.equals("")) {
            str = context.getString(R.string.notFoundWord);
        }
        return str;
    }

    public void insertDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSqlDB = mDatabasePath.openDatabase();
            }
        }).start();
    }


    @Override
    public String loadEnglish(String Chinese) {
        return null;
    }

}
