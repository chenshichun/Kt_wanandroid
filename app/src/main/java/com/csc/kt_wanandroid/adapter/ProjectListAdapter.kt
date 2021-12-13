package com.csc.kt_wanandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.bean.ArticleBean
import kotlinx.android.synthetic.main.item_project_list.view.*

/**
 * @author chenshichun
 * 创建日期：2021/12/13
 * 描述：
 *
 */
class ProjectListAdapter(
    private val context: Context?,
    private val mData: ArrayList<ArticleBean>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mOnItemClickLisenter: OnItemClickLisenter? = null

    fun setOnItemClickLisenter(onItemClickLisenter: OnItemClickLisenter?) {
        mOnItemClickLisenter = onItemClickLisenter
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_project_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context!!)
            .load(mData!![position].envelopePic)
            .into(holder.itemView.imageIv)
        holder.itemView.titleTv.text = mData[position].title
        holder.itemView.descTv.text = mData[position].desc
        holder.itemView.timeTv.text = mData[position].niceDate
        holder.itemView.nameTv.text = mData[position].author
        holder.itemView.setOnClickListener {
            mOnItemClickLisenter!!.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickLisenter {
        fun onItemClick(position: Int)
    }

}