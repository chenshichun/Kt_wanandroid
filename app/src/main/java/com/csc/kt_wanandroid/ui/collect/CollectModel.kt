package com.csc.kt_wanandroid.ui.collect

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class CollectModel:CollectContract.Model {
    override fun getCollectData(pageNum: Int): Call<BaseNetModel<ArticleListBean>> {
        return ApiRequest.create(ApiService::class.java).getCollectData(pageNum)
    }

    override fun uncollectArt(id: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).uncollectArt(id)
    }
}