package com.csc.kt_wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.csc.core.model.BaseNetModel
import com.csc.core.mvp.BaseMvpFragment
import com.csc.core.util.IntentUtil
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.WebViewActivity
import com.csc.kt_wanandroid.adapter.ArticleAdapter
import com.csc.kt_wanandroid.adapter.ArticleAdapter.OnItemClickLisenter
import com.csc.kt_wanandroid.bean.ArticleBean
import com.csc.kt_wanandroid.bean.ArticleListBean
import com.csc.kt_wanandroid.bean.BannerBean
import com.csc.kt_wanandroid.widget.BannerImageLoader
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：首页
 */
class HomeFragment : BaseMvpFragment<HomePresent>(), HomeContract.View, View.OnClickListener {

    private var articleAdapter: ArticleAdapter? = null
    private var articleList: ArrayList<ArticleBean> = ArrayList()
    private var pageNum: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        mPresenter = HomePresent()
        mPresenter!!.attachView(this)

        refreshView()

        articleAdapter = ArticleAdapter(context, articleList)
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mRecyclerView?.adapter = articleAdapter
        articleAdapter!!.setOnItemClickLisenter(object : OnItemClickLisenter {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("web_url", articleList[position].link)
                bundle.putString("web_title", articleList[position].title)
                IntentUtil.get().goActivity(context, WebViewActivity::class.java,bundle)
            }

            override fun onCollectClick(position: Int) {
                if (articleList[position].collect) {
                    mPresenter!!.uncollectArt(position, articleList[position].id)
                } else {
                    mPresenter!!.collectArt(position, articleList[position].id)
                }
            }

        })
    }


    override fun initData() {
        mPresenter?.getBannerData()
        mPresenter?.getArticleTopListData()
        mPresenter?.getArticleListData(pageNum)
    }

    private fun refreshView() {
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            initData()
        }
        refreshLayout.setOnLoadMoreListener {
            pageNum++
            mPresenter?.getArticleListData(pageNum)
        }
    }

    /*
    * banner 数据处理
    * */
    override fun getBannerData(mData: ArrayList<BannerBean>?) {
        var bannerList: List<String> = mutableListOf()
        for (bannerBean in mData!!) {
            bannerList = bannerList + bannerBean.imagePath
        }
        mBanner.setImageLoader(BannerImageLoader())
        mBanner.setImages(bannerList)
        mBanner.setOnBannerListener { position ->
            val bundle = Bundle()
            bundle.putString("web_url", mData[position].url)
            bundle.putString("web_title", mData[position].title)
            IntentUtil.get().goActivity(context, WebViewActivity::class.java,bundle)
        }
        mBanner.start()
    }

    /*
   * 文章置顶数据
   * */
    override fun getArticleTopListData(mData: ArrayList<ArticleBean>?) {
        articleList.clear()// 刷新需要清理数据
        for (articleBean in mData!!) {
            articleBean.isTop = true
        }
        mData.let { articleList.addAll(it) }
        articleAdapter?.notifyDataSetChanged()
    }

    /*
    * 文章列表数据
    * */
    override fun getArticleListData(mData: ArticleListBean?) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
        mData?.let { articleList.addAll(it.datas) }
        articleAdapter?.notifyDataSetChanged()
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

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T>? {
        return null
    }


    override fun onClick(v: View?) {
    }

}