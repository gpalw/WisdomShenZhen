package com.twowen.wisdomshenzhen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.twowen.wisdomshenzhen.utils.PrefUtils;

public class MainActivity extends Activity {
    RelativeLayout rlRoot;
    public static final int WEIHTANDHIGHT=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot= (RelativeLayout) findViewById(R.id.rl_root);
        startAnim();
    }
    //开启动画
    private void startAnim(){
        AnimationSet set =new AnimationSet(false);
        //旋转动画
        RotateAnimation rotate=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);//设置时间
        rotate.setFillAfter(true);//保持动画状态
        //变大动画
        ScaleAnimation scale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(1000);//设置时间
        scale.setFillAfter(true);//保持动画状态

        //淡入动画
        AlphaAnimation alpha=new AlphaAnimation(0,1);
        alpha.setDuration(2000);//设置时间
        alpha.setFillAfter(true);//保持动画状态

        //设置同时
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        //设置监听，结束
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转到引导页
                jumpNextPage();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlRoot.startAnimation(set);

    }

    private void jumpNextPage(){
        boolean userGuide= PrefUtils.getBoolean(this,"is_user_guide_showed", false);
        if (!userGuide){
            //跳转到引导页
            startActivity(new Intent(MainActivity.this, GuideActivity.class));
        }else {
            //跳转主页面
            startActivity(new Intent(MainActivity.this, SplashMainActivity.class));
        }
        finish();
    }



}
