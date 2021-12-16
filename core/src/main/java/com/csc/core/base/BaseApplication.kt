package com.csc.core.base;

import android.app.Application;
import android.content.Context;

import com.csc.core.manage.AccountManager;
import com.csc.core.util.ToastUtil;

public class BaseApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // Toast初始化
        ToastUtil.init(this);
        AccountManager.init(this);
    }
}
