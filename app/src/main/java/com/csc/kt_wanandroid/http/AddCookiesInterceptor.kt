package com.csc.kt_wanandroid.http

import com.csc.core.manage.AccountManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var cookies = HashSet<String>()

        AccountManager.getCookie()?.let {
            cookies = it as HashSet<String>
        }
        cookies.let {
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
            }
        }

        return chain.proceed(builder.build())
    }
}