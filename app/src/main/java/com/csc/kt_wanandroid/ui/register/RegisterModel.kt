package com.csc.kt_wanandroid.ui.register

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
class RegisterModel : RegisterContract.Model {
    override fun register(username: String, password: String, repassword: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).register(username, password, repassword)
    }
}