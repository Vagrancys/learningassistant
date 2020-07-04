package com.vargancys.learningassistant.model.overview.bean;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅排行数据
 */
public class OverViewHallRankBean {
    private String title;
    private long id;
    private String summary;
    private int quality;
    private int hot;
    private int system;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getQuality() {
        return String.valueOf(quality);
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getHot() {
        return String.valueOf(hot);
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getSystem() {
        return String.valueOf(system);
    }

    public void setSystem(int system) {
        this.system = system;
    }
}
