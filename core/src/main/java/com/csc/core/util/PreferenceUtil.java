package com.csc.core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: wudou
 * @Package: com.shop.base.util
 * @ClassName: PreferenceUtil
 * @Description: java类作用描述
 * @Author: csc
 * @CreateDate: 2020/3/27 13:43
 */
public class PreferenceUtil {
    public static void save(Context context, String tag, String text) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(tag, text);
        editor.apply();
    }

    public static void save(Context context, String tag, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(tag, value);
        editor.apply();
    }

    public static void save(Context context, String tag, float value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(tag, value);
        editor.apply();
    }

    public static void save(Context context, String tag, long value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(tag, value);
        editor.apply();
    }

    public static String get(Context context, String tag) {
        return get(context, tag, "");
    }


    public static String get(Context context, String tag, String defString) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(tag, defString);
    }

    public static float get(Context context, String tag, float defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getFloat(tag, defaultValue);
    }

    public static int get(Context context, String tag, int defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(tag, defaultValue);
    }

    public static long get(Context context, String tag, long defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sp.getLong(tag, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static void save(Context context, String tag, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(tag, value);
        editor.apply();
    }

    public static boolean get(Context context, String tag, boolean defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(tag, defValue);
    }

    public static <T> void saveListObject(Context context, String key, List<T> list) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        if (list != null && list.size() > 0) {
            String str = JSON.toJSONString(list);
            edit.putString(key, str);
        } else {
            edit.putString(key, "");
        }
        edit.apply();
    }

    public static <T> void saveStringSet(Context context, String key, Set<String> set) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putStringSet(key, set);
        edit.apply();
    }

    public static Set<String> getStringSet(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        Set<String> stringSet = sp.getStringSet(key, null);
        return stringSet;
    }


    public static <T> List<T> getListObject(Context context, String key, Class<T> clazz) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            String json = sp.getString(key, null);
            if (!TextUtils.isEmpty(json)) {
                return JSON.parseArray(json, clazz);
            }
        } catch (Exception e) {
        }
        return new ArrayList<T>();
    }

    public static <T> void saveObject(Context context, String key, T obj) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        if (obj == null) {
            edit.putString(key, "");
        } else {
            String str = JSON.toJSONString(obj);
            edit.putString(key, str);
        }
        edit.apply();
    }

    public static <T> T getObject(Context context, String key, Class<T> clazz) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(key, "");
        return JSON.parseObject(json, clazz);
    }

    public static void clearUser(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
