package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识项数据
 */
public class KnowLedgeItemBean {
    private String title;
    private String count;
    private int quality;
    private int time;
    private int people;
    private int level;
    private int prize;
    private int type;
    private List<KnowLedgeItem> items;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public int getPeople() {
        return people;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrize() {
        return prize;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getQuality() {
        return quality;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getCount() {
        return count;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }



    public List<KnowLedgeItem> getItems() {
        return items;
    }

    public void setItems(List<KnowLedgeItem> items) {
        this.items = items;
    }

    public static class KnowLedgeItem{
        private Long id;
        private String title;
        private int number;
        private int level;
        private String summary;
        private int time;
        private int problem;
        private int look;
        private boolean createClass;
        private boolean have;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public boolean isHave() {
            return have;
        }

        public void setHave(boolean have) {
            this.have = have;
        }

        public void setCreateClass(boolean createClass) {
            this.createClass = createClass;
        }

        public boolean isCreateClass() {
            return createClass;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getTime() {
            return time;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getLook() {
            return look;
        }

        public void setLook(int look) {
            this.look = look;
        }

        public int getProblem() {
            return problem;
        }

        public void setProblem(int problem) {
            this.problem = problem;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
