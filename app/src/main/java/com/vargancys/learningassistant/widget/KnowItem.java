package com.vargancys.learningassistant.widget;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class KnowItem {
    private long id;
    private int centerPaintX;
    private int centerPaintY;
    private int parentCenterPaintX;
    private int parentCenterPaintY;
    private int level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCenterPaintX() {
        return centerPaintX;
    }

    public void setCenterPaintX(int centerPaintX) {
        this.centerPaintX = centerPaintX;
    }

    public int getCenterPaintY() {
        return centerPaintY;
    }

    public void setCenterPaintY(int centerPaintY) {
        this.centerPaintY = centerPaintY;
    }

    public int getParentCenterPaintX() {
        return parentCenterPaintX;
    }

    public void setParentCenterPaintX(int parentCenterPaintX) {
        this.parentCenterPaintX = parentCenterPaintX;
    }

    public void setParentCenterPaintY(int parentCenterPaintY) {
        this.parentCenterPaintY = parentCenterPaintY;
    }

    public int getParentCenterPaintY() {
        return parentCenterPaintY;
    }
}
