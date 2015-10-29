package com.twowen.wisdomshenzhen.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2015/10/27.
 * SharePreference封装
 */
public class PrefUtils {
    public static final  String PREF_NAME = "config";
    public static boolean getBoolean(Context context,String key,
                                     boolean defaultValue){
        //判断之前是否显示过
        SharedPreferences sp =context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultValue);
    }

    public static void setBoolean(Context context,String key,
                                     boolean Value){
        //判断之前是否显示过
        SharedPreferences sp =context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,Value).commit();
    }
}
