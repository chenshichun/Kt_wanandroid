package com.csc.kt_wanandroid.adapter

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.csc.kt_wanandroid.R
import com.csc.kt_wanandroid.util.ColorUtils
import com.csc.kt_wanandroid.widget.ZsSimplePagerTitleView
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator


class TabAdapter(tabList: MutableList<String>) : CommonNavigatorAdapter() {

    private var tabList = tabList

    interface OnTabClickListener {
        fun onTabClick(view: View, index: Int)
    }

    private var onTabClickListener: OnTabClickListener? = null
    fun setOnTabClickListener(onTabClickListener: OnTabClickListener) {
        this.onTabClickListener = onTabClickListener
    }

    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        val simplePagerTitleView =
            ZsSimplePagerTitleView(context)
        simplePagerTitleView.text = tabList[index]
        simplePagerTitleView.textSize = 16f
        simplePagerTitleView.setPadding(40, 0, 40, 0)
        simplePagerTitleView.normalColor = ColorUtils.parseColor(R.color.color_999)
        simplePagerTitleView.selectedColor = ColorUtils.parseColor(R.color.red)
        simplePagerTitleView.setOnClickListener {
            onTabClickListener?.onTabClick(it, index)
        }
        //选中结果后将字体加粗
        simplePagerTitleView.setSelectListener(object : ZsSimplePagerTitleView.SelectListener {
            override fun onSelect(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = true
            }

            override fun onDeselected(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = false
            }
        })
        return simplePagerTitleView
    }

    override fun getCount(): Int {
        return tabList.size
    }

    override fun getIndicator(context: Context?): IPagerIndicator {
        val indicator = LinePagerIndicator(context)
        indicator.mode = LinePagerIndicator.MODE_EXACTLY
        indicator.lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.lineWidth = UIUtil.dip2px(context, 20.0).toFloat()
        indicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.startInterpolator = AccelerateInterpolator()
        indicator.endInterpolator = DecelerateInterpolator(2.0f)
        indicator.setColors(ColorUtils.parseColor(R.color.red))
        return indicator
    }
}