package com.androidapps.cm.geeksdictionary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// +----------------------------------------------------------------------
// | CreateTime: 16/5/9 
// +----------------------------------------------------------------------
// | Author:     cm
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class NewWordsModel implements INewWordsModel {
    private NewWordsDbHelper dbHelper;
    private Context mContext;
    private static SQLiteDatabase mSqlDb;
    private DatabasePath mDBPath;

    public NewWordsModel(Context context){
        this.mContext = context;
       // this.dbHelper = new NewWordsDbHelper(context);
        mDBPath = new DatabasePath(context);
        insertNewWordsDB();
    }


    @Override
    public List<NewWord> getNewWords() {
        List<NewWord> list=new ArrayList<>();
        Cursor cursor = mSqlDb.rawQuery("select word, meaning from new_words",null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                NewWord newWord=new NewWord();
                newWord.setWord(cursor.getString(cursor.getColumnIndex("word")));
                newWord.setMeaing(cursor.getString(cursor.getColumnIndex("meaning")));
                list.add(newWord);
            }
            cursor.close();
        }
        list.size();
        return list;
    }

    @Override
    public void insertWords(String English, String Chinese) {
       // String insertSQL = "insert into new_words (word,meaning) values(?,?)";
       // mSqlDb.execSQL(insertSQL,new String[]{English,Chinese});
        mSqlDb.execSQL("insert into new_words (word,meaning) values('"+English+"', '"+Chinese+"')");
    }


    @Override
    public void deleteWords(String English) {
        String deleteSQL = "delete from new_words where word = ?";
        mSqlDb.execSQL(deleteSQL,new String[]{English});
    }

    public void insertNewWordsDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSqlDb = mDBPath.openNewWordsDatabase();
            }
        }).start();
    }
}
