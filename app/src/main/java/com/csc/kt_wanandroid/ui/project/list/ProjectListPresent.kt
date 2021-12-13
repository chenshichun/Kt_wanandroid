package com.csc.kt_wanandroid.ui.project.list

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.ArticleListBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/13
 * 描述：
 *
 */
class ProjectListPresent : BasePresenter<ProjectListContract.View>(),
    ProjectListContract.Presenter {
    val model = ProjectListModel()

    override fun getProjectListData(pageNum: Int, cid: String) {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getProjectListData(pageNum,cid).enqueue(object : Callback<BaseNetModel<ArticleListBean>>{
            override fun onResponse(
                call: Call<BaseNetModel<ArticleListBean>>,
                response: Response<BaseNetModel<ArticleListBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.getProjectListData(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<ArticleListBean>>, t: Throwable) {
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