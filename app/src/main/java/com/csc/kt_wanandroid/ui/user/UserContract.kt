package com.csc.kt_wanandroid.ui.user

import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseView
import com.csc.kt_wanandroid.bean.UserInfoBean
import retrofit2.Call

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class UserContract {
    interface Model {
        fun getInformation(): Call<BaseNetModel<UserInfoBean>>
    }

    interface View : BaseView {
        fun getInformation(mData: UserInfoBean?)
    }

    interface Presenter{
        fun getInformation()
    }
}