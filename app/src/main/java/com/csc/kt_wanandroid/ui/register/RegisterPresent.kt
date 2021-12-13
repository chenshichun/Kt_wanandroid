package com.csc.kt_wanandroid.ui.register

import com.blankj.utilcode.util.ToastUtils
import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.socks.library.KLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
class RegisterPresent : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {
    private var model: RegisterContract.Model = RegisterModel()

    override fun register(username: String, password: String, repassword: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()

        model.register(username, password, repassword)
            .enqueue(object : Callback<BaseNetModel<Any>> {
                override fun onResponse(
                    call: Call<BaseNetModel<Any>>,
                    response: Response<BaseNetModel<Any>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.registerSuccess()
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(call: Call<BaseNetModel<Any>>, t: Throwable) {
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