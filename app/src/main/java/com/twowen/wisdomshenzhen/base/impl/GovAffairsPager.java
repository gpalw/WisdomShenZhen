package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BasePager;

/**
 * Created by lenovo on 2015/11/4.
 * 政务实现
 */
public class GovAffairsPager extends BasePager {
    public GovAffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("人口管理");
        setSlidingMenuEnable(true);//打开侧边栏
        TextView text=new TextView(mActivity);
        text.setText("政务");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        //向Framelayout动态添加布局
        rlContent.addView(text);
    }
}
