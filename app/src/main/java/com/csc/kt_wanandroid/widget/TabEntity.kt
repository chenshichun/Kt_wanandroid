package com.csc.kt_wanandroid.widget

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class TabEntity(val title: String?, val selectedIcon: Int, val unSelectedIcon: Int) :
    CustomTabEntity {
    override fun getTabTitle(): String? {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}