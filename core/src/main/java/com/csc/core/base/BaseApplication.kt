package com.csc.core.base

import android.app.Application
import android.content.Context
import com.csc.core.manage.AccountManager
import com.csc.core.util.ToastUtil

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        ToastUtil.init(this)
        AccountManager.init(this)
    }

    companion object {
        @JvmField
        var mContext: Context? = null
    }
}