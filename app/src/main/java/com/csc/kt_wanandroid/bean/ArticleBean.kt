package com.csc.kt_wanandroid.bean

/**
 * @author chenshichun
 * 创建日期：2021/12/7
 * 描述：
 *
 */
data class ArticleBean(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: String,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val originId: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: String,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    var isTop: Boolean = false
)

data class Tag(
    val name: String,
    val url: String
)