package com.csc.kt_wanandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.bean.ArticleBean
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：
 *
 */
class ArticleAdapter(private val context: Context?, val mData: List<ArticleBean>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mOnItemClickLisenter: OnItemClickLisenter? = null

    fun setOnItemClickLisenter(onItemClickLisenter: OnItemClickLisenter?) {
        mOnItemClickLisenter = onItemClickLisenter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.titleTv.text = mData!![position].title
        holder.itemView.timeTv.text = mData!![position].niceDate
        if (mData!![position].author.isNotEmpty()) {
            holder.itemView.nameTv.text = "作者：" + mData!![position].author
        } else {
            holder.itemView.nameTv.text = "分享人：" + mData!![position].shareUser
        }
        holder.itemView.topTv.visibility = if (mData!![position].isTop) View.VISIBLE else View.GONE
        holder.itemView.collectIv.setBackgroundResource(if (mData!![position].collect) R.drawable.article_collect else R.drawable.article_un_collect)
        holder.itemView.collectIv.setOnClickListener {
            mOnItemClickLisenter?.onCollectClick(position)
        }
        holder.itemView.setOnClickListener {
            mOnItemClickLisenter?.onItemClick(position)
        }
    }

    interface OnItemClickLisenter {
        fun onItemClick(position: Int)
        fun onCollectClick(position: Int)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}