package com.csc.core.util

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.csc.core.R

/**
 * Created by jayli on 2017/5/2 0002.
 * Toast通知工具类
 */
object ToastUtil {
    private var context: Context? = null
    fun init(c: Context?) {
        context = c
    }

    private val mHandler = Handler()
    private var mToast: Toast? = null
    private val r = Runnable { mToast!!.cancel() }
    @JvmStatic
    fun show(msg: String?) {
        mHandler.removeCallbacks(r)
        if (context == null) {
            return
        }
        if (context != null && context is Activity) {
            if ((context as Activity?)!!.isFinishing) {
                return
            }
        }
        if (TextUtils.isEmpty(msg) || "" == msg?.trim { it <= ' ' }) {
            return
        }
        //调用Activity的getLayoutInflater()
        val inflater = LayoutInflater.from(context)
        //加載layout下的布局
        val view = inflater.inflate(R.layout.layout_toast_no_img, null)
        val toast_tv = view.findViewById<TextView>(R.id.toast_tv)
        //toast的文本内容
        toast_tv.text = msg
        mToast = Toast(context)
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        mToast!!.setGravity(Gravity.CENTER, 0, 0)
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        mToast!!.duration = Toast.LENGTH_SHORT
        //添加视图文件
        mToast!!.view = view
        //显示toast
        mHandler.postDelayed(r, 2000)
        mToast!!.show()
    }

    fun showToast(msg: String, gravity: Int) {
        if (context == null) {
            return
        }
        if (context != null && context is Activity) {
            if ((context as Activity?)!!.isFinishing) {
                return
            }
        }
        if (TextUtils.isEmpty(msg) || "" == msg.trim { it <= ' ' }) {
            return
        }
        //调用Activity的getLayoutInflater()
        val inflater = LayoutInflater.from(context)
        //加載layout下的布局
        val view = inflater.inflate(R.layout.layout_toast_no_img, null)
        val toast_tv = view.findViewById<TextView>(R.id.toast_tv)
        //toast的文本内容
        toast_tv.text = msg
        val toast = Toast(context)
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setGravity(gravity, 0, 0)
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.duration = Toast.LENGTH_SHORT
        //添加视图文件
        toast.view = view
        //显示toast
        toast.show()
    }

    fun showToast(context: Context?, msg: String, rid: Int?, gravity: Int) {
        if (context == null) {
            return
        }
        if (context != null && context is Activity) {
            if (context.isFinishing) {
                return
            }
        }
        if (TextUtils.isEmpty(msg) || "" == msg.trim { it <= ' ' }) {
            return
        }
        //调用Activity的getLayoutInflater()
        val inflater = (context as Activity).layoutInflater
        //加載layout下的布局
        val view = inflater.inflate(R.layout.layout_toast_with_img, null)
        val toast_img = view.findViewById<ImageView>(R.id.toast_img)
        val toast_tv = view.findViewById<TextView>(R.id.toast_tv)
        //toast的图片资源
        if (rid == null) {
            toast_img.visibility = View.GONE
        } else {
            toast_img.setImageResource(rid)
        }
        //toast的文本内容
        toast_tv.text = msg
        val toast = Toast(context)
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setGravity(gravity, 0, 0)
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.duration = Toast.LENGTH_SHORT
        //添加视图文件
        toast.view = view
        //显示toast
        toast.show()
    }

    fun defaultShowConnectError() {
        show(context!!.getString(R.string.network_connection_failed))
    }

    fun showNoMoreData() {
        show(context!!.getString(R.string.no_more_data))
    }

    fun defaultNetwordBusy() {
        show(context!!.getString(R.string.network_busy))
    }

    fun upLoadFail() {
        show(context!!.getString(R.string.upload_fail))
    }
}