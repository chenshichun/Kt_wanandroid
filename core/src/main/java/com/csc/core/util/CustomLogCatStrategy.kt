package com.csc.core.util

import android.util.Log
import com.orhanobut.logger.LogStrategy

/**
 * 适用于com.orhanobut:logger:2.2.0或更新版本
 * @Description: 解决日志打印错位不整齐的问题
 * @Author: YinYongbo
 * @Time: 2018/7/25 11:27
 */
class CustomLogCatStrategy : LogStrategy {
    override fun log(priority: Int, tag: String?, message: String) {
        Log.println(priority, randomKey() + tag, message)
    }

    private var last = 0
    private fun randomKey(): String {
        var random = (10 * Math.random()).toInt()
        if (random == last) {
            random = (random + 1) % 10
        }
        last = random
        return random.toString()
    }
}