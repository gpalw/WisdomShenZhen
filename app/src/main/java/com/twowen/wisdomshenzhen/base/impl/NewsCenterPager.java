package com.twowen.wisdomshenzhen.base.impl;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.twowen.wisdomshenzhen.SplashMainActivity;
import com.twowen.wisdomshenzhen.base.BaseMenuDetailPager;
import com.twowen.wisdomshenzhen.base.BasePager;
import com.twowen.wisdomshenzhen.base.menudetail.InteractMenuDetailPager;
import com.twowen.wisdomshenzhen.base.menudetail.NewsMenuDetailPager;
import com.twowen.wisdomshenzhen.base.menudetail.PhotoMenuDetailPager;
import com.twowen.wisdomshenzhen.base.menudetail.TopicMenuDetailPager;
import com.twowen.wisdomshenzhen.domain.NewsData;
import com.twowen.wisdomshenzhen.fragment.LeftMenuFragment;
import com.twowen.wisdomshenzhen.global.GlobalContants;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/11/4.
 * 新闻中心的实现
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Activity activity) {
        super(activity);
    }
    private NewsData mNewsdata;//接受数据
    private ArrayList<BaseMenuDetailPager> mPagers;//4个菜单详情页

    @Override
    public void initData() {
        tvTitle.setText("新闻");
        setSlidingMenuEnable(true);//打开侧边栏
        getDataFromSever();


    }
    //从服务器获取数据
    private void getDataFromSever() {
        HttpUtils utils=new HttpUtils();

        //使用Xutils发送请求
        utils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL, new RequestCallBack<String>() {
            //访问成功
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Toast.makeText(mActivity, "访问成功", Toast.LENGTH_SHORT).show();
                Log.d("info", "返回结果是：" + result);
                parseData(result);
            }

            //访问失败
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, "请求错误，请重试" + s, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
    //解析网络数据
    private void parseData(String result) {
        Gson gson= new Gson();

        mNewsdata= gson.fromJson(result, NewsData.class);
        //刷新侧边栏数据
        SplashMainActivity mainUI= (SplashMainActivity) mActivity;
        LeftMenuFragment leftMenuFragment=mainUI.getLeftMenuFragment();
        leftMenuFragment.setMenuData(mNewsdata);

        //准备4个菜单详情页
        mPagers=new ArrayList<BaseMenuDetailPager>();
        mPagers.add(new NewsMenuDetailPager(mActivity,mNewsdata.data.get(0).children));
        mPagers.add(new TopicMenuDetailPager(mActivity));
        mPagers.add(new PhotoMenuDetailPager(mActivity));
        mPagers.add(new InteractMenuDetailPager(mActivity));

        setCurrentMenuDetailPager(0);//设置菜单详情页-新闻为默认当前页
    }
    //设置当前菜单详情页
    public void setCurrentMenuDetailPager(int postition){
        BaseMenuDetailPager parger=mPagers.get(postition);//获取当前要显示的菜单详情页
        rlContent.removeAllViews();//清除之前的布局
        rlContent.addView(parger.mRootView);//将菜单详情页的布局设置给帧布局

        NewsData.NewsMenuDate menuDate=mNewsdata.data.get(postition);//设置当前页标题
        tvTitle.setText(menuDate.title);

        parger.initData();//初始化当前页面的数据
    }


}
