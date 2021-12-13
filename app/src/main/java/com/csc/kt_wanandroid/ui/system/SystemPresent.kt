package com.csc.kt_wanandroid.ui.system

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.SystemBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemPresent : BasePresenter<SystemContract.View>(), SystemContract.Presenter {

    private val model: SystemContract.Model = SystemModel()

    override fun getSystemData() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getSystemData().enqueue(object : Callback<BaseNetModel<SystemBean>> {
            override fun onResponse(
                call: Call<BaseNetModel<SystemBean>>,
                response: Response<BaseNetModel<SystemBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.getSystemData(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<SystemBean>>, t: Throwable) {
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