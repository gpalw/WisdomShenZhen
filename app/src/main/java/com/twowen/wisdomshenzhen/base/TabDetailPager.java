package com.twowen.wisdomshenzhen.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.domain.NewsData;

/**
 * Created by lenovo on 2015/11/8.
 * 页签详情页
 */
public class TabDetailPager extends BaseMenuDetailPager {
    NewsData.NewsTabData mTabData;
    private TextView tvtext;
    public TabDetailPager(Activity activity,NewsData.NewsTabData newsTabData) {
        super(activity);
        mTabData=newsTabData;
    }

    @Override
    public View initViews() {

        tvtext= new TextView(mActivity);
        tvtext.setText("页签详情页");
        tvtext.setTextColor(Color.RED);
        tvtext.setTextSize(25);
        tvtext.setGravity(Gravity.CENTER);
        return tvtext;
    }

    @Override
    public void initData() {
        tvtext.setText(mTabData.title);
    }
}
