package com.zdm.demo.utildemo.utils;
import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

/**
 * @author: zdm
 * @description: 屏幕分辨率工具类
 * 对于dip、dp、px和sp，之前不太理解的是dip和px之间的区别，
 * 我们知道google的推荐，像素统一使用dip，字体统一使用sp，
 * 接下来具体说明一下：
 * 1. dip: device independent pixels(设备独立像素)：不同设备显示效果不同,dip与屏幕密度有关，
 * 而屏幕密度又和设备硬件有关，一般我们为了支持WVGA、HVGA和QVGA 推荐使用这这个，不依赖像素。
 * dip的换算： dip（value）=(int) (px（value）/1.5 + 0.5)
 * 2. dp: 和dip是完全一样的，只是名字不同而已。
 * 3. px: pixels(像素)，绝对像素，不同的设备不同的显示屏显示效果是一样的，这个是多少就永远是多少不会改变。
 * 4. sp: scaled pixels(放大像素). 主要用于字体显示。
 */
public class ScreenUtil {
    /**
     * 根据手机分辨率将dp转为px单位
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context mContext, float pxValue) {
        final float scale = mContext.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 屏幕宽高
     *
     * @param mContext 上下文
     * @return
     */
    private static int[] getScreenSize(Context mContext) {
        DisplayMetrics dm = mContext
                .getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        return new int[]{screenWidth, screenHeight};
    }

    /**
     * 获取状态栏高度
     *
     * @param mContext 上下文
     * @return
     */
    public static int getStatusBarHeight(Context mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取手机屏幕的宽度
     *
     * @param mContext 上下文
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        int screen[] = getScreenSize(mContext);
        return screen[0];
    }

    /**
     * 获取手机屏幕的高度
     *
     * @param mContext 上下文
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        int screen[] = getScreenSize(mContext);
        return screen[1];
    }
}
