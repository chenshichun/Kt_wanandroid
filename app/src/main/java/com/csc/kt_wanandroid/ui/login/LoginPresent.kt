package com.csc.kt_wanandroid.ui.login

import com.csc.core.data.ApiException
import com.csc.core.data.UserBean
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class LoginPresent : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    private var model: LoginContract.Model? = LoginModel()

    override fun login(username: String, password: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model?.login(username, password)?.enqueue(object : Callback<BaseNetModel<UserBean>> {
            override fun onResponse(
                call: Call<BaseNetModel<UserBean>>,
                response: Response<BaseNetModel<UserBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.loginSuccess(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<UserBean>>, t: Throwable) {
                when (t) {
                    is HttpException -> ToastUtil.defaultNetwordBusy()
                    is IOException -> ToastUtil.defaultNetwordBusy()
                    is ApiException -> ToastUtil.show(t.message)
                    else -> mView?.onError(t)
                }
            }
        })
    }
}