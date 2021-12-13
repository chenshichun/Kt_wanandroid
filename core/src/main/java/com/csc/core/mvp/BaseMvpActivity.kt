package com.csc.core.mvp

import androidx.lifecycle.Lifecycle
import com.csc.core.base.BaseActivity
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    protected var mPresenter: T? = null

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }


    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
    </T> */
    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return AutoDispose.autoDisposable(
            AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY)
        )
    }
}
