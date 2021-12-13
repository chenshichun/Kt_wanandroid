package com.csc.kt_wanandroid.ui.system

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.SystemBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemModel:SystemContract.Model {
    override fun getSystemData(): Call<BaseNetModel<SystemBean>> {
        return ApiRequest.create(ApiService::class.java).getSystemData()
    }
}