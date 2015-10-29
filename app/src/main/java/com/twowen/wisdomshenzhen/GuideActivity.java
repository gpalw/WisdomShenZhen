package com.twowen.wisdomshenzhen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.twowen.wisdomshenzhen.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/10/26.
 */
public class GuideActivity extends Activity {
    private ViewPager vpGuide;
    private int mPoint_width;//原点距离
    private View viewRedPoint;//红色原点

    public int densityDpi=100;
    private Button btnStart;//开始体验
    private LinearLayout llPointGroup;
    private static final int[] mImageIds = new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3};
    ArrayList<ImageView> imageViewsList = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_guide);
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        viewRedPoint = findViewById(R.id.view_red_point);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_pointgroup);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新SP
                PrefUtils.setBoolean(GuideActivity.this, "is_user_guide_showed", true);

                //跳转主页面
                startActivity(new Intent(GuideActivity.this, SplashMainActivity.class));

                finish();
            }
        });
        initViews();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageListener());

    }

    //初始化界面
    private void initViews() {
        imageViewsList = new ArrayList<ImageView>();

        for (int i = 0; i < mImageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImageIds[i]);
            imageViewsList.add(imageView);
        }

        //引导页小圆点
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);//设置原点
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(densityDpi, densityDpi);
            if (i > 0) {
                params.leftMargin = densityDpi; //设置原点间隔
            }
            point.setLayoutParams(params);//设置原点大小
            llPointGroup.addView(point);//添加原点
        }


        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当layout执行结束后回调此方法
            @Override
            public void onGlobalLayout() {
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //measure(测量大小)  layout(界面位置)  ondraw()
                mPoint_width = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                DisplayMetrics dm = new DisplayMetrics();
                densityDpi= dm.densityDpi;
            }
        });
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg1, Object arg2) {
            return arg1 == arg2;
        }

        //初始化界面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }

    //滑动监听
    class GuidePageListener implements ViewPager.OnPageChangeListener {
        //滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int len= (int) (mPoint_width * positionOffset)+position*mPoint_width;
//            ViewGroup.LayoutParams layoutParams=viewRedPoint.getLayoutParams();
            //获取当前红点的布局参数
            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
            params.leftMargin =len;//设置左边距
            viewRedPoint.setLayoutParams(params);//重新给小红点设置布局参数

        }
    //某个页面被选中
        @Override
        public void onPageSelected(int position) {
        if (position==mImageIds.length-1) {
            btnStart.setVisibility(View.VISIBLE);
        }else {
            btnStart.setVisibility(View.INVISIBLE);
        }

        }
    //滑动状态改变
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }




}
