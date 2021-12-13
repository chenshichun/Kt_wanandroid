package com.csc.kt_wanandroid.widget

import android.content.Context
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class ZsSimplePagerTitleView(context: Context?) : ColorTransitionPagerTitleView(context) {
    private var selectListener: SelectListener? = null
    override fun onSelected(index: Int, totalCount: Int) {
        super.onSelected(index, totalCount)
        if (selectListener != null) {
            selectListener!!.onSelect(index, totalCount)
        }
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        super.onDeselected(index, totalCount)
        if (selectListener != null) {
            selectListener!!.onDeselected(index, totalCount)
        }
    }

    fun setSelectListener(selectListener: SelectListener?) {
        this.selectListener = selectListener
    }

    interface SelectListener {
        /**
         * 选中
         * @param index
         * @param totalCount
         */
        fun onSelect(index: Int, totalCount: Int)

        /**
         * 未选中
         * @param index
         * @param totalCount
         */
        fun onDeselected(index: Int, totalCount: Int)
    }
}