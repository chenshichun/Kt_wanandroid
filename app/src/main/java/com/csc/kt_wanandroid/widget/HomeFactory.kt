package com.csc.kt_wanandroid.widget

import com.csc.core.base.BaseFragment
import com.csc.kt_wanandroid.ui.home.HomeFragment
import com.csc.kt_wanandroid.ui.project.ProjectFragment
import com.csc.kt_wanandroid.ui.system.SystemFragment
import com.csc.kt_wanandroid.ui.user.UserFragment
import java.util.*

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class HomeFactory {

    private val mCaches: MutableMap<Int, BaseFragment?> = LinkedHashMap<Int, BaseFragment?>()

    fun getFragment(position: Int): BaseFragment? {
        var fragment: BaseFragment? = mCaches[position]

        // 判断缓存中是否有
        if (fragment != null) {
            return fragment
        }
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = SystemFragment()
            2 -> fragment = ProjectFragment()
            3 -> fragment = UserFragment()
        }

        // 存储到缓存
        mCaches[position] = fragment
        return fragment
    }

    fun clearAllCache() {
        mCaches.clear()
    }

}