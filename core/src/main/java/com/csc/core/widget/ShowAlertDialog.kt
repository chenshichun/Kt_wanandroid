package com.csc.core.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.csc.core.R

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：请求接口的进度条
 *
 */
object ShowAlertDialog {
    private val alertDialog: Dialog? = null
    fun closeImg() {
        if (anim != null) {
            anim!!.stop()
            anim = null
        }
    }

    private var anim: AnimationDrawable? = null
    fun loadingDialog(context: Context?): Dialog {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.dialog_loading, null) // 得到加载view
        val loadingDialog = Dialog(context!!, R.style.loading_dialog) // 创建自定义样式dialog
        loadingDialog.setCancelable(true) // 不可以用“返回键”
        loadingDialog.setContentView(
            v, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        ) // 设置布局
        loadingDialog.setCanceledOnTouchOutside(false)
        return loadingDialog
    }
}