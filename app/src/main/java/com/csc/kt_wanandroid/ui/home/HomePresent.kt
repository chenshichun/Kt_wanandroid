package com.csc.kt_wanandroid.ui.home

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.bean.BannerBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 */
class HomePresent : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    private val model: HomeContract.Model = HomeModel()

    override fun getBannerData() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getBannerData()
            .enqueue(object : Callback<BaseNetModel<ArrayList<BannerBean>>> {
                override fun onResponse(
                    call: Call<BaseNetModel<ArrayList<BannerBean>>>,
                    response: Response<BaseNetModel<ArrayList<BannerBean>>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.getBannerData(response.body()!!.data)
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(
                    call: Call<BaseNetModel<ArrayList<BannerBean>>>,
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

    override fun getArticleTopListData() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getArticleTopListData()
            .enqueue(object : Callback<BaseNetModel<ArrayList<ArticleBean>>> {
                override fun onResponse(
                    call: Call<BaseNetModel<ArrayList<ArticleBean>>>,
                    response: Response<BaseNetModel<ArrayList<ArticleBean>>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.getArticleTopListData(response.body()!!.data)
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(
                    call: Call<BaseNetModel<ArrayList<ArticleBean>>>,
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

    override fun getArticleListData(pageNum: Int) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getArticleListData(pageNum)
            .enqueue(object : Callback<BaseNetModel<ArticleListBean>> {
                override fun onResponse(
                    call: Call<BaseNetModel<ArticleListBean>>,
                    response: Response<BaseNetModel<ArticleListBean>>
                ) {
                    mView?.hideLoading()
                    if (response.body()!!.errorCode == 0) {
                        mView?.getArticleListData(response.body()!!.data)
                    } else {
                        ToastUtil.show(response.body()!!.errorMsg)
                    }
                }

                override fun onFailure(
                    call: Call<BaseNetModel<ArticleListBean>>,
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