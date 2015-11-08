package com.twowen.wisdomshenzhen.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.twowen.wisdomshenzhen.R;
import com.twowen.wisdomshenzhen.SplashMainActivity;

/**
 * Created by lenovo on 2015/10/28.
 * 主页下5个子页面的积累
 */
public class BasePager  {
    public Activity mActivity;
    public View mRootView;//布局对象

    public TextView tvTitle;//标题对象
    public FrameLayout rlContent;//页面对象
    public ImageButton btnMenu;//菜单按钮
    public BasePager(Activity activity){
        mActivity=activity;

        initViews();

    }
    //初始化布局
    public void initViews(){
         mRootView=View.inflate(mActivity, R.layout.base_pager,null);

        tvTitle= (TextView) mRootView.findViewById(R.id.tv_title);
        rlContent= (FrameLayout) mRootView.findViewById(R.id.rl_content);
        btnMenu= (ImageButton) mRootView.findViewById(R.id.btn_menu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }

    private void toggleSlidingMenu() {
        SplashMainActivity mainUI= (SplashMainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        slidingMenu.toggle();//切换显示，显示时隐藏，隐藏时显示

    }

    //初始化
    public void initData(){

    }
    /**
     * 设置侧边栏开启或者关闭
     * */
    public void setSlidingMenuEnable(boolean enable){
        SplashMainActivity mainUI= (SplashMainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);}
    }

}
