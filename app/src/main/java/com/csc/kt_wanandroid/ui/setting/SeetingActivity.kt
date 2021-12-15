package com.csc.kt_wanandroid.ui.setting

import android.view.View
import androidx.appcompat.app.AlertDialog
import com.csc.core.manage.AppManager
import com.csc.core.mvp.BaseMvpActivity
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class SeetingActivity : BaseMvpActivity<SettingPresent>(), SettingContract.View,
    View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        mPresenter = SettingPresent()
        mPresenter!!.attachView(this)
        mTvTitle.text = "设置"

        versionStv.setRightString(getVersion())
        loginOutStv.setOnClickListener(this)
        mTvLeft.setOnClickListener(this)

    }

    override fun loginOut() {
        AppManager.getInstance().finishAllActivity()
        IntentUtil.get().goActivity(this, LoginActivity::class.java)
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
            R.id.loginOutStv -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("确定退出登录吗")
                builder.setTitle("提示")
                builder.setPositiveButton(
                    "确定"
                ) { dialog, which ->
                    mPresenter!!.loginOut()
                    dialog.dismiss()
                }
                builder.setNegativeButton(
                    "取消"
                ) { dialog, which -> dialog.dismiss() }
                builder.create().show()
            }
            R.id.mTvLeft -> finish()
        }
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    fun getVersion(): String? {
        return try {
            val manager = this.packageManager
            val info = manager.getPackageInfo(this.packageName, 0)
            info.versionName
        } catch (e: Exception) {
            e.printStackTrace()
            "无法获取到版本号"
        }
    }
}