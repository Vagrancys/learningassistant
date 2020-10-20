package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.home.bean.ClassTreeBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 首页知识函数展示适配器
 */
public class ShowClassTreeAdapter extends BaseClassTreeAdapter {
    private List<ClassTreeBean> trees;
    private int position = 0;
    private List<ClassTreeListBean> lists = new ArrayList<>();
    public ShowClassTreeAdapter(Context context, ArrayList<ClassTreeBean> trees){
        super(context,0,R.layout.knowledge_class_show_header_item,R.layout.knowledge_class_show_item);
        this.trees = trees;
        initClassTree();
    }

    private void initClassTree() {
        for (ClassTreeBean tree : trees) {
            addHeader(tree);
            addItem(tree);
        }
        setTree(lists);
    }

    public void setTrees(List<ClassTreeBean> tree){
        trees.clear();
        trees.addAll(tree);
        initClassTree();
    }

    private void addItem(ClassTreeBean tree) {
        for (ClassTreeBean.ClassTreeItemBean item : tree.getItems()) {
            ClassTreeListBean mList = new ClassTreeListBean();
            ClassTreeListBean.ClassTreeItem mItem = new ClassTreeListBean.ClassTreeItem();
            mItem.setFather_id(tree.getTree_id());
            mItem.setItem_id(item.getTree_item_id());
            mItem.setLevel(item.getLevel());
            mItem.setPosition(position++);
            mItem.setTitle(item.getTitle());
            mItem.setSummary(item.getSummary());
            mList.setType(2);
            mList.setItem(mItem);
            lists.add(mList);
        }
    }

    private void addHeader(ClassTreeBean tree){
        ClassTreeListBean mList = new ClassTreeListBean();
        ClassTreeListBean.ClassTreeHeader mHeader = new ClassTreeListBean.ClassTreeHeader();
        mHeader.setCount(tree.getItems().size());
        mHeader.setHeader_id(tree.getTree_id());
        mHeader.setPosition(position++);
        mHeader.setTitle(tree.getTitle());
        mList.setType(1);
        mList.setHeader(mHeader);
        lists.add(mList);
    }

    @Override
    CommonViewHolder getHeaderHolder(View view) {
        return new ClassHeaderViewHolder(view);
    }

    @Override
    CommonViewHolder getItemHolder(View view) {
        return new ClassItemViewHolder(view);
    }

    @Override
    CommonViewHolder getAddHolder(View view) {
        return new ClassAddViewHolder(view);
    }

    @Override
    void onBindAddHolder(CommonViewHolder holder, int position) {
    }

    @Override
    void onBindHeaderHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeHeader header) {
        ClassHeaderViewHolder mHolder = (ClassHeaderViewHolder) holder;
        mHolder.classHeaderCount.setText(header.getCount());
        mHolder.classHeaderTitle.setText(header.getTitle());
    }

    @Override
    void onBindItemHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeItem item) {
        ClassItemViewHolder mHolder = (ClassItemViewHolder) holder;
        int imageId;
        switch (item.getLevel()){
            case 1:
                imageId = R.drawable.know_level_1;
                break;
            case 2:
                imageId = R.drawable.know_level_2;
                break;
            default:
                imageId = R.drawable.know_level_0;
                break;
        }
        mHolder.classItemLevel.setBackgroundResource(imageId);
        mHolder.classItemTitle.setText(item.getTitle());
        mHolder.classItemSummary.setText(item.getSummary());
    }

    public class ClassHeaderViewHolder extends CommonViewHolder{
        @BindView(R.id.class_header_count)
        TextView classHeaderCount;
        @BindView(R.id.class_header_title)
        TextView classHeaderTitle;
        private ClassHeaderViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }

    public class ClassItemViewHolder extends CommonViewHolder{
        @BindView(R.id.class_item_level)
        ImageView classItemLevel;
        @BindView(R.id.class_item_title)
        TextView classItemTitle;
        @BindView(R.id.class_item_summary)
        TextView classItemSummary;
        private ClassItemViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }

    public class ClassAddViewHolder extends CommonViewHolder{
        private ClassAddViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}

