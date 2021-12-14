package com.csc.kt_wanandroid.ui.setting

import com.csc.core.data.ApiException
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
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class SettingPresent : BasePresenter<SettingContract.View>(), SettingContract.Presenter {
    private val model = SettingModel()

    override fun loginOut() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.loginOut().enqueue(object : Callback<BaseNetModel<Any>> {
            override fun onResponse(
                call: Call<BaseNetModel<Any>>,
                response: Response<BaseNetModel<Any>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.loginOut()
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