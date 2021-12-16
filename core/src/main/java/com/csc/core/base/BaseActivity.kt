package com.csc.core.base

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.csc.core.R
import com.csc.core.manage.AppManager
import com.csc.core.widget.ShowAlertDialog
import com.gyf.immersionbar.ImmersionBar


/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：activity基类
 *
 */
abstract class BaseActivity : AppCompatActivity(), BGASwipeBackHelper.Delegate {

    protected var dialog: Dialog? = null
    protected var mSwipeBackHelper: BGASwipeBackHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // 强制竖屏
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)// 键盘弹窗不会遮挡文字

        setContentView(getLayoutId())
        initView()
        AppManager.instance!!.addActivity(this) //添加到栈中

        // 沉浸式状态栏
        ImmersionBar.with(this).init()
        initSwipeBackFinish();
    }

    override fun onDestroy() {
        AppManager.instance!!.finishActivity(this) //从栈中移除
        super.onDestroy()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun showDialog() {
        if (null == dialog) {
            dialog = ShowAlertDialog.loadingDialog(this)
        }
        dialog!!.show()
    }

    open fun closeDialog() {
        if (null != dialog) {
            dialog!!.dismiss()
        }
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private fun initSwipeBackFinish() {
        mSwipeBackHelper = BGASwipeBackHelper(this, this)
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper!!.setSwipeBackEnable(true)
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper!!.setIsOnlyTrackingLeftEdge(false)
        // 设置是否是微信滑动返回样式。默认值为 true
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper!!.setIsWeChatStyle(true)
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper!!.setShadowResId(R.drawable.bga_sbl_shadow)
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper!!.setIsNeedShowShadow(true)
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper!!.setIsShadowAlphaGradient(true)
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper!!.setSwipeBackThreshold(0.3f)
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper!!.setIsNavigationBarOverlap(false)
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    override fun isSupportSwipeBack(): Boolean {
        return true
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    override fun onSwipeBackLayoutSlide(slideOffset: Float) {}

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    override fun onSwipeBackLayoutCancel() {}

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    override fun onSwipeBackLayoutExecuted() {
        mSwipeBackHelper!!.swipeBackward()
    }
    override fun onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper!!.isSliding) {
            return
        }
        mSwipeBackHelper!!.backward()
    }
}