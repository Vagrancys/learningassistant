package com.vargancys.learningassistant.db.game;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/16
 * version:1.0
 */
public class GameStartContent {
    //答题的类型
    private int type;

    //单选的问题标题
    private String radio_title;

    //单选的第一个答案
    private String radio_first_title;
    //单选的第二个答案

    private String radio_second_title;
    //单选的第三个答案

    private String radio_third_title;
    //单选的第四个答案

    private String radio_fourth_title;
    //单选的答案
    private int radio_yes;

    //多选的标题

    //多选的第一个回答
    //多选的第二个回答
    //多选的第三个回答
    //多选的第四个回答
    //多选的第一个答案
    //多选的第二个答案
    //多选的第三个答案
    //多选的第四个答案

    public String getRadio_title() {
        return radio_title;
    }

    public void setRadio_title(String radio_title) {
        this.radio_title = radio_title;
    }

    public void setRadio_first_title(String radio_first_title) {
        this.radio_first_title = radio_first_title;
    }

    public void setRadio_fourth_title(String radio_fourth_title) {
        this.radio_fourth_title = radio_fourth_title;
    }

    public void setRadio_second_title(String radio_second_title) {
        this.radio_second_title = radio_second_title;
    }

    public void setRadio_third_title(String radio_third_title) {
        this.radio_third_title = radio_third_title;
    }

    public void setRadio_yes(int radio_yes) {
        this.radio_yes = radio_yes;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRadio_second_title() {
        return radio_second_title;
    }

    public int getRadio_yes() {
        return radio_yes;
    }

    public int getType() {
        return type;
    }

    public String getRadio_first_title() {
        return radio_first_title;
    }

    public String getRadio_fourth_title() {
        return radio_fourth_title;
    }

    public String getRadio_third_title() {
        return radio_third_title;
    }
}
