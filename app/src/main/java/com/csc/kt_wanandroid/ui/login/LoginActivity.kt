package com.csc.kt_wanandroid.ui.login

import android.view.View
import com.csc.core.data.UserBean
import com.csc.core.manage.AccountManager
import com.csc.core.manage.AppManager
import com.csc.core.mvp.BaseMvpActivity
import com.csc.core.util.IntentUtil
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.MainActivity
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class LoginActivity : BaseMvpActivity<LoginPresent>(), LoginContract.View, View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mPresenter = LoginPresent()
        mPresenter!!.attachView(this)
        btLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun loginSuccess(mData: UserBean?) {
        ToastUtil.show("登录成功")

        AccountManager.signInToken(mData!!.token)
        AccountManager.signIn(mData)

        AppManager.getInstance()?.finishAllActivity()
        IntentUtil.get().goActivity(this, MainActivity::class.java)
    }

    override fun showLoading() {
        showDialog()
    }

    override fun hideLoading() {
        closeDialog()
    }

    override fun onError(throwable: Throwable?) {
        ToastUtil.show(throwable.toString())
        closeDialog()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btLogin -> {
                when {
                    etName.text.toString().isEmpty() -> ToastUtil.show("请输入账号")
                    etPassword.text.toString().isEmpty() -> ToastUtil.show("请输入密码")
                    else -> {
                        mPresenter?.login(etName.text.toString(), etPassword.text.toString())
                    }
                }
            }
            R.id.tvRegister -> {
                IntentUtil.get().goActivity(this, RegisterActivity::class.java)
            }
        }
    }
}