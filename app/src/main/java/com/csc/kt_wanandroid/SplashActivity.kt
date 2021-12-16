package com.csc.kt_wanandroid

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.KeyEvent
import com.csc.core.base.BaseActivity
import com.csc.core.util.IntentUtil

/**
 * @author chenshichun
 * 创建日期：2021/12/16
 * 描述：
 *
 */
class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        handler.sendEmptyMessageDelayed(0, 3000)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    IntentUtil.get()?.goActivity(this@SplashActivity, MainActivity::class.java)
                    finish()
                }
            }
        }
    }
}