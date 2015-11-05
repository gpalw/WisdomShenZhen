package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BasePager;

/**
 * Created by lenovo on 2015/11/4.
 * 新闻中心的实现
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("新闻");
        TextView texte=new TextView(mActivity);
        setSlidingMenuEnable(true);//打开侧边栏
        texte.setText("新闻中心");
        texte.setTextColor(Color.RED);
        texte.setTextSize(25);
        texte.setGravity(Gravity.CENTER);
        //向Framelayout动态添加布局
        rlContent.addView(texte);
    }
}
