package com.twowen.wisdomshenzhen.fragment;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.twowen.wisdomshenzhen.R;
import com.twowen.wisdomshenzhen.SplashMainActivity;
import com.twowen.wisdomshenzhen.base.impl.NewsCenterPager;
import com.twowen.wisdomshenzhen.domain.NewsData;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/10/27.
 */
public class LeftMenuFragment extends BaseFragment {
    @ViewInject(R.id.lv_list)
    private ListView lvList;
    //记录被点击的菜单栏
    private int mCurrentPos;
    private ArrayList<NewsData.NewsMenuDate> mMenuList;
    private MenuAdapter mAdapter;
    @Override
    public View initViews() {
        View view=View.inflate(mActivity, R.layout.fragment_left_menu,null);

        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
       lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               mCurrentPos = position;
               mAdapter.notifyDataSetChanged();

               setCurrentMenuDetailPager(position);
               toggleSlidingMenu();//隐藏
           }
       });
    }

    private void toggleSlidingMenu() {
        SplashMainActivity mainUI= (SplashMainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        slidingMenu.toggle();//切换显示，显示时隐藏，隐藏时显示

    }

    //设置当前菜单详情页
    private void setCurrentMenuDetailPager(int position) {
        SplashMainActivity mainUI= (SplashMainActivity) mActivity;
        ContentFragment fragment=mainUI.getContentFragment();//获取主页面FRAGMENT
        NewsCenterPager pager =fragment.getNewsCenterPager();//获取新闻中心
        pager.setCurrentMenuDetailPager(position);//设置当前菜单详情页
    }

    //设置网络数据
    public void setMenuData(NewsData data){
        Log.i("info","拿到数据啦"+data);
        mMenuList=data.data;
        mAdapter=new MenuAdapter();
        lvList.setAdapter(mAdapter);
    }
//侧边栏适配器
    class MenuAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return mMenuList.size();
        }

        @Override
        public Object getItem(int position) {
            return mMenuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

              View view=View.inflate(mActivity,R.layout.list_menu_item,null);
            TextView tvtitle= (TextView) view.findViewById(R.id.tv_title);

            NewsData.NewsMenuDate newsMenuDate= (NewsData.NewsMenuDate) getItem(position);

            tvtitle.setText(newsMenuDate.title);


            //判断当前绘制的View是否被选中
            if (mCurrentPos == position){
                //显示红色
                tvtitle.setEnabled(true);

            }else {
                //显示白色
                tvtitle.setEnabled(false);
            }

            return view;
        }
    }

}
