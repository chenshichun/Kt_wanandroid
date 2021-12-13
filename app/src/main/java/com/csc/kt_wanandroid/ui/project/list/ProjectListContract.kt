package com.csc.kt_wanandroid.ui.project.list

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.ArticleListBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/13
 * 描述：
 *
 */
class ProjectListContract {
    interface Model {
        fun getProjectListData(
            pageNum: Int,
            cid: String
        ): Call<BaseNetModel<ArticleListBean>>
    }

    interface View : BaseView {
        fun getProjectListData(mData: ArticleListBean?)
    }

    interface Presenter {
        fun getProjectListData(
            pageNum: Int,
            cid: String
        )
    }
}