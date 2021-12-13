package com.csc.core.data

import com.google.gson.annotations.SerializedName

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
class HttpStatus {
    @SerializedName("code")
    private val errorCode = 0

    @SerializedName("message")
    private val errorMsg: String? = null
    fun getCode(): Int {
        return errorCode
    }

    fun getMessage(): String? {
        return errorMsg
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    fun isCodeInvalid(): Boolean {
        return errorCode != Constants.WEB_RESP_CODE_SUCCESS
    }

    fun isNeedLogin(): Boolean {
        return errorCode == Constants.NEED_LOGIN_CODE
    }
}