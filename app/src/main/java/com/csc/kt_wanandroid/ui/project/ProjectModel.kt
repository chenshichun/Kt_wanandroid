package com.csc.kt_wanandroid.ui.project

import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.ProjectTreeBean
import com.csc.kt_wanandroid.http.ApiRequest
import com.csc.kt_wanandroid.service.ApiService
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/10
 * 描述：
 *
 */
class ProjectModel : ProjectContract.Model {
    override fun getProjectTreeData(): Call<BaseNetModel<ProjectTreeBean>> {
        return ApiRequest.create(ApiService::class.java).getProjectTreeData()
    }
}