package com.twowen.wisdomshenzhen.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.twowen.wisdomshenzhen.base.BaseMenuDetailPager;

/**
 * Created by lenovo on 2015/11/7.
 *
 * 菜单详情页--互动
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager {
    public InteractMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initViews() {
        TextView text=new TextView(mActivity);
        text.setText("菜单详情页--互动");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        return text;
    }
}
