package com.vargancys.learningassistant.model.common.bean;

import com.vargancys.learningassistant.db.common.HelpContentItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpContentBean {
    private String msg;
    private int error;
    private List<HelpContentItem> helpContentItems;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<HelpContentItem> getHelpContentItems() {
        return helpContentItems;
    }

    public void setHelpContentItems(List<HelpContentItem> helpContentItems) {
        this.helpContentItems = helpContentItems;
    }
}
