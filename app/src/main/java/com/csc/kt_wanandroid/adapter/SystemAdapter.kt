package com.csc.kt_wanandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.bean.SystemBean
import kotlinx.android.synthetic.main.item_system.view.*


/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemAdapter(private val context: Context?, private val mData: SystemBean?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mOnItemClickLisenter: SystemAdapter.OnItemClickLisenter? = null

    fun setOnItemClickLisenter(onItemClickLisenter: SystemAdapter.OnItemClickLisenter?) {
        mOnItemClickLisenter = onItemClickLisenter
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_system, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.nameTv.text = mData!![position].name
        holder.itemView.countTv.text = "分类：" + mData!![position].children.size

        holder.itemView.labelsView.apply {
            setLabels(mData[position].children) { _, _, data ->
                data.name
            }

            setOnLabelClickListener { _, _, position1 ->
                mOnItemClickLisenter!!.onItemClick(position,position1)
            }
        }
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickLisenter {
        fun onItemClick(position: Int, position1: Int)
    }

}