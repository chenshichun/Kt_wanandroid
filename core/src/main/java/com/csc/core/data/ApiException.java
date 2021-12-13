package com.csc.core.data;


/**
 * @ProjectName : JeecgBoot
 * @Author : chenshichun
 * @Time : 2020/11/2 19:19
 * @Description : 文件描述
 */
public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }
}
