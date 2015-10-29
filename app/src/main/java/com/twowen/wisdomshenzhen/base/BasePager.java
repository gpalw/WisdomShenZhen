package com.twowen.wisdomshenzhen.base;

import android.app.Activity;
import android.view.View;

import com.twowen.wisdomshenzhen.R;

/**
 * Created by lenovo on 2015/10/28.
 * 主页下5个子页面的积累
 */
public class BasePager  {
    public Activity mActivity;
    public View mRootView;//布局对象
    public BasePager(Activity activity){
        mActivity=activity;

        initViews();

    }
    //初始化布局
    public void initViews(){
         mRootView=View.inflate(mActivity, R.layout.base_pager,null);
    }

    //初始化
    public void initData(){

    }

}
