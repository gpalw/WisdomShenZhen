package com.twowen.wisdomshenzhen.domain;

import java.util.ArrayList;

/**
 * Created by lenovo on 2015/11/7.
 * 网络分类信息的封装
 * 字段名字必须和服务器返回的字段名一致
 * 方便JSON解析
 *
 */
public class NewsData {
    public int retcode;

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }

    public ArrayList<NewsMenuDate> data;
    /**
     * 侧边栏数据对象
     * */
    public class NewsMenuDate {
        public String id;

        @Override
        public String toString() {
            return "NewsMenuDate{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }

        public String title;
        public int type;
        public String url;

        public ArrayList<NewsTabData> children;

    }
    /**
     * 新闻页面下的11个子标签数据
     * */

    public  class NewsTabData{
        public String id;


        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }

        public String title;
        public int type;
        public String url;

    }

}
