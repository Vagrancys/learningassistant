package com.vargancys.learningassistant.bean.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯问题项
 */
@Entity
public class LadderTopicBean {
    @Id
    @Unique
    private Long id;

    //类型
    private int type;

    //单选问题
    private String radio_title;

    //单选的答案
    private int radio_answer;

    //单选第一个答案
    private String radio_first_answer;

    //单选第二个答案
    private String radio_second_answer;

    //单选第三个答案
    private String radio_third_answer;

    //单选第四个答案
    private String radio_fourth_answer;

    //多选问题
    private String multiple_title;

    //多选的第一个答案
    private boolean multiple_first_answer;

    //多选的第二个答案
    private boolean multiple_second_answer;

    //多选的第三个答案
    private boolean multiple_third_answer;

    //多选的第四个答案
    private boolean multiple_fourth_answer;

    //多选第一个回答
    private String multiple_first_title;

    //多选第二个回答
    private String multiple_second_title;

    //多选第三个回答
    private String multiple_third_title;

    //多选第四个回答
    private String multiple_fourth_title;

    //填空问题
    private String fill_title;

    //填空答案
    private int fill_answer;

    //填空第一个回答
    private String fill_first_answer;

    //填空第二个回答
    private String fill_second_answer;

    //填空第三个回答
    private String fill_third_answer;

    //填空第四个回答
    private String fill_fourth_answer;

    //主观问题
    private String subjective_title;

    //主观参考答案
    private String subjective_answer;

    @Generated(hash = 116359106)
    public LadderTopicBean(Long id, int type, String radio_title, int radio_answer,
            String radio_first_answer, String radio_second_answer,
            String radio_third_answer, String radio_fourth_answer,
            String multiple_title, boolean multiple_first_answer,
            boolean multiple_second_answer, boolean multiple_third_answer,
            boolean multiple_fourth_answer, String multiple_first_title,
            String multiple_second_title, String multiple_third_title,
            String multiple_fourth_title, String fill_title, int fill_answer,
            String fill_first_answer, String fill_second_answer,
            String fill_third_answer, String fill_fourth_answer,
            String subjective_title, String subjective_answer) {
        this.id = id;
        this.type = type;
        this.radio_title = radio_title;
        this.radio_answer = radio_answer;
        this.radio_first_answer = radio_first_answer;
        this.radio_second_answer = radio_second_answer;
        this.radio_third_answer = radio_third_answer;
        this.radio_fourth_answer = radio_fourth_answer;
        this.multiple_title = multiple_title;
        this.multiple_first_answer = multiple_first_answer;
        this.multiple_second_answer = multiple_second_answer;
        this.multiple_third_answer = multiple_third_answer;
        this.multiple_fourth_answer = multiple_fourth_answer;
        this.multiple_first_title = multiple_first_title;
        this.multiple_second_title = multiple_second_title;
        this.multiple_third_title = multiple_third_title;
        this.multiple_fourth_title = multiple_fourth_title;
        this.fill_title = fill_title;
        this.fill_answer = fill_answer;
        this.fill_first_answer = fill_first_answer;
        this.fill_second_answer = fill_second_answer;
        this.fill_third_answer = fill_third_answer;
        this.fill_fourth_answer = fill_fourth_answer;
        this.subjective_title = subjective_title;
        this.subjective_answer = subjective_answer;
    }

    @Generated(hash = 183396880)
    public LadderTopicBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRadio_title() {
        return this.radio_title;
    }

    public void setRadio_title(String radio_title) {
        this.radio_title = radio_title;
    }

    public int getRadio_answer() {
        return this.radio_answer;
    }

    public void setRadio_answer(int radio_answer) {
        this.radio_answer = radio_answer;
    }

    public String getRadio_first_answer() {
        return this.radio_first_answer;
    }

    public void setRadio_first_answer(String radio_first_answer) {
        this.radio_first_answer = radio_first_answer;
    }

    public String getRadio_second_answer() {
        return this.radio_second_answer;
    }

    public void setRadio_second_answer(String radio_second_answer) {
        this.radio_second_answer = radio_second_answer;
    }

    public String getRadio_third_answer() {
        return this.radio_third_answer;
    }

    public void setRadio_third_answer(String radio_third_answer) {
        this.radio_third_answer = radio_third_answer;
    }

    public String getRadio_fourth_answer() {
        return this.radio_fourth_answer;
    }

    public void setRadio_fourth_answer(String radio_fourth_answer) {
        this.radio_fourth_answer = radio_fourth_answer;
    }

    public String getMultiple_title() {
        return this.multiple_title;
    }

    public void setMultiple_title(String multiple_title) {
        this.multiple_title = multiple_title;
    }

    public boolean getMultiple_first_answer() {
        return this.multiple_first_answer;
    }

    public void setMultiple_first_answer(boolean multiple_first_answer) {
        this.multiple_first_answer = multiple_first_answer;
    }

    public boolean getMultiple_second_answer() {
        return this.multiple_second_answer;
    }

    public void setMultiple_second_answer(boolean multiple_second_answer) {
        this.multiple_second_answer = multiple_second_answer;
    }

    public boolean getMultiple_third_answer() {
        return this.multiple_third_answer;
    }

    public void setMultiple_third_answer(boolean multiple_third_answer) {
        this.multiple_third_answer = multiple_third_answer;
    }

    public boolean getMultiple_fourth_answer() {
        return this.multiple_fourth_answer;
    }

    public void setMultiple_fourth_answer(boolean multiple_fourth_answer) {
        this.multiple_fourth_answer = multiple_fourth_answer;
    }

    public String getMultiple_first_title() {
        return this.multiple_first_title;
    }

    public void setMultiple_first_title(String multiple_first_title) {
        this.multiple_first_title = multiple_first_title;
    }

    public String getMultiple_second_title() {
        return this.multiple_second_title;
    }

    public void setMultiple_second_title(String multiple_second_title) {
        this.multiple_second_title = multiple_second_title;
    }

    public String getMultiple_third_title() {
        return this.multiple_third_title;
    }

    public void setMultiple_third_title(String multiple_third_title) {
        this.multiple_third_title = multiple_third_title;
    }

    public String getMultiple_fourth_title() {
        return this.multiple_fourth_title;
    }

    public void setMultiple_fourth_title(String multiple_fourth_title) {
        this.multiple_fourth_title = multiple_fourth_title;
    }

    public String getFill_title() {
        return this.fill_title;
    }

    public void setFill_title(String fill_title) {
        this.fill_title = fill_title;
    }

    public int getFill_answer() {
        return this.fill_answer;
    }

    public void setFill_answer(int fill_answer) {
        this.fill_answer = fill_answer;
    }

    public String getFill_first_answer() {
        return this.fill_first_answer;
    }

    public void setFill_first_answer(String fill_first_answer) {
        this.fill_first_answer = fill_first_answer;
    }

    public String getFill_second_answer() {
        return this.fill_second_answer;
    }

    public void setFill_second_answer(String fill_second_answer) {
        this.fill_second_answer = fill_second_answer;
    }

    public String getFill_third_answer() {
        return this.fill_third_answer;
    }

    public void setFill_third_answer(String fill_third_answer) {
        this.fill_third_answer = fill_third_answer;
    }

    public String getFill_fourth_answer() {
        return this.fill_fourth_answer;
    }

    public void setFill_fourth_answer(String fill_fourth_answer) {
        this.fill_fourth_answer = fill_fourth_answer;
    }

    public String getSubjective_title() {
        return this.subjective_title;
    }

    public void setSubjective_title(String subjective_title) {
        this.subjective_title = subjective_title;
    }

    public String getSubjective_answer() {
        return this.subjective_answer;
    }

    public void setSubjective_answer(String subjective_answer) {
        this.subjective_answer = subjective_answer;
    }
}
