package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯各项挑战记录实体类
 */
public class ChallengeItemBean {
    private long id;
    private int type;
    private String title;
    private int count;
    private int success;
    private int fail;
    private int highest;
    private int time;
    private int number;
    private int difficulty;
    private List<ChallengeItem> mItems;

    public List<ChallengeItem> getItems() {
        return mItems;
    }

    public void setItems(List<ChallengeItem> mItems) {
        this.mItems = mItems;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getFail() {
        return fail;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getHighest() {
        return highest;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public static class ChallengeItem{
        private long id;
        private String title;
        private int serial;
        private String summary;
        private int highest;
        private long time;
        private boolean situation;

        public boolean isSituation() {
            return situation;
        }

        public int getHighest() {
            return highest;
        }

        public void setHighest(int highest) {
            this.highest = highest;
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

        public void setTime(long time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSerial() {
            return serial;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public long getTime() {
            return time;
        }

        public void setSerial(int serial) {
            this.serial = serial;
        }

        public void setSituation(boolean situation) {
            this.situation = situation;
        }
    }
}
