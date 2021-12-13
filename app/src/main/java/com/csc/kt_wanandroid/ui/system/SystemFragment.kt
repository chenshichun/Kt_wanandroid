package com.csc.kt_wanandroid.ui.system

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.csc.core.mvp.BaseMvpFragment
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.adapter.SystemAdapter
import com.csc.kt_wanandroid.bean.SystemBean
import com.csc.kt_wanandroid.ui.system.systemlist.SystemListActivity
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：体系
 *
 */
class SystemFragment : BaseMvpFragment<SystemPresent>(), SystemContract.View {
    private var systemAdapter: SystemAdapter? = null
    private var systemBean: SystemBean? = SystemBean()

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        mPresenter = SystemPresent()
        mPresenter?.attachView(this)

        systemAdapter = SystemAdapter(context, systemBean)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = systemAdapter
        systemAdapter!!.setOnItemClickLisenter(object : SystemAdapter.OnItemClickLisenter {
            override fun onItemClick(position: Int,position1: Int) {
                var bundle = Bundle()
                bundle.putString("title", systemBean!![position].children[position1].name)
                bundle.putString("id", systemBean!![position].children[position1].id)
                IntentUtil.get().goActivity(context, SystemListActivity::class.java, bundle)
            }
        })
    }

    override fun initData() {
        mPresenter?.getSystemData()
    }

    override fun getSystemData(mData: SystemBean?) {
        systemBean?.clear()
        systemBean?.addAll(mData!!)
        systemAdapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
        showDialog()
    }

    override fun hideLoading() {
        closeDialog()
    }

    override fun onError(throwable: Throwable?) {
        closeDialog()
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T>? {
        return null
    }

}