package com.csc.kt_wanandroid.bean

/**
 * @author chenshichun
 * 创建日期：2021/12/9
 * 描述：
 *
 */
class SystemBean : ArrayList<SystemBeanItem>()

data class SystemBeanItem(
    val children: List<Children>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: String,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class Children(
    val children: List<Any>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: String,
    val userControlSetTop: Boolean,
    val visible: Int
)