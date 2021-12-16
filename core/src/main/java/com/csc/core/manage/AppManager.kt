package com.csc.core.manage

import android.app.Activity
import android.os.Process
import java.util.*

class AppManager private constructor() {
    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    val topActivity: Activity?
        get() = activityStack!!.lastElement()

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    fun finishTopActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    fun finishActivity(cls: Class<*>) {
        val iterator: MutableIterator<*> = activityStack!!.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next() as Activity
            if (activity.javaClass == cls) {
                iterator.remove()
                activity.finish()
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i]!!.finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            finishAllActivity()
            System.exit(0)
            Process.killProcess(Process.myPid())
        } catch (e: Exception) {
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 得到指定类名的Activity
     */
    fun getActivity(cls: Class<*>): Activity? {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                return activity
            }
        }
        return null
    }

    companion object {
        var activityStack: Stack<Activity?>? = null
            private set

        /**
         * 单一实例
         */
        var instance: AppManager? = null
            get() {
                if (field == null) {
                    field = AppManager()
                }
                return field
            }
            private set
    }
}