package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项详情实体类
 */
public class ProblemItemBean {
    //标题
    private String title;
    //等级
    private String level;
    //使用人数
    private String people;
    //使用次数
    private String use;
    //数量
    private String count;
    //发布时间
    private String time;

    //类型
    private int type;
    private List<ProblemItem> mItems;

    public void setItems(List<ProblemItem> mItems) {
        this.mItems = mItems;
    }

    public List<ProblemItem> getItems() {
        return mItems;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeople() {
        return people;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUse() {
        return use;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public static class ProblemItem{
        private long id;
        private String title;
        private String time;
        private String type;
        private String level;
        private String use;
        private String people;
        private String context;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }

        public String getUse() {
            return use;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPeople() {
            return people;
        }

        public void setUse(String use) {
            this.use = use;
        }

        public String getContext() {
            return context;
        }

        public void setPeople(String people) {
            this.people = people;
        }

        public String getType() {
            return type;
        }

        public void setContext(String context) {
            this.context = context;
        }

    }
}

