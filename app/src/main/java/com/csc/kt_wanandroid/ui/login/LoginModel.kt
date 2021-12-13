package com.csc.kt_wanandroid.ui.login

import com.csc.core.data.UserBean
import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class LoginModel : LoginContract.Model {
    override fun login(username: String, password: String): Call<BaseNetModel<UserBean>> {
        return ApiRequest.create(ApiService::class.java).login(username, password)
    }
}