package com.csc.kt_wanandroid.ui.system.systemlist

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.SystemListBean
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
class SystemListPresent : BasePresenter<SystemListContract.View>(), SystemListContract.Presenter {
    private val model: SystemListContract.Model = SystemListModel()

    override fun getSystemListData(pageNum: Int, id: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getSystemListData(pageNum, id).enqueue(object : Callback<BaseNetModel<SystemListBean>>{
            override fun onResponse(
                call: Call<BaseNetModel<SystemListBean>>,
                response: Response<BaseNetModel<SystemListBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.getSystemListData(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<SystemListBean>>, t: Throwable) {
                when (t) {
                    is HttpException -> ToastUtil.defaultNetwordBusy()
                    is IOException -> ToastUtil.defaultNetwordBusy()
                    is ApiException -> ToastUtil.show(t.message)
                    else -> mView?.onError(t)
                }
            }

        })
    }

    override fun collectArt(position: Int,id: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.collectArt(id)
            .enqueue(object : Callback<BaseNetModel<Any>> {
                override fun onResponse(
                    call: Call<BaseNetModel<Any>>,
                    response: Response<BaseNetModel<Any>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.collectArt(position,response.body()!!)
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(
                    call: Call<BaseNetModel<Any>>,
                    t: Throwable
                ) {
                    when (t) {
                        is HttpException -> ToastUtil.defaultNetwordBusy()
                        is IOException -> ToastUtil.defaultNetwordBusy()
                        is ApiException -> ToastUtil.show(t.message)
                        else -> mView?.onError(t)
                    }
                }
            })
    }

    override fun uncollectArt(position: Int, id: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.uncollectArt(id)
            .enqueue(object : Callback<BaseNetModel<Any>> {
                override fun onResponse(
                    call: Call<BaseNetModel<Any>>,
                    response: Response<BaseNetModel<Any>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.uncollectArt(position,response.body()!!)
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(
                    call: Call<BaseNetModel<Any>>,
                    t: Throwable
                ) {
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