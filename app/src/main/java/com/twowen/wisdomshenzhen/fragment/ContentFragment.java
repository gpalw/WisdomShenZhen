package com.twowen.wisdomshenzhen.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.twowen.wisdomshenzhen.R;
import com.twowen.wisdomshenzhen.base.BasePager;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/10/27.
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.rg_group)
    private RadioGroup radioGroup;
    private ArrayList<BasePager> mBaseList;
   // @ViewInject(R.id.vp_content);
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
        mBaseList=new ArrayList<BasePager>();
        //初始化5个子页面
        for (int i=0;i<5;i++){
            BasePager pager=new BasePager(mActivity);
            mBaseList.add(pager);
        }

       // mViewPager.setAdapter(new ContentAdapter());
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
            container.addView(mBaseList.get(position).mRootView);
            return mBaseList.get(position).mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
