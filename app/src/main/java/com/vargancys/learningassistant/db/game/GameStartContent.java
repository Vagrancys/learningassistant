package com.vargancys.learningassistant.db.game;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/16
 * version:1.0
 */
public class GameStartContent {
    //知识模块的id
    private long game_id;
    //知识总项的id
    private long content_id;
    //知识最低项的id
    private long start_id;
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
    private String multiple_title;

    //多选的第一个回答
    private String multiple_first_title;

    //多选的第二个回答
    private String multiple_second_title;

    //多选的第三个回答
    private String multiple_third_title;

    //多选的第四个回答
    private String multiple_fourth_title;

    //多选的第一个答案
    private boolean multiple_first_answer;

    //多选的第二个答案
    private boolean multiple_second_answer;

    //多选的第三个答案
    private boolean multiple_third_answer;

    //多选的第四个答案
    private boolean multiple_fourth_answer;

    //填空的标题
    private String fill_title;

    //填空的答案
    private int fill_answer;

    //填空的第一个答案
    private String fill_first_title;

    //填空的第二个答案
    private String fill_second_title;

    //填空的第三个答案
    private String fill_third_title;

    //填空的第四个答案
    private String fill_fourth_title;

    //主观题标题
    private String subjective_title;
    //主观题答案
    private String subjective_answer;

    public long getStart_id() {
        return start_id;
    }

    public void setStart_id(long start_id) {
        this.start_id = start_id;
    }

    public String getSubjective_answer() {
        return subjective_answer;
    }

    public void setSubjective_answer(String subjective_answer) {
        this.subjective_answer = subjective_answer;
    }

    public void setSubjective_title(String subjective_title) {
        this.subjective_title = subjective_title;
    }

    public String getSubjective_title() {
        return subjective_title;
    }

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

    public String getMultiple_first_title() {
        return multiple_first_title;
    }

    public void setMultiple_first_answer(boolean multiple_first_answer) {
        this.multiple_first_answer = multiple_first_answer;
    }

    public String getMultiple_fourth_title() {
        return multiple_fourth_title;
    }

    public String getMultiple_second_title() {
        return multiple_second_title;
    }

    public void setMultiple_first_title(String multiple_first_title) {
        this.multiple_first_title = multiple_first_title;
    }

    public void setMultiple_fourth_answer(boolean multiple_fourth_answer) {
        this.multiple_fourth_answer = multiple_fourth_answer;
    }

    public void setMultiple_fourth_title(String multiple_fourth_title) {
        this.multiple_fourth_title = multiple_fourth_title;
    }

    public void setMultiple_second_answer(boolean multiple_second_answer) {
        this.multiple_second_answer = multiple_second_answer;
    }

    public void setMultiple_second_title(String multiple_second_title) {
        this.multiple_second_title = multiple_second_title;
    }

    public void setMultiple_third_answer(boolean multiple_third_answer) {
        this.multiple_third_answer = multiple_third_answer;
    }

    public void setMultiple_third_title(String multiple_third_title) {
        this.multiple_third_title = multiple_third_title;
    }

    public void setMultiple_title(String multiple_title) {
        this.multiple_title = multiple_title;
    }

    public String getMultiple_third_title() {
        return multiple_third_title;
    }

    public String getMultiple_title() {
        return multiple_title;
    }

    public boolean isMultiple_first_answer() {
        return multiple_first_answer;
    }

    public boolean isMultiple_fourth_answer() {
        return multiple_fourth_answer;
    }

    public boolean isMultiple_second_answer() {
        return multiple_second_answer;
    }

    public boolean isMultiple_third_answer() {
        return multiple_third_answer;
    }

    public int getFill_answer() {
        return fill_answer;
    }

    public void setFill_answer(int fill_answer) {
        this.fill_answer = fill_answer;
    }

    public String getFill_first_title() {
        return fill_first_title;
    }

    public String getFill_fourth_title() {
        return fill_fourth_title;
    }

    public void setFill_first_title(String fill_first_title) {
        this.fill_first_title = fill_first_title;
    }

    public String getFill_second_title() {
        return fill_second_title;
    }

    public void setFill_fourth_title(String fill_fourth_title) {
        this.fill_fourth_title = fill_fourth_title;
    }

    public String getFill_third_title() {
        return fill_third_title;
    }

    public void setFill_second_title(String fill_second_title) {
        this.fill_second_title = fill_second_title;
    }

    public void setFill_third_title(String fill_third_title) {
        this.fill_third_title = fill_third_title;
    }

    public String getFill_title() {
        return fill_title;
    }

    public void setFill_title(String fill_title) {
        this.fill_title = fill_title;
    }

    public long getContent_id() {
        return content_id;
    }

    public void setContent_id(long content_id) {
        this.content_id = content_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public long getGame_id() {
        return game_id;
    }
}
