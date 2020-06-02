package com.vargancys.learningassistant.model.mine.bean;

/**
 * @author Vagrancy
 * @date 2020/6/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题详情实体类
 */
public class ProblemDetailsBean {
    //标题
    private String title;
    //数量
    private int count;
    //等级
    private int level;
    //观看
    private int people;
    //使用
    private int use;
    //问题类型
    private int type;
    //单选问题
    private RadioItem mRadio;

    //多选问题
    private MultipleItem mMultiple;
    //填空问题
    private FillItem mFill;
    //主观问题
    private SubjectiveItem mSubjective;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getLevel() {
        return level;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getPeople() {
        return people;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFill(FillItem mFill) {
        this.mFill = mFill;
    }

    public FillItem getFill() {
        return mFill;
    }

    public void setMultiple(MultipleItem mMultiple) {
        this.mMultiple = mMultiple;
    }

    public int getUse() {
        return use;
    }

    public void setRadio(RadioItem mRadio) {
        this.mRadio = mRadio;
    }

    public MultipleItem getMultiple() {
        return mMultiple;
    }

    public void setSubjective(SubjectiveItem mSubjective) {
        this.mSubjective = mSubjective;
    }

    public RadioItem getRadio() {
        return mRadio;
    }

    public SubjectiveItem getSubjective() {
        return mSubjective;
    }


    public static class RadioItem{
        //问题
        private String title;
        //答案
        private int answer;
        //第一个答案
        private String radio_first_answer;

        //第二个答案
        private String radio_second_answer;

        //第三个答案
        private String radio_third_answer;

        //第四个答案
        private String radio_fourth_answer;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAnswer() {
            return answer;
        }

        public void setAnswer(int answer) {
            this.answer = answer;
        }

        public String getRadio_first_answer() {
            return radio_first_answer;
        }

        public void setRadio_first_answer(String radio_first_answer) {
            this.radio_first_answer = radio_first_answer;
        }

        public String getRadio_fourth_answer() {
            return radio_fourth_answer;
        }

        public void setRadio_fourth_answer(String radio_fourth_answer) {
            this.radio_fourth_answer = radio_fourth_answer;
        }

        public String getRadio_second_answer() {
            return radio_second_answer;
        }

        public void setRadio_second_answer(String radio_second_answer) {
            this.radio_second_answer = radio_second_answer;
        }

        public String getRadio_third_answer() {
            return radio_third_answer;
        }

        public void setRadio_third_answer(String radio_third_answer) {
            this.radio_third_answer = radio_third_answer;
        }
    }

    public static class MultipleItem{

        //问题
        private String title;

        //第一个答案
        private boolean multiple_first_answer;

        //第二个答案
        private boolean multiple_second_answer;

        //第三个答案
        private boolean multiple_third_answer;

        //第四个答案
        private boolean multiple_fourth_answer;

        //第一个回案
        private String multiple_first_title;

        //第二个回案
        private String multiple_second_title;

        //第三个回案
        private String multiple_third_title;

        //第四个回案
        private String multiple_fourth_title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMultiple_third_title() {
            return multiple_third_title;
        }

        public void setMultiple_third_title(String multiple_third_title) {
            this.multiple_third_title = multiple_third_title;
        }

        public String getMultiple_second_title() {
            return multiple_second_title;
        }

        public void setMultiple_third_answer(boolean multiple_third_answer) {
            this.multiple_third_answer = multiple_third_answer;
        }

        public String getMultiple_fourth_title() {
            return multiple_fourth_title;
        }

        public void setMultiple_second_title(String multiple_second_title) {
            this.multiple_second_title = multiple_second_title;
        }

        public String getMultiple_first_title() {
            return multiple_first_title;
        }

        public void setMultiple_second_answer(boolean multiple_second_answer) {
            this.multiple_second_answer = multiple_second_answer;
        }

        public void setMultiple_fourth_title(String multiple_fourth_title) {
            this.multiple_fourth_title = multiple_fourth_title;
        }

        public void setMultiple_fourth_answer(boolean multiple_fourth_answer) {
            this.multiple_fourth_answer = multiple_fourth_answer;
        }

        public void setMultiple_first_title(String multiple_first_title) {
            this.multiple_first_title = multiple_first_title;
        }

        public void setMultiple_first_answer(boolean multiple_first_answer) {
            this.multiple_first_answer = multiple_first_answer;
        }

        public boolean isMultiple_third_answer() {
            return multiple_third_answer;
        }

        public boolean isMultiple_second_answer() {
            return multiple_second_answer;
        }

        public boolean isMultiple_fourth_answer() {
            return multiple_fourth_answer;
        }

        public boolean isMultiple_first_answer() {
            return multiple_first_answer;
        }

    }

    public static class FillItem{
        //问题
        private String title;

        //第一个答案
        private int fill_answer;

        //第一个回案
        private String fill_first_title;

        //第二个回案
        private String fill_second_title;

        //第三个回案
        private String fill_third_title;

        //第四个回案
        private String fill_fourth_title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFill_third_title() {
            return fill_third_title;
        }

        public void setFill_third_title(String fill_third_title) {
            this.fill_third_title = fill_third_title;
        }

        public String getFill_second_title() {
            return fill_second_title;
        }

        public void setFill_second_title(String fill_second_title) {
            this.fill_second_title = fill_second_title;
        }

        public String getFill_fourth_title() {
            return fill_fourth_title;
        }

        public void setFill_fourth_title(String fill_fourth_title) {
            this.fill_fourth_title = fill_fourth_title;
        }

        public String getFill_first_title() {
            return fill_first_title;
        }

        public void setFill_first_title(String fill_first_title) {
            this.fill_first_title = fill_first_title;
        }

        public int getFill_answer() {
            return fill_answer;
        }

        public void setFill_answer(int fill_answer) {
            this.fill_answer = fill_answer;
        }
    }

    public static class SubjectiveItem{
        //问题
        private String title;
        //答案
        private String answer;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
