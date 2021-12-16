package com.csc.kt_wanandroid

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.csc.core.base.BaseActivity
import com.csc.core.manage.AppManager
import com.csc.core.util.ToastUtil
import com.csc.kt_wanandroid.widget.HomeFactory
import com.csc.kt_wanandroid.widget.TabEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var mFragments: ArrayList<Fragment>? = null
    private var mTitles: Array<String>? = null
    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var isExit = false

    private val mIconUnselectedIds = intArrayOf(
        R.drawable.hjgj_icon_mr,
        R.drawable.sqxq_icon_mr,
        R.drawable.sjps_icon_mr,
        R.drawable.wd_icon_mr
    )
    private val mIconSelectIds = intArrayOf(
        R.drawable.hjgj_icon_xz,
        R.drawable.sqxq_icon_xz,
        R.drawable.sjps_icon_xz,
        R.drawable.wd_icon_xz
    )

    private var currentPosition = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initFragments()
        mTitles = arrayOf("首页", "体系", "项目", "我的")
        initTabItems()
    }

    private fun initFragments() {
        mFragments = ArrayList()
        mFragments?.clear()
        HomeFactory().getFragment(0)?.let { mFragments?.add(it) }
        HomeFactory().getFragment(1)?.let { mFragments?.add(it) }
        HomeFactory().getFragment(2)?.let { mFragments?.add(it) }
        HomeFactory().getFragment(3)?.let { mFragments?.add(it) }
    }

    private fun initTabItems() {
        for (i in mTitles!!.indices) {
            mTabEntities.add(TabEntity(mTitles!![i], mIconSelectIds[i], mIconUnselectedIds[i]))
        }

        mBottomNavigationBar?.setTabData(
            mTabEntities,
            this,
            R.id.m_fl_content_container,
            mFragments
        )

        mBottomNavigationBar?.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                currentPosition = when (position) {
                    0 -> 0
                    1 -> 1
                    2 -> 2
                    3 -> 3
                    else -> 3
                }
            }

            override fun onTabReselect(position: Int) {
                Logger.d(position)
            }
        })
        mBottomNavigationBar?.currentTab = currentPosition
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            isExit = false
        }
    }

    private fun exit() {
        if (!isExit) {
            isExit = true
            ToastUtil.show("再按一次退出程序")
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000)
        } else {
            AppManager.instance!!.appExit()
        }
    }
}