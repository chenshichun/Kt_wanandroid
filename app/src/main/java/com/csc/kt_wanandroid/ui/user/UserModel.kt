package com.csc.kt_wanandroid.ui.user

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.UserInfoBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class UserModel : UserContract.Model{
    override fun getInformation(): Call<BaseNetModel<UserInfoBean>> {
        return ApiRequest.create(ApiService::class.java).getInformation()
    }
}