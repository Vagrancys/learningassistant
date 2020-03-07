package com.vargancys.learningassistant.model.home.bean;

import com.vargancys.learningassistant.db.home.HomeKnowItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeContentBean {
    private int error;
    private String msg;
    private List<HomeKnowItem> contentBeans;

    public int getError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HomeKnowItem> getContentBeans() {
        return contentBeans;
    }

    public void setContentBeans(List<HomeKnowItem> contentBeans) {
        this.contentBeans = contentBeans;
    }
}
