package com.vargancys.learningassistant.widget.TreeDirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public class Node {
    private long id;
    //根节点pId为0
    private long pId = 0;
    private String name;
    //当前的级别
    private int level;
    //当前的层级
    private int layer;

    //是否展开
    private boolean isExpand = false;

    private int icon;

    //下一级的子Node
    private List<Node> children = new ArrayList<Node>();

    //父Node
    private Node parent;

    private int score;

    public Node(){

    }

    public Node(long id,long pId,String name){
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public long getpId() {
        return pId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return parent == null ? 0: parent.getLevel()+1;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if(!expand){
            for (Node node :children){
                node.setExpand(expand);
            }
        }
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    //是否为根节点
    public boolean isRoot(){
        return parent == null;
    }

    public boolean isExpand() {
        return isExpand;
    }

    //判断父节点是否展开
    public boolean isParentExpand(){
        if(parent == null){
            return false;
        }
        return parent.isExpand();
    }

    //是否是叶子节点
    public boolean isLeaf(){
        return children.size() == 0;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}












