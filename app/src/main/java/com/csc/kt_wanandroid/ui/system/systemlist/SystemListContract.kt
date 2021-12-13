package com.csc.kt_wanandroid.ui.system.systemlist

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.SystemListBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemListContract {
    interface Model {
        fun getSystemListData(
            pageNum: Int,
            id: String
        ): Call<BaseNetModel<SystemListBean>>
        fun collectArt(id: String): Call<BaseNetModel<Any>>
        fun uncollectArt(id: String): Call<BaseNetModel<Any>>
    }

    interface View : BaseView {
        fun getSystemListData(mData: SystemListBean?)
        fun collectArt(position: Int, mData: BaseNetModel<Any>)
        fun uncollectArt(position: Int, mData: BaseNetModel<Any>)
    }

    interface Presenter {
        fun getSystemListData(pageNum: Int, id: String)
        fun collectArt(position: Int, id: String)
        fun uncollectArt(position: Int, id: String)
    }
}