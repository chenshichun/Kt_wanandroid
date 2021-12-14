package com.csc.kt_wanandroid.ui.collect

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class CollectContract {
    interface Model {
        fun getCollectData(pageNum: Int): Call<BaseNetModel<ArticleListBean>>

        fun uncollectArt(id: String): Call<BaseNetModel<Any>>

    }

    interface View : BaseView {
        fun getCollectData(mData: ArticleListBean?)

        fun uncollectArt(position: Int, mData: BaseNetModel<Any>)

    }

    interface Presenter{
        fun getCollectData(pageNum: Int)

        fun uncollectArt(position: Int, id: String)
    }
}