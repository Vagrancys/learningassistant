package com.vargancys.learningassistant.widget.TreeDirectory;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public class FileBean {
    @TreeNodeId
    private int _id;
    @TreeNodePid
    private int parentId;
    @TreeNodeLabel
    private String name;
    private long length;
    private String desc;
    public FileBean(int _id,int parentId,String name){
        super();
        this._id = _id;
        this.parentId = parentId;
        this.name = name;
    }
}





















