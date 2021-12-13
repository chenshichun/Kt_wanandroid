package com.csc.kt_wanandroid.http

import android.text.TextUtils
import com.blankj.utilcode.util.NetworkUtils
import com.csc.core.base.BaseConstant
import com.csc.core.manage.AccountManager
import com.socks.library.KLog
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * Created by ex-lvchaofeng001 on 2017/8/10.
 */
class NetworkInterceptor : Interceptor {
    var request: Request? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        request = chain.request()
        val builder = chain.request().newBuilder()
        val token = AccountManager.getToken()
        val cookies = AccountManager.getCookie()

     /*   if (cookies != null) {
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
            }
        }*/
        request = if (!TextUtils.isEmpty(token)) {
            builder
                .header(BaseConstant.ACCESS_TOKEN, "Bearer $token")
                .build()
        } else {
            builder
                .build()
        }
        return if (NetworkUtils.isAvailable()) {
            request = request!!.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .addHeader("User-Agent", "android")
                .header("Cache-Control", "public,max-age=" + CACHE_AGE_SEC)
                .method(request!!.method(), request!!.body()) // 未加密以后的body
                .build()
            val response = chain.proceed(request)
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .addHeader("User-Agent", "android")
                .header("Cache-Control", "public,max-age=" + CACHE_AGE_SEC)
                .build()
        } else {
            request = request!!.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .addHeader("User-Agent", "android")
                .header("Cache-Control", "public,only-if-cached,max-stale=" + CACHE_STALE_SEC)
                .method(request!!.method(), request!!.body()) // 未加密以后的body
                .build()
            chain.proceed(request).newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .addHeader("User-Agent", "android")
                .header("Cache-Control", "public,only-if-cached,max-stale=" + CACHE_STALE_SEC)
                .build()
        }
    }

    /**
     * 获取常规post请求参数
     */
    @Throws(IOException::class)
    private fun getParamContent(body: RequestBody): String {
        val buffer = Buffer()
        body.writeTo(buffer)
        return buffer.readUtf8()
    }

    private fun logRequest(fromObject: JSONObject?, treeMap: MutableMap<String, String?>) {
        if (fromObject != null) {
            val it: Iterator<*> = fromObject.keys()
            while (it.hasNext()) {
                val key = it.next().toString()
                var value: String? = null
                try {
                    value = fromObject[key].toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                treeMap[key] = value
            }
        }
    }

    companion object {
        private const val CACHE_STALE_SEC = (60 * 60 * 24 * 7).toLong()
        private const val CACHE_AGE_SEC: Long = 0

        @Synchronized
        fun setCommonParam(commonParams: Map<String, String?>?) {
            if (commonParams != null) {
                if (Companion.commonParams != null) {
                    Companion.commonParams!!.clear()
                } else {
                    Companion.commonParams = HashMap()
                }
                for (paramKey in commonParams.keys) {
                    Companion.commonParams!![paramKey] = commonParams[paramKey]
                }
            }
        }

        @Synchronized
        fun updateOrInsertCommonParam(paramKey: String, paramValue: String) {
            if (commonParams == null) {
                commonParams = HashMap()
            }
            commonParams!![paramKey] = paramValue
        }

        private var commonParams: MutableMap<String, String?>? = null
    }
}