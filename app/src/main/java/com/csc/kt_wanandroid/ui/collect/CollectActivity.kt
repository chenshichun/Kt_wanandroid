package com.csc.kt_wanandroid.ui.collect

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseMvpActivity
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.WebViewActivity
import com.csc.kt_wanandroid.adapter.ArticleAdapter
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.socks.library.KLog
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_project_list.*
import kotlinx.android.synthetic.main.fragment_project_list.mRecyclerView
import kotlinx.android.synthetic.main.fragment_project_list.refreshLayout
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @author chenshichun
 * 创建日期：2021/12/14
 * 描述：
 *
 */
class CollectActivity : BaseMvpActivity<CollectPresent>(), CollectContract.View,View.OnClickListener {
    private var pageNum: Int = 0
    private var articleAdapter: ArticleAdapter? = null
    private var articleList: ArrayList<ArticleBean> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.activity_collect
    }

    override fun initView() {
        mPresenter = CollectPresent()
        mPresenter!!.attachView(this)

        mTvTitle.text = "我的收藏"
        mPresenter!!.getCollectData(pageNum)
        mTvLeft.setOnClickListener(this)

        refreshView()

        articleAdapter = ArticleAdapter(this, articleList)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.adapter = articleAdapter
        articleAdapter!!.setOnItemClickLisenter(object : ArticleAdapter.OnItemClickLisenter {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("web_url", articleList[position].link)
                bundle.putString("web_title", articleList[position].title)
                IntentUtil.get()?.goActivity(this@CollectActivity, WebViewActivity::class.java, bundle)
            }

            override fun onCollectClick(position: Int) {
                mPresenter!!.uncollectArt(position, articleList[position].originId)
            }
        })
    }

    override fun getCollectData(mData: ArticleListBean?) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()

        if (mData!!.datas.isNotEmpty()) {
            for (collectData in mData.datas) {
                collectData.collect = true
            }
            if (pageNum == 0) {
                articleList.clear()
                articleList.addAll(mData.datas)
                articleAdapter?.notifyDataSetChanged()
            } else if (pageNum <= mData.pageCount - 1) {
                KLog.d("chenshichun", pageNum)
                articleList.addAll(mData.datas)
                articleAdapter?.notifyDataSetChanged()
            }
        } else {
            pageNum--
        }
    }

    override fun uncollectArt(position: Int, mData: BaseNetModel<Any>) {
        articleList.removeAt(position)
        articleAdapter?.notifyDataSetChanged()
    }

    private fun refreshView() {
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setOnRefreshListener {
            pageNum = 0
            mPresenter!!.getCollectData(pageNum)
        }
        refreshLayout.setOnLoadMoreListener {
            pageNum++
            mPresenter!!.getCollectData(pageNum)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mTvLeft -> finish()
        }
    }
}