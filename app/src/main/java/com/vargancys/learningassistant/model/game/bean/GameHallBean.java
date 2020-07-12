package com.vargancys.learningassistant.model.game.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅数据实体类
 */
public class GameHallBean {
    private List<Banner> mBanner;
    private List<Hall> mHall;

    public List<Banner> getBanner() {
        return mBanner;
    }

    public void setBanner(List<Banner> mBanner) {
        this.mBanner = mBanner;
    }

    public List<Hall> getHall() {
        return mHall;
    }

    public void setHall(List<Hall> mHall) {
        this.mHall = mHall;
    }

    public static class Hall{
        private String title;
        private String summary;
        private int total;
        private int people;
        private long id;
        private long author;
        private String time;

        public String getPeople(){
            return String.valueOf(people);
        }

        public void setPeople(int people) {
            this.people = people;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSummary() {
            return summary;
        }

        public void setAuthor(long author) {
            this.author = author;
        }

        public String getTotal() {
            return String.valueOf(total);
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public long getAuthor() {
            return author;
        }
    }

    //知识体系轮播类
    public static class Banner{
        private String title;
        private long id;
        private String imgUri;
        private String summary;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUri() {
            return imgUri;
        }

        public void setImgUri(String imgUri) {
            this.imgUri = imgUri;
        }
    }
}
