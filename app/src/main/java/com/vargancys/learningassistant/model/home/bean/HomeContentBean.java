package com.vargancys.learningassistant.model.home.bean;

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
    private List<ContentBean> contentBeans;

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

    public List<ContentBean> getContentBeans() {
        return contentBeans;
    }

    public void setContentBeans(List<ContentBean> contentBeans) {
        this.contentBeans = contentBeans;
    }

    public class ContentBean{
        private boolean have;
        private String title;
        private int level;
        private String summary;
        private int progress;
        private int count;
        private int max;
        private int masterLevel;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getLevel() {
            return level;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isHave() {
            return have;
        }

        public int getMasterLevel() {
            return masterLevel;
        }

        public void setHave(boolean have) {
            this.have = have;
        }

        public int getMax() {
            return max;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getProgress() {
            return progress;
        }

        public void setMasterLevel(int masterLevel) {
            this.masterLevel = masterLevel;
        }

        public String getSummary() {
            return summary;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}
