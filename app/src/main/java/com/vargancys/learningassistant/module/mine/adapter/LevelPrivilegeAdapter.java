package com.vargancys.learningassistant.module.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/31
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级权力适配器
 */
public class LevelPrivilegeAdapter extends BaseRecyclerAdapter {
    private List<MineLevelPrivilegeBean> mBean;

    public LevelPrivilegeAdapter(Context context, List<MineLevelPrivilegeBean> bean) {
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        LevelPrivilegeViewHolder mHolder = (LevelPrivilegeViewHolder) holder;
        MineLevelPrivilegeBean bean = mBean.get(position);
        mHolder.levelPrivilegeTitle.setText(bean.getTitle());
        mHolder.levelPrivilegeCondition.setText(bean.getCondition());
        mHolder.levelPrivilegeLevel.setText(bean.getLevel());
        mHolder.levelPrivilegeSummary.setText(bean.getSummary());
        mHolder.levelPrivilegeTime.setText(bean.getTime());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LevelPrivilegeViewHolder(getView(R.layout.level_privilege_item));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class LevelPrivilegeViewHolder extends CommonViewHolder {
        @BindView(R.id.level_privilege_level)
        TextView levelPrivilegeLevel;
        @BindView(R.id.level_privilege_title)
        TextView levelPrivilegeTitle;
        @BindView(R.id.level_privilege_condition)
        TextView levelPrivilegeCondition;
        @BindView(R.id.level_privilege_summary)
        TextView levelPrivilegeSummary;
        @BindView(R.id.level_privilege_time)
        TextView levelPrivilegeTime;
        public LevelPrivilegeViewHolder(View view) {
            super(view);
        }
    }
}
