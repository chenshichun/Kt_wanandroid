package com.csc.kt_wanandroid.ui.system.systemlist

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
import com.csc.kt_wanandroid.bean.SystemListBean
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SystemListActivity : BaseMvpActivity<SystemListPresent>(), SystemListContract.View,
    View.OnClickListener {
    var id: String = ""
    private var pageNum: Int = 0
    private var articleAdapter: ArticleAdapter? = null
    private var articleList: ArrayList<ArticleBean> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.activity_system_list
    }

    override fun initView() {
        mPresenter = SystemListPresent()
        mPresenter!!.attachView(this)

        mTvLeft.setOnClickListener(this)
        mTvTitle.text = intent.getStringExtra("title")
        id = intent.getStringExtra("id")
        mPresenter?.getSystemListData(pageNum, id)

        articleAdapter = ArticleAdapter(this, articleList)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.adapter = articleAdapter
        articleAdapter!!.setOnItemClickLisenter(object : ArticleAdapter.OnItemClickLisenter {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("web_url", articleList[position].link)
                bundle.putString("web_title", articleList[position].title)
                IntentUtil.get()
                    .goActivity(this@SystemListActivity, WebViewActivity::class.java, bundle)
            }

            override fun onCollectClick(position: Int) {
                if (articleList[position].collect) {
                    mPresenter!!.uncollectArt(position, articleList[position].id)
                } else {
                    mPresenter!!.collectArt(position, articleList[position].id)
                }
            }

        })

        refreshView()
    }

    private fun refreshView() {
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            mPresenter?.getSystemListData(pageNum, id)
        }
        refreshLayout.setOnLoadMoreListener {
            pageNum++
            mPresenter?.getSystemListData(pageNum, id)
        }
    }

    override fun getSystemListData(mData: SystemListBean?) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
        if (mData!!.datas.isNotEmpty()) {
            if (pageNum == 0) {
                articleList.clear()
                articleList.addAll(mData.datas)
                articleAdapter?.notifyDataSetChanged()
            } else if (pageNum <= mData.pageCount) {
                articleList.addAll(mData.datas)
                articleAdapter?.notifyDataSetChanged()
            }
        } else {
            pageNum--
        }
    }

    /*
    * 收藏
    * */
    override fun collectArt(position: Int, mData: BaseNetModel<Any>) {
        articleList[position].collect = true
        articleAdapter?.notifyDataSetChanged()
    }

    /*
    * 取消收藏
    * */
    override fun uncollectArt(position: Int, mData: BaseNetModel<Any>) {
        articleList[position].collect = false
        articleAdapter?.notifyDataSetChanged()
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