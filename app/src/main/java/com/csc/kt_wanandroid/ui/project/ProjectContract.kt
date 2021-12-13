package com.csc.kt_wanandroid.ui.project

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.ProjectTreeBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/10
 * 描述：
 *
 */
class ProjectContract {
    interface Model {
        fun getProjectTreeData(): Call<BaseNetModel<ProjectTreeBean>>
    }

    interface View : BaseView {
        fun getProjectTreeData(mData: ProjectTreeBean?)
    }

    interface Presenter {
        fun getProjectTreeData()
    }
}