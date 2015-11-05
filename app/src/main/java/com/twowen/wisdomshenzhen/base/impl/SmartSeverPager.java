package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BasePager;

/**
 * Created by lenovo on 2015/11/4.
 * 智慧服务实现
 */
public class SmartSeverPager extends BasePager {
    public SmartSeverPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("生活");
        TextView texte=new TextView(mActivity);
        setSlidingMenuEnable(true);//打开侧边栏
        texte.setText("智慧服务");
        texte.setTextColor(Color.RED);
        texte.setTextSize(25);
        texte.setGravity(Gravity.CENTER);
        //向Framelayout动态添加布局
        rlContent.addView(texte);
    }
}
