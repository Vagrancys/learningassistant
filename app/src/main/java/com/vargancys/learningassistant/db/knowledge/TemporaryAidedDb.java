package com.vargancys.learningassistant.db.knowledge;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/8
 * version:1.0
 * 模块名: 临时辅助类数据类
 */
@Entity
public class TemporaryAidedDb {
    @Id
    private long db_id;
    private int id;
    private String title;
    private int count;
    private String summary;
    private String explain;
    private int level;
    private int father_id;
    private String directory;
    private String now_explain;
    private String deep_explain;
    private String advance;
    private String experience;
    private String publicize;
    private String Case;
    @Generated(hash = 1680426654)
    public TemporaryAidedDb(long db_id, int id, String title, int count,
            String summary, String explain, int level, int father_id,
            String directory, String now_explain, String deep_explain,
            String advance, String experience, String publicize, String Case) {
        this.db_id = db_id;
        this.id = id;
        this.title = title;
        this.count = count;
        this.summary = summary;
        this.explain = explain;
        this.level = level;
        this.father_id = father_id;
        this.directory = directory;
        this.now_explain = now_explain;
        this.deep_explain = deep_explain;
        this.advance = advance;
        this.experience = experience;
        this.publicize = publicize;
        this.Case = Case;
    }
    @Generated(hash = 1132144786)
    public TemporaryAidedDb() {
    }
    public long getDb_id() {
        return this.db_id;
    }
    public void setDb_id(long db_id) {
        this.db_id = db_id;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getExplain() {
        return this.explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getFather_id() {
        return this.father_id;
    }
    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }
    public String getDirectory() {
        return this.directory;
    }
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public String getNow_explain() {
        return this.now_explain;
    }
    public void setNow_explain(String now_explain) {
        this.now_explain = now_explain;
    }
    public String getDeep_explain() {
        return this.deep_explain;
    }
    public void setDeep_explain(String deep_explain) {
        this.deep_explain = deep_explain;
    }
    public String getAdvance() {
        return this.advance;
    }
    public void setAdvance(String advance) {
        this.advance = advance;
    }
    public String getExperience() {
        return this.experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getPublicize() {
        return this.publicize;
    }
    public void setPublicize(String publicize) {
        this.publicize = publicize;
    }
    public String getCase() {
        return this.Case;
    }
    public void setCase(String Case) {
        this.Case = Case;
    }
}
