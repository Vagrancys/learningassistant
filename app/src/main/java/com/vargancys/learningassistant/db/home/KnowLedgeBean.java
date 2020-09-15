package com.vargancys.learningassistant.db.home;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 首页知识item的表结构
 */
public class KnowLedgeBean {
    public static String MASTERLEVEL = "master_level";
    public static String PROGRESS = "progress";
    public static String TITLE = "title";
    public static String ACTIVITY = "activity";
    public static String HAVE = "have";
    public static String LEVEL = "level";
    public static String SUMMARY = "summary";
    public static String STUDTTITLE = "study_title";
    public static String MAX = "max";
    public static String COUNT = "count";
    public static String CREATECLASS = "create_class";
    //知识项id
    private int id;

    //用户id
    public long memberId;
    public long contentId;
    private long dataId;

    //官方知识
    private boolean official;

    //知识项观看人数
    private int look;

    //知识项的演示需要类
    private String activity;

    //知识项演示判断
    private boolean have;

    //知识项的标题
    private String title;

    //知识项的复杂与深度度
    private int level;

    //知识项的简要概述
    private String summary;

    //知识项的观看进度
    private int progress;

    //知识项当前消耗度
    private int count;

    //知识项最大消耗度
    private int max;
    //知识项的掌握程度
    private int masterLevel;

    //知识项的学习要求
    private String studyTitle;

    //知识项内容是否创建了
    private boolean createClass;

    //知识的语言类别language
    private int language;

    //知识的时间
    private int time;

    //问题数量
    private int problem;

    //知识的类型
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOfficial() {
        return this.official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean getHave() {
        return this.have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMasterLevel() {
        return this.masterLevel;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public String getStudyTitle() {
        return this.studyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        this.studyTitle = studyTitle;
    }

    public boolean getCreateClass() {
        return this.createClass;
    }

    public void setCreateClass(boolean createClass) {
        this.createClass = createClass;
    }

    public long getContentId() {
        return this.contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getLook() {
        return this.look;
    }

    public void setLook(int look) {
        this.look = look;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getProblem() {
        return this.problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
