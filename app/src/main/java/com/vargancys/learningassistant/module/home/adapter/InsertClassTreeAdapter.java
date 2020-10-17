package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
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
 * 首页知识第四适配器
 */
public class InsertClassTreeAdapter extends BaseClassTreeAdapter {
    private List<ClassTreeBean> trees;
    private int position = 0;
    private List<ClassTreeListBean> lists = new ArrayList<>();
    public InsertClassTreeAdapter(Context context, ArrayList<ClassTreeBean> trees){
        super(context,R.layout.knowledge_class_add_item,R.layout.knowledge_class_header_item,R.layout.knowledge_class_item);
        this.trees = trees;
        initClassTree();
    }

    private void initClassTree() {
        for (ClassTreeBean tree : trees) {
            addHeader(tree);
            addItem(tree);
        }
        ClassTreeListBean mList = new ClassTreeListBean();
        mList.setType(3);
        lists.add(mList);
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
        ClassAddViewHolder mHolder = (ClassAddViewHolder) holder;
        mHolder.classAddLinear.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onAdd(position);
            }
        });
    }

    @Override
    void onBindHeaderHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeHeader header) {
        ClassHeaderViewHolder mHolder = (ClassHeaderViewHolder) holder;
        mHolder.classHeaderCount.setText(header.getCount());
        mHolder.classHeaderTitle.setText(header.getTitle());
        mHolder.classHeaderAdd.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onHeaderAdd(header.getPosition(),header.getHeader_id());
            }
        });
        mHolder.classHeaderUpdate.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onHeaderUpdate(header.getPosition(),header.getHeader_id());
            }
        });
        mHolder.classHeaderDelete.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onHeaderDelete(header.getPosition(),header.getHeader_id());
            }
        });
    }

    @Override
    void onBindItemHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeItem item) {
        ClassItemViewHolder mHolder = (ClassItemViewHolder) holder;
        int imageId = 0;
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
        mHolder.classItemUpdate.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onItemUpdate(item.getPosition(),item.getFather_id(),item.getItem_id());
            }
        });
        mHolder.classItemDelete.setOnClickListener(v -> {
            if(onClickClassListener != null){
                onClickClassListener.onItemDelete(item.getPosition(),item.getFather_id(),item.getItem_id());
            }
        });
    }

    public class ClassHeaderViewHolder extends CommonViewHolder{
        @BindView(R.id.class_header_count)
        TextView classHeaderCount;
        @BindView(R.id.class_header_title)
        TextView classHeaderTitle;
        @BindView(R.id.class_header_add)
        ImageView classHeaderAdd;
        @BindView(R.id.class_header_update)
        ImageView classHeaderUpdate;
        @BindView(R.id.class_header_delete)
        ImageView classHeaderDelete;
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
        @BindView(R.id.class_item_update)
        ImageView classItemUpdate;
        @BindView(R.id.class_item_delete)
        ImageView classItemDelete;
        @BindView(R.id.class_item_summary)
        TextView classItemSummary;
        private ClassItemViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }

    public class ClassAddViewHolder extends CommonViewHolder{
        @BindView(R.id.class_add_linear)
        LinearLayout classAddLinear;
        private ClassAddViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }

    private OnClickClassListener onClickClassListener;

    public void setOnClickClassListener(OnClickClassListener onClickClassListener) {
        this.onClickClassListener = onClickClassListener;
    }

    public interface OnClickClassListener{
        void onAdd(int position);
        void onItemUpdate(int position,int header_id,int item_id);
        void onItemDelete(int position,int header_id,int item_id);
        void onHeaderAdd(int position,int header_id);
        void onHeaderUpdate(int position,int header_id);
        void onHeaderDelete(int position,int header_id);
    }
}

