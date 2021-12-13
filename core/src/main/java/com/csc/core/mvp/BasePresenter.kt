package com.csc.core.mvp

import com.socks.library.KLog
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

open class BasePresenter<V : BaseView?> {
    /**
     * 弱引用, 防止内存泄漏
     */
    private var weakReference: WeakReference<V?>? = null
    protected var mView: V? = null

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    fun attachView(view: V) {
        weakReference = WeakReference(view)

        val viewHandler = weakReference!!.get()?.let { MvpViewHandler(it) }

        mView = view
        /*mView = Proxy.newProxyInstance(
            this::class.java.classLoader,
            this.javaClass.interfaces,
            viewHandler
        ) as V*/
    }


    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    fun detachView() {
        if (isViewAttached) {
            weakReference!!.clear()
            weakReference = null
        }
    }

    /**
     * View是否绑定
     *
     * @return
     */
    val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null

    private inner class MvpViewHandler internal constructor(private val mvpView: BaseView) :
        InvocationHandler {
        @Throws(Throwable::class)
        override fun invoke(
            proxy: Any,
            method: Method,
            args: Array<Any>
        ): Any? { //解决第三个问题，如果V层没被销毁, 执行V层的方法.
            try {
                if (isViewAttached) {
                    return method.invoke(mvpView, *args)
                }
            } catch (e: InvocationTargetException) {
                throw e.cause!!
            }
            //P层不需要关注V层的返回值
            return null
        }
    }
}