package com.vargancys.learningassistant.widget.TreeDirectory;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.db.overview.OverViewListBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public class TreeHelper {
    public static <T>List<Node> getSortedNodes(List<T> datas,
                                              int defaultExpandLayer) throws IllegalArgumentException,
            IllegalAccessException{
        List<Node> result = new ArrayList<>();
        List<Node> nodes = convetData2Node(datas);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node:rootNodes){
            addNode(result,node,defaultExpandLayer,1);
        }
        return result;
    }

    public static List<Node> filterVisibleNode(List<Node> nodes){
        List<Node> result = new ArrayList<>();
        for (Node node:nodes){
            if(node.isRoot() || node.isParentExpand()){
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    private static <T> List<Node> convetData2Node(List<T> datas)
        throws IllegalArgumentException,IllegalAccessException{
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t:datas){
            int id = -1;
            int pId = -1;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields){
                if(f.getAnnotation(TreeNodeId.class) !=null){
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if(f.getAnnotation(TreeNodePid.class) !=null){
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }

                if(f.getAnnotation(TreeNodeLabel.class) !=null){
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }

                if(id != -1 && pId != -1 && label !=null){
                    break;
                }
            }
            node = new Node(id,pId,label);
            node.setScore(((OverViewListBean) t).getScore());
            node.setLevel(((OverViewListBean) t).getLevel());
            nodes.add(node);
        }

        for (int i = 0;i<nodes.size();i++){
            Node node2 = nodes.get(i);
            for (int j = i+1;j<nodes.size();j++){
                Node m = nodes.get(j);
                if(m.getpId() == node2.getId()){
                    node2.getChildren().add(m);
                    m.setParent(node2);
                }else if(m.getId() == node2.getpId()){
                    m.getChildren().add(node2);
                    node2.setParent(m);
                }
            }
        }
        for (Node node1 : nodes){
            setNodeIcon(node1);
        }
        return nodes;
    }

    private static List<Node> getRootNodes(List<Node> nodes){
        List<Node> root = new ArrayList<Node>();
        for (Node node :nodes){
            if(node.isRoot()){
                root.add(node);
            }
        }
        return root;
    }

    private static void addNode(List<Node> nodes,Node node,int defaultExpandLayer,int currentLayer){
        node.setLayer(currentLayer);
        nodes.add(node);
        if(defaultExpandLayer>=currentLayer){
            node.setExpand(true);
        }
        if(node.isLeaf()){
            return;
        }
        for (int i = 0;i<node.getChildren().size();i++){
            addNode(nodes,node.getChildren().get(i),defaultExpandLayer,currentLayer+1);
        }
    }

    private static void setNodeIcon(Node node){
        if(node.getChildren().size() > 0&& node.isExpand()){
            node.setIcon(R.drawable.overview_up_normal);
        }else if(node.getChildren().size()>0&& !node.isExpand()){
            node.setIcon(R.drawable.overview_down_normal);
        }else{
            node.setIcon(-1);
        }
    }
}













