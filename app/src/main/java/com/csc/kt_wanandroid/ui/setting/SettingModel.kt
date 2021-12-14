package com.csc.kt_wanandroid.ui.setting

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class SettingModel:SettingContract.Model{
    override fun loginOut(): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).loginOut()
    }
}