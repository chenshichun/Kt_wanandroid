package com.csc.kt_wanandroid.ui.setting

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class SettingContract {
    interface Model {
        fun loginOut(): Call<BaseNetModel<Any>>
    }

    interface View : BaseView {
        fun loginOut()
    }

    interface Presenter {
        fun loginOut()
    }
}