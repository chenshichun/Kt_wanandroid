package com.csc.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.csc.core.widget.ShowAlertDialog

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
abstract class BaseFragment : Fragment() {
    protected var dialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        initData()
        super.onActivityCreated(savedInstanceState)
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()
    protected abstract fun initData()

    open fun showDialog() {
        if (null == dialog) {
            dialog = ShowAlertDialog.loadingDialog(context)
        }
        dialog!!.show()
    }

    open fun closeDialog() {
        if (null != dialog) {
            dialog!!.dismiss()
        }
    }
}