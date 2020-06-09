package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级各项实体类
 */
public class LevelItemBean {
    private long id;
    private String title;
    private String news;
    private String next;
    private String experience;
    private String next_experience;
    private String rank;

    public List<LevelItem> getItems() {
        return mItems;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    private List<LevelItem> mItems;

    public void setItems(List<LevelItem> mItems) {
        this.mItems = mItems;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNext_experience() {
        return next_experience;
    }

    public void setNext_experience(String next_experience) {
        this.next_experience = next_experience;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class LevelItem{

        private String title;
        private String level;
        private String experience;
        private String next_experience;
        private String rank;
        private String time;
        private String privilege;



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

        public String getExperience() {
            return experience;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getNext_experience() {
            return next_experience;
        }

        public void setNext_experience(String next_experience) {
            this.next_experience = next_experience;
        }

        public String getPrivilege() {
            return privilege;
        }

        public void setPrivilege(String privilege) {
            this.privilege = privilege;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
