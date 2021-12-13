package com.csc.core.mvp

import com.csc.core.base.BaseFragment

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    protected var mPresenter: T? = null

    override fun onDestroyView() {
        super.onDestroyView()

        mPresenter?.detachView()

        super.onDestroyView()
    }
}