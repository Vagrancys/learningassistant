package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
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
    private List<ClassTreeListBean> lists = new ArrayList<>();
    public InsertClassTreeAdapter(Context context, ArrayList<ClassTreeBean> trees){
        super(context,0,0);
        this.trees = trees;
        initClassTree();
    }

    private void initClassTree() {

        setTree(lists);
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
    void onBindHeaderHolder(CommonViewHolder holder, int position) {

    }

    @Override
    void onBindItemHolder(CommonViewHolder holder, int position) {

    }

    public class ClassHeaderViewHolder extends CommonViewHolder{
        private ClassHeaderViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }

    public class ClassItemViewHolder extends CommonViewHolder{
        private ClassItemViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}

