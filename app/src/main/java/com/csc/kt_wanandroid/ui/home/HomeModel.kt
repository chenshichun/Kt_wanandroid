package com.csc.kt_wanandroid.ui.home

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.bean.BannerBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class HomeModel : HomeContract.Model {
    override fun getBannerData(): Call<BaseNetModel<ArrayList<BannerBean>>> {
        return ApiRequest.create(ApiService::class.java).getBannerData()
    }

    override fun getArticleTopListData(): Call<BaseNetModel<ArrayList<ArticleBean>>> {
        return ApiRequest.create(ApiService::class.java).getArticleTopListData()
    }

    override fun getArticleListData(pageNum: Int): Call<BaseNetModel<ArticleListBean>> {
        return ApiRequest.create(ApiService::class.java).getArticleListData(pageNum)
    }

    override fun collectArt(id: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).collectArt(id)
    }

    override fun uncollectArt(id: String): Call<BaseNetModel<Any>> {
        return ApiRequest.create(ApiService::class.java).uncollectArt(id)
    }
}