package com.twowen.wisdomshenzhen.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.twowen.wisdomshenzhen.R;
import com.twowen.wisdomshenzhen.base.BasePager;
import com.twowen.wisdomshenzhen.base.impl.GovAffairsPager;
import com.twowen.wisdomshenzhen.base.impl.HomePager;
import com.twowen.wisdomshenzhen.base.impl.NewsCenterPager;
import com.twowen.wisdomshenzhen.base.impl.SettingPager;
import com.twowen.wisdomshenzhen.base.impl.SmartSeverPager;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/10/27.
 * Content
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.rg_group)
    private RadioGroup radioGroup;
    private ArrayList<BasePager> mBaseList;

    @ViewInject(R.id.vp_content)
    private ViewPager mViewPager;
    @Override
    public View initViews() {

        View view=View.inflate(mActivity, R.layout.fragment_content,null);
        com.lidroid.xutils.ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        radioGroup.check(R.id.rb_hone);//默认勾选首页
        mBaseList=new ArrayList<>();
//        //初始化5个子页面
//        for (int i=0;i<5;i++){
//            BasePager pager=new BasePager(mActivity);
//            mBaseList.add(pager);
//        }
        mBaseList.add(new HomePager(mActivity));
        mBaseList.add(new NewsCenterPager(mActivity));
        mBaseList.add(new SmartSeverPager(mActivity));
        mBaseList.add(new GovAffairsPager(mActivity));
        mBaseList.add(new SettingPager(mActivity));


       mViewPager.setAdapter(new ContentAdapter());

        //监听RadioGroup的选择事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_hone:
                        //mViewPager.setCurrentItem(0); //设置到HOME页面
                        mViewPager.setCurrentItem(0,false);//去掉切换页面的动画
                        break;
                    case R.id.rb_newscenter:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_smartsevers:
                        mViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rb_govement:
                        mViewPager.setCurrentItem(3,false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4,false);
                        break;

                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBaseList.get(position).initData();//获取当前被选中的页面，初始化该页面的数据

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBaseList.get(0).initData();//初始化第一页的数据
    }

    class ContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mBaseList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mBaseList.get(position);
            //pager.initData();初始化数据，不要放在此处，否则会预加载下一个页面
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    //获取新闻中心页面
    public NewsCenterPager getNewsCenterPager(){
        return (NewsCenterPager) mBaseList.get(1);

    }
}
