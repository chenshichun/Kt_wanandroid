package com.csc.kt_wanandroid.ui.user

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.csc.core.mvp.BaseMvpFragment
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.bean.UserInfoBean
import com.csc.kt_wanandroid.ui.collect.CollectActivity
import com.csc.kt_wanandroid.ui.login.LoginActivity
import com.csc.kt_wanandroid.ui.setting.SeetingActivity
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：
 *
 */
class UserFragment : BaseMvpFragment<UserPresent>(), UserContract.View, View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {
        mPresenter = UserPresent()
        mPresenter!!.attachView(this)

        nameTv.setOnClickListener(this)
        collectStv.setOnClickListener(this)
        settingStv.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter?.getInformation()
    }

    @SuppressLint("SetTextI18n")
    override fun getInformation(mData: UserInfoBean?) {
        Glide.with(context!!).load(mData!!.userInfo.icon).error(R.drawable.default_head_pic_icon)
            .into(headIv)
        nameTv.text = mData.userInfo.nickname
        integralTv.visibility = View.VISIBLE
        integralTv.text = "积分: " + mData.userInfo.coinCount
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(throwable: Throwable?) {
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T>? {
        return null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nameTv -> IntentUtil.get().goActivity(context, LoginActivity::class.java)
            R.id.collectStv -> IntentUtil.get().goActivity(context, CollectActivity::class.java)
            R.id.settingStv -> IntentUtil.get().goActivity(context, SeetingActivity::class.java)
        }
    }
}