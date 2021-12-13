package com.csc.kt_wanandroid.ui.login

import com.csc.core.data.UserBean
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class LoginContract {
    interface Model {
        fun login(username: String, password: String): Call<BaseNetModel<UserBean>>
    }

    interface View : BaseView {
        fun loginSuccess(mData: UserBean?)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}