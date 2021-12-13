package com.csc.core.mvp

import com.uber.autodispose.AutoDisposeConverter

interface BaseView {
    /**
     * 显示加载中
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 数据获取失败
     * @param throwable
     */
    fun onError(throwable: Throwable?)

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
    </T> */
    fun <T> bindAutoDispose(): AutoDisposeConverter<T>?
}