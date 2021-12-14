package com.csc.kt_wanandroid.ui.user

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.UserInfoBean
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
class UserPresent : BasePresenter<UserContract.View>(), UserContract.Presenter {
    private val model = UserModel()

    override fun getInformation() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getInformation().enqueue(object : Callback<BaseNetModel<UserInfoBean>> {
            override fun onResponse(
                call: Call<BaseNetModel<UserInfoBean>>,
                response: Response<BaseNetModel<UserInfoBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.getInformation(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<UserInfoBean>>, t: Throwable) {
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