package com.androidapps.cm.geeksdictionary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.androidapps.cm.geeksdictionary.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/5/3.
 */
public class DatabasePath {
    private Context context;
    public DatabasePath(Context context){
        this.context=context;
    }

    //定义数据库的存放路径
    private final String DATABASE_PATH = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/lexicon";
    private final String DATABASE_FILENAME = "lexicon.db";
    private final String DATABASE_FILENAME_WORDS = "new_words.db";

    public SQLiteDatabase openDatabase() {
        try {
            // 获得dictionary.db文件的绝对路径
            String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
            File dir = new File(DATABASE_PATH);
            // 如果/sdcard/dictionary目录中不存在，创建这个目录
            if (!dir.exists())
                dir.mkdir();
            // 如果在/sdcard/dictionary目录中不存在
            // dictionary.db文件，则从res\raw目录中复制这个文件到
            // SD卡的目录（/sdcard/dictionary）
            if (!(new File(databaseFilename)).exists()) {
                // 获得封装dictionary.db文件的InputStream对象
                InputStream is = context.getResources().openRawResource(
                        R.raw.lexicon);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                // 开始复制dictionary.db文件
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                //关闭文件流
                fos.close();
                is.close();
            }
            // 打开/sdcard/dictionary目录中的dictionary.db文件
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                    databaseFilename, null);
            return database;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public SQLiteDatabase openNewWordsDatabase() {
        try {
            String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME_WORDS;
            File dir = new File(DATABASE_PATH);
            if (!dir.exists())
                dir.mkdir();
            if (!(new File(databaseFilename)).exists()) {
                InputStream is = context.getResources().openRawResource(
                        R.raw.new_words);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
                    databaseFilename, null);
            return database;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

