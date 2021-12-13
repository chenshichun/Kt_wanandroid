package com.csc.kt_wanandroid.ui.home

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.bean.BannerBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class HomeContract {
    interface Model {
        fun getBannerData(): Call<BaseNetModel<ArrayList<BannerBean>>>
        fun getArticleTopListData(): Call<BaseNetModel<ArrayList<ArticleBean>>>
        fun getArticleListData(pageNum: Int): Call<BaseNetModel<ArticleListBean>>
        fun collectArt(id: String): Call<BaseNetModel<Any>>
        fun uncollectArt(id: String): Call<BaseNetModel<Any>>
    }

    interface View : BaseView {
        fun getBannerData(mData: ArrayList<BannerBean>?)
        fun getArticleTopListData(mData: ArrayList<ArticleBean>?)
        fun getArticleListData(mData: ArticleListBean?)
        fun collectArt(position: Int, mData: BaseNetModel<Any>)
        fun uncollectArt(position: Int, mData: BaseNetModel<Any>)
    }

    interface Presenter {
        fun getBannerData()
        fun getArticleTopListData()
        fun getArticleListData(pageNum: Int)
        fun collectArt(position: Int, id: String)
        fun uncollectArt(position: Int, id: String)
    }
}