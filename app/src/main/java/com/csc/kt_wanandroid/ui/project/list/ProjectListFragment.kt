package com.csc.kt_wanandroid.ui.project.list

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.csc.core.mvp.BaseMvpFragment
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.WebViewActivity
import com.csc.kt_wanandroid.adapter.ProjectListAdapter
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * @author chenshichun
 * 创建日期：2021/12/13
 * 描述：
 *
 */
class ProjectListFragment(private var cid: String) : BaseMvpFragment<ProjectListPresent>(),
    ProjectListContract.View {

    private var mProjectListAdapter: ProjectListAdapter? = null
    var articleBeans = ArrayList<ArticleBean>()
    private var pageNum: Int = 1

    override fun getLayoutId(): Int {
        return R.layout.fragment_project_list
    }

    override fun initView() {
        mPresenter = ProjectListPresent()
        mPresenter!!.attachView(this)

        mProjectListAdapter = ProjectListAdapter(context, articleBeans)
        mRecyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView?.adapter = mProjectListAdapter
        mProjectListAdapter?.setOnItemClickLisenter(object :
            ProjectListAdapter.OnItemClickLisenter {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("web_url", articleBeans[position].link)
                bundle.putString("web_title", articleBeans[position].title)
                IntentUtil.get().goActivity(context, WebViewActivity::class.java,bundle)
            }
        })
        refreshView()
    }

    private fun refreshView() {
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            initData()
        }
        refreshLayout.setOnLoadMoreListener {
            pageNum++
            initData()
        }
    }

    override fun initData() {
        mPresenter?.getProjectListData(pageNum, cid)
    }

    override fun getProjectListData(mData: ArticleListBean?) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()

        if (mData!!.datas.isNotEmpty()) {
            if (pageNum == 1) {
                articleBeans.clear()
                articleBeans.addAll(mData.datas)
                mProjectListAdapter?.notifyDataSetChanged()
            } else if (pageNum <= mData.pageCount) {
                articleBeans.addAll(mData.datas)
                mProjectListAdapter?.notifyDataSetChanged()
            }
        } else {
            pageNum--
        }
    }


    override fun showLoading() {
        showDialog()
    }

    override fun hideLoading() {
        closeDialog()
    }

    override fun onError(throwable: Throwable?) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
        closeDialog()
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T>? {
        return null
    }
}