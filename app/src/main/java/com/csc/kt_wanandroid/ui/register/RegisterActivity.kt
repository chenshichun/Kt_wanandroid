package com.csc.kt_wanandroid.ui.register

import android.view.View
import com.csc.core.mvp.BaseMvpActivity
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.R
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_register.*

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
class RegisterActivity : BaseMvpActivity<RegisterPresent>(), RegisterContract.View,
    View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        mPresenter = RegisterPresent()
        mPresenter!!.attachView(this)

        btRegister.setOnClickListener(this)
        btnClose.setOnClickListener(this)
    }

    override fun registerSuccess() {
        ToastUtil.show("注册成功")
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btRegister -> {
                val username = etName.text.toString()
                val password = etPassword.text.toString()
                val rePassword = etRePassword.text.toString()

                when {
                    username.isEmpty() -> ToastUtil.show("请输入账号")
                    password.isEmpty() -> ToastUtil.show("请输入密码")
                    rePassword.isEmpty() -> ToastUtil.show("请输入确认密码")
                    password != rePassword -> ToastUtil.show("两次密码不一致")
                    else -> {
                        mPresenter?.register(username, password, rePassword)
                    }
                }
            }
            R.id.btnClose -> {
                finish()
            }
        }
    }

}