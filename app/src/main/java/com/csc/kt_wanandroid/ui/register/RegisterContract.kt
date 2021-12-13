package com.csc.kt_wanandroid.ui.register

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
class RegisterContract {
    interface Model {
        fun register(username: String, password: String, repassword: String): Call<BaseNetModel<Any>>
    }

    interface View : BaseView {
        fun registerSuccess()
    }

    interface Presenter {
        fun register(username: String, password: String, repassword: String)
    }
}