package com.vargancys.learningassistant.model.common.bean;

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
    private List<com.vargancys.learningassistant.db.common.HelpContentBean> helpContentItems;

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

    public List<com.vargancys.learningassistant.db.common.HelpContentBean> getHelpContentItems() {
        return helpContentItems;
    }

    public void setHelpContentItems(List<com.vargancys.learningassistant.db.common.HelpContentBean> helpContentItems) {
        this.helpContentItems = helpContentItems;
    }
}
