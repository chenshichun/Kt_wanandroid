package com.csc.kt_wanandroid.ui.system.systemlist

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.SystemListBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemListModel : SystemListContract.Model {
    override fun getSystemListData(
        pageNum: Int,
        id: String
    ): Call<BaseNetModel<SystemListBean>> {
        return ApiRequest.create(ApiService::class.java).getSystemListData(pageNum, id)
    }


    override fun collectArt(id: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).collectArt(id)
    }

    override fun uncollectArt(id: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).uncollectArt(id)
    }

}