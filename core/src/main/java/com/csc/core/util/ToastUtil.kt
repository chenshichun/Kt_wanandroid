package com.csc.core.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.core.R;
/**
 * Created by jayli on 2017/5/2 0002.
 * Toast通知工具类
 */

public class ToastUtil {
    private static Context context;
    public static void init(Context c){
        context=c;
    }
    private static Handler mHandler = new Handler();
    private static Toast mToast;
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };
    public static void show(String msg) {
        mHandler.removeCallbacks(r);

        if (context == null){
            return;
        }
        if (context != null && context instanceof Activity) {
            if(((Activity) context).isFinishing()) {
                return;
            }
        }
        if(TextUtils.isEmpty(msg) || "".equals(msg.trim())){
            return;
        }
        //调用Activity的getLayoutInflater()
        LayoutInflater inflater = LayoutInflater.from(context);
        //加載layout下的布局
        View view = inflater.inflate(R.layout.layout_toast_no_img, null);
        TextView toast_tv = view.findViewById(R.id.toast_tv);
        //toast的文本内容
        toast_tv.setText(msg);
        mToast = new Toast(context);
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        mToast.setGravity( Gravity.CENTER, 0, 0);
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        mToast.setDuration(Toast.LENGTH_SHORT);
        //添加视图文件
        mToast.setView(view);
        //显示toast
        mHandler.postDelayed(r, 2000);
        mToast.show();
    }

    public static void showToast(String msg, int gravity) {
        if (context == null){
            return;
        }
        if (context != null && context instanceof Activity) {
            if(((Activity) context).isFinishing()) {
                return;
            }
        }
        if(TextUtils.isEmpty(msg) || "".equals(msg.trim())){
            return;
        }
        //调用Activity的getLayoutInflater()
        LayoutInflater inflater = LayoutInflater.from(context);
        //加載layout下的布局
        View view = inflater.inflate(R.layout.layout_toast_no_img, null);
        TextView toast_tv = view.findViewById(R.id.toast_tv);
        //toast的文本内容
        toast_tv.setText(msg);

        Toast toast = new Toast(context);
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setGravity(gravity, 0, 0);
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setDuration(Toast.LENGTH_SHORT);
        //添加视图文件
        toast.setView(view);
        //显示toast
        toast.show();
    }

    public static void showToast(Context context, String msg, Integer rid, int gravity) {
        if (context == null){
            return;
        }
        if (context != null && context instanceof Activity) {
            if(((Activity) context).isFinishing()) {
                return;
            }
        }
        if(TextUtils.isEmpty(msg) || "".equals(msg.trim())){
            return;
        }
        //调用Activity的getLayoutInflater()
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        //加載layout下的布局
        View view = inflater.inflate(R.layout.layout_toast_with_img, null);
        ImageView toast_img = view.findViewById(R.id.toast_img);
        TextView toast_tv = view.findViewById(R.id.toast_tv);
        //toast的图片资源
        if(rid == null){
            toast_img.setVisibility(View.GONE);
        }else {
            toast_img.setImageResource(rid);
        }
        //toast的文本内容
        toast_tv.setText(msg);

        Toast toast = new Toast(context);
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setGravity(gravity, 0, 0);
        //setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setDuration(Toast.LENGTH_SHORT);
        //添加视图文件
        toast.setView(view);
        //显示toast
        toast.show();
    }

    public static void defaultShowConnectError(){
        ToastUtil.show(context.getString(R.string.network_connection_failed));
    }
    public static void showNoMoreData(){
        ToastUtil.show(context.getString(R.string.no_more_data));
    }

    public static void defaultNetwordBusy(){
        ToastUtil.show(context.getString(R.string.network_busy));
    }

    public static void upLoadFail(){
        ToastUtil.show(context.getString(R.string.upload_fail));
    }
}
