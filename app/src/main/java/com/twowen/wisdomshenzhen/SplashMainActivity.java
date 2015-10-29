package com.twowen.wisdomshenzhen;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.twowen.wisdomshenzhen.fragment.ContentFragment;
import com.twowen.wisdomshenzhen.fragment.LeftMenuFragment;

/**
 * Created by lenovo on 2015/10/27.
 */
public class SplashMainActivity extends SlidingFragmentActivity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spmain);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setBehindContentView(R.layout.left_menu);//设置侧边栏
        SlidingMenu slidingMenu=getSlidingMenu();//获取侧边栏对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设置左侧边栏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸
        slidingMenu.setBehindOffset(200);//设置预留屏幕的宽度
        initFragment();
    }
    //初始化Fragment，将Fragment数据填充给布局文件
    private void initFragment(){
        android.support.v4.app.FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();//开启事务
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),FRAGMENT_LEFT_MENU);
        transaction.replace(R.id.fl_content, new ContentFragment(),FRAGMENT_CONTENT);

        transaction.commit();//提交事务

    }


}
