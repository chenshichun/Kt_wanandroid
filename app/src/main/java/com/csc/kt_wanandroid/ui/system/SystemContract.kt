package com.csc.kt_wanandroid.ui.system

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.SystemBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemContract {
    interface Model {
        fun getSystemData(): Call<BaseNetModel<SystemBean>>
    }

    interface View : BaseView {
        fun getSystemData(mData: SystemBean?)
    }

    interface Presenter {
        fun getSystemData()
    }
}