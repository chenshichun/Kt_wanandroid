package com.csc.kt_wanandroid.ui.project

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.csc.core.mvp.BaseMvpFragment
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.adapter.TabAdapter
import com.csc.kt_wanandroid.bean.ProjectTreeBean
import com.csc.kt_wanandroid.bean.ProjectTreeBeanItem
import com.csc.kt_wanandroid.ui.project.list.ProjectListFragment
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：
 *
 */
@Suppress("NAME_SHADOWING")
class ProjectFragment : BaseMvpFragment<ProjectPresent>(), ProjectContract.View,
    TabAdapter.OnTabClickListener {
    private var tabList = mutableListOf<ProjectTreeBeanItem>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        mPresenter = ProjectPresent()
        mPresenter!!.attachView(this)
    }


    override fun initData() {
        mPresenter?.getProjectTreeData()
    }

    override fun getProjectTreeData(mData: ProjectTreeBean?) {
        tabList.clear()
        tabList.addAll(mData!!)
        initFragment()
    }

    private fun initFragment() {
        val fragmentList = mutableListOf<Fragment>()
        val list = mutableListOf<String>()
        tabList.forEachIndexed { _, value ->
            val fragment = ProjectListFragment(value.id)
            fragmentList.add(fragment)

            value.name.let { value -> list.add(value) }
            val adapter = FragmentListAdapter(fragmentList, childFragmentManager)
            viewPager.offscreenPageLimit = 6
            viewPager.adapter = adapter
            val commonNavigator = CommonNavigator(context)
            val tabAdapter = TabAdapter(list)
            //tab点击事件
            tabAdapter.setOnTabClickListener(this)
            commonNavigator.adapter = tabAdapter
            magicView.navigator = commonNavigator
            //将magicView和viewPager进行绑定
            ViewPagerHelper.bind(magicView, viewPager)
        }

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

    class FragmentListAdapter(private val fragments: MutableList<Fragment>, fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }

    override fun onTabClick(view: View, index: Int) {
        viewPager.currentItem = index
    }

}