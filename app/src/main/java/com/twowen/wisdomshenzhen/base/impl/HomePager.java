package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BasePager;

/**
 * Created by lenovo on 2015/11/4.
 * 首页实现
 */
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("智慧北京");
        btnMenu.setVisibility(View.GONE);//隐藏菜单按钮
        setSlidingMenuEnable(false);//关闭侧边栏

        TextView text = new TextView(mActivity);
        text.setText("首页！！！");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);


        //向Framelayout动态添加布局
        rlContent.addView(text);
    }




}
