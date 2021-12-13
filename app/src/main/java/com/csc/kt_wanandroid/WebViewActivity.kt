package com.csc.kt_wanandroid

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.csc.core.base.BaseActivity
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * @author chenshichun
 * 创建日期：2021/12/8
 * 描述：webview 加载
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class WebViewActivity : BaseActivity(), View.OnClickListener {
    private var url: String = ""
    private var title: String = ""
    private var mAgentWeb: AgentWeb? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        url = intent.getStringExtra("web_url")
        title = intent.getStringExtra("web_title")
        mTvTitle.text = title

        mTvLeft.setOnClickListener(this)

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(view, ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .createAgentWeb()
            .ready()
            .go(url)
        val webSetting = mAgentWeb?.webCreator?.webView?.settings
        webSetting?.javaScriptEnabled = true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.mTvLeft->finish()
        }
    }
}