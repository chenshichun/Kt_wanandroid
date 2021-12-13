package com.csc.kt_wanandroid.bean

/**
 * @author chenshichun
 * 创建日期：2021/12/10
 * 描述：
 *
 */
class ProjectTreeBean : ArrayList<ProjectTreeBeanItem>()

data class ProjectTreeBeanItem(
    val children: List<Any>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)