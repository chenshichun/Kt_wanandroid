package com.csc.kt_wanandroid.ui.project.list

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/13
 * 描述：
 *
 */
class ProjectListModel:ProjectListContract.Model {
    override fun getProjectListData(
        pageNum: Int,
        cid: String
    ): Call<BaseNetModel<ArticleListBean>> {
        return ApiRequest.create(ApiService::class.java).getProjectListData(pageNum, cid)
    }
}