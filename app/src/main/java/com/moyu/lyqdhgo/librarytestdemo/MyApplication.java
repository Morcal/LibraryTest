package com.moyu.lyqdhgo.librarytestdemo;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by lyqdhgo on 2016/5/13.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Logger.init();
    }
}
