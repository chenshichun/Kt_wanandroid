package com.csc.core.data

/**
 * @Author : chenshichun
 * @Time : 2020/11/2 19:19
 * @Description : 文件描述
 */
class ApiException(private val mErrorCode: Int, errorMessage: String?) :
    RuntimeException(errorMessage)