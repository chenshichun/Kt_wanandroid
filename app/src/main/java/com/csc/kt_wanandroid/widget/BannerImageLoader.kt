package com.csc.kt_wanandroid.widget

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * @author chenshichun
 * 创建日期：2021/12/6
 * 描述：
 *
 */
class BannerImageLoader:ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context!!)
            .load(path)
            .into(imageView!!)
    }
}