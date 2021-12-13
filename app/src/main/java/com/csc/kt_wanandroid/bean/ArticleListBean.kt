package com.csc.kt_wanandroid.bean

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：
 *
 */
data class ArticleListBean(
    val curPage: Int,
    val datas: List<ArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
