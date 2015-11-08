package com.twowen.wisdomshenzhen.base.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.twowen.wisdomshenzhen.R;
import com.twowen.wisdomshenzhen.base.BaseMenuDetailPager;
import com.twowen.wisdomshenzhen.base.TabDetailPager;
import com.twowen.wisdomshenzhen.domain.NewsData;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/11/7.
 *
 * 菜单详情页--新闻
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {

    private ArrayList<TabDetailPager> mPagerList;
    private ViewPager mViewPager;
    private ArrayList<NewsData.NewsTabData> mNewsTabData;//页签网络数据

    public NewsMenuDetailPager(Activity activity,ArrayList<NewsData.NewsTabData> children) {
        super(activity);
        mNewsTabData=children;

    }
    @Override
    public View initViews() {
        View view =View.inflate(mActivity,R.layout.news_menu_detail,null);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_menu_detail);
        return view;
    }

    @Override
    public void initData() {
        //初始化页签数据
        mPagerList=new ArrayList<TabDetailPager>();
        for (int i=0;i<mNewsTabData.size();i++){
            TabDetailPager pager =new TabDetailPager(mActivity,mNewsTabData.get(i));
            mPagerList.add(pager);
        }
        mViewPager.setAdapter(new MenuDetailAdapter());
    }

    class MenuDetailAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager pager = mPagerList.get(position);

            container.addView(pager.mRootView);
            pager.initData();

            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
