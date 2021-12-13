package com.csc.kt_wanandroid.http

import com.csc.core.manage.AccountManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class SaveCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.headers("set-cookie").isNotEmpty()) {
            val cookies = HashSet(response.headers("set-cookie"))
            AccountManager.saveCookie(cookies)
        }
        return response
    }
}