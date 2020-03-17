package com.vargancys.learningassistant.db.home;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HomeKnowContent extends LitePalSupport {
    //详细知识的id
    private int id;
    //详细知识的标题
    private String title;

    //详细知识的简介
    private String summary;

    //详细知识的示范
    private String show;

    //详细知识的解释
    private String explain;

    //详细知识的注意点
    private String heed;

    //详细知识的使用心得
    private String experience;

    private HomeKnowItem homeKnowItem;

    public HomeKnowItem getHomeKnowItem() {
        return homeKnowItem;
    }

    public void setHomeKnowItem(HomeKnowItem homeKnowItem) {
        this.homeKnowItem = homeKnowItem;
    }

    //详细函数的数据项
    private List<HomeKnowFunction> homeKnowFunctions = new ArrayList<>();

    public List<HomeKnowFunction> getHomeKnowFunctions() {
        return homeKnowFunctions;
    }

    public void setHomeKnowFunctions(List<HomeKnowFunction> homeKnowFunctions) {
        this.homeKnowFunctions = homeKnowFunctions;
    }

    public int getId() {
        return id;
    }

    public void setId(int content_id) {
        this.id = content_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected long getBaseObjId() {
        return super.getBaseObjId();
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }

    public void setHeed(String heed) {
        this.heed = heed;
    }

    public String getHeed() {
        return heed;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getShow() {
        return show;
    }
}
