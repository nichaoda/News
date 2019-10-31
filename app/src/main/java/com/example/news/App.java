package com.example.news;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.news.greendao.DaoMaster;
import com.example.news.greendao.DaoSession;

public class App extends Application {
    private static final String DB_NAME = "news.db";

    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    /**
     * 初始化greenDAO
     */
    private void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        sDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }
}
