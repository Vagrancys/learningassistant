package com.vargancys.learningassistant.db.common;

import org.litepal.crud.LitePalSupport;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HelpCommendItem extends LitePalSupport {
    private String summary;
    private String time;
    private HelpContentItem helpContentItem;

    public void setHelpContentItem(HelpContentItem helpContentItem) {
        this.helpContentItem = helpContentItem;
    }

    public HelpContentItem getHelpContentItem() {
        return helpContentItem;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
