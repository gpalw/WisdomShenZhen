package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BasePager;

/**
 * Created by lenovo on 2015/11/4.
 * 设置的实现
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("设置");
        btnMenu.setVisibility(View.GONE);//隐藏菜单按钮
        setSlidingMenuEnable(false);//关闭侧边栏
        TextView texte=new TextView(mActivity);
        texte.setText("设置");
        texte.setTextColor(Color.RED);
        texte.setTextSize(25);

        texte.setGravity(Gravity.CENTER);
        texte.setVisibility(View.VISIBLE);




        //向Framelayout动态添加布局
        rlContent.addView(texte);
    }
}
