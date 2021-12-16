package com.csc.core.manage;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.csc.core.base.BaseApplication;
import com.csc.core.base.BaseConstant;
import com.csc.core.data.UserBean;
import com.csc.core.util.PreferenceUtil;
import com.csc.core.util.ToastUtil;

import java.util.Set;

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 */
public class AccountManager {

    private Context applicationContext;
    private static UserBean mAccount = null;

    private static AccountManager sAccountManager;
    private static String mAuthorizationToken;
    private static String mExpireime;

    private AccountManager(Context application) {
        this.applicationContext = application;
        if (mAccount == null) {
            mAccount = PreferenceUtil.getObject(application, BaseConstant.TAG_USER_BEAN, UserBean.class);
        }
        mAuthorizationToken = PreferenceUtil.get(application, BaseConstant.TAG_TOKEN);
    }


    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context should not be null!!!");
        }
        if (sAccountManager == null) {
            sAccountManager = new AccountManager(context);
        }
    }

    public static UserBean getAccountInfo() {
        return sAccountManager.mAccount;
    }


    public static String getToken() {
        return sAccountManager.mAuthorizationToken;
    }

    public static boolean isLogin() {
        return sAccountManager.mAccount != null && !TextUtils.isEmpty(sAccountManager.mAuthorizationToken);
    }

    public static void signInToken(String token) {
        sAccountManager.mAuthorizationToken = token;
        PreferenceUtil.save(BaseApplication.mContext, BaseConstant.TAG_TOKEN, token);
    }

    public static void signIn(UserBean account) {
        sAccountManager.mAccount = account;
        PreferenceUtil.saveObject(BaseApplication.mContext, BaseConstant.TAG_USER_BEAN, account);

    }

    /*更新登录信息*/
    public static void updateAccountInfo(UserBean accountInfo) {
        sAccountManager.mAccount = accountInfo;
        PreferenceUtil.saveObject(BaseApplication.mContext, BaseConstant.TAG_USER_BEAN, accountInfo);
    }


    /*需要登录的判断*/
    public static boolean login(Context context) {
        return login(context, false);
    }

    public static boolean login(Context context, boolean toast) {
        if (!AccountManager.isLogin()) {
            if (toast) {
                ToastUtil.show("请先登录");
            }
            return false;
        }
        return true;
    }

    public static void signOut() {
        //清空账户信息
        mAccount = null;
        mAuthorizationToken = "";
        PreferenceUtil.save(BaseApplication.mContext, BaseConstant.TAG_TOKEN, mAuthorizationToken);
        PreferenceUtil.save(BaseApplication.mContext, BaseConstant.TAG_TOKEN_EXPIRE_TIME, 0L);
        PreferenceUtil.saveObject(BaseApplication.mContext, BaseConstant.TAG_USER_BEAN, mAccount);
        PreferenceUtil.saveObject(BaseApplication.mContext, BaseConstant.TAG_USER_PROFILE, null);
    }

    public static boolean isLoginExpired() {
        return (TimeUtils.getNowMills() - TimeUtils.string2Millis(mExpireime)) > 0;
    }

    public static void saveCookie(Set<String> set) {
        PreferenceUtil.saveStringSet(BaseApplication.mContext, BaseConstant.COOKIE, set);
    }

    public static Set<String> getCookie() {
        Set<String> stringSet = PreferenceUtil.getStringSet(BaseApplication.mContext, BaseConstant.COOKIE);
        return stringSet;
    }
}
