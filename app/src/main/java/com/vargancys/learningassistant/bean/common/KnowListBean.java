package com.vargancys.learningassistant.bean.common;

import com.vargancys.learningassistant.widget.TreeDirectory.TreeNodeId;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeNodeLabel;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeNodePid;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class KnowListBean {
    @TreeNodeId
    private long _id;
    @TreeNodePid
    private long parentId;
    @TreeNodeLabel
    private String title;
    private int level;
    private int masterLevel;
    private boolean study;
    private int score;
    private long knowId;
    public KnowListBean(long _id, long parentId, String title){
        this._id = _id;
        this.parentId = parentId;
        this.title = title;
    }

    public long getKnowId() {
        return knowId;
    }

    public void setKnowId(long knowId) {
        this.knowId = knowId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public String getTitle() {
        return title;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }

    public boolean isStudy() {
        return study;
    }

    public long getParentId() {
        return parentId;
    }
}
