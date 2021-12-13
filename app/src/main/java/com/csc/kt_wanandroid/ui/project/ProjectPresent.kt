package com.csc.kt_wanandroid.ui.project

import com.csc.core.data.ApiException
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BasePresenter
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.bean.ProjectTreeBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * @author chenshichun
 * 创建日期：2021/12/10
 * 描述：
 *
 */
class ProjectPresent : BasePresenter<ProjectContract.View>(), ProjectContract.Presenter {
    private val model = ProjectModel()

    override fun getProjectTreeData() {
        if (!isViewAttached) {
            return
        }
        mView?.showLoading()
        model.getProjectTreeData().enqueue(object : Callback<BaseNetModel<ProjectTreeBean>> {
            override fun onResponse(
                call: Call<BaseNetModel<ProjectTreeBean>>,
                response: Response<BaseNetModel<ProjectTreeBean>>
            ) {
                mView?.hideLoading()
                if (response.body()!!.errorCode == 0) {
                    mView?.getProjectTreeData(response.body()!!.data)
                } else {
                    ToastUtil.show(response.body()!!.errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseNetModel<ProjectTreeBean>>, t: Throwable) {
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