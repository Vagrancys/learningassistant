package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.home.bean.FeelingBean;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/22
 * version:1.0
 * 模块名: 感悟型知识适配器
 */
public class CommonFeelingItemAdapter extends BaseFeelingTreeAdapter {
    private boolean show = false;
    public CommonFeelingItemAdapter(Context context,boolean show, List<FeelingBean.FeelingItemBean> mItem) {
        super(context, R.layout.knowledge_feeling_add_item,R.layout.knowledge_feeling_item);
        setTree(mItem);
        this.show = show;
    }

    @Override
    CommonViewHolder getItemHolder(View view) {
        return new FeelingItemViewHolder(view);
    }

    @Override
    CommonViewHolder getAddHolder(View view) {
        return new FeelingAddViewHolder(view);
    }

    @Override
    void onBindItemHolder(CommonViewHolder holder, int position, FeelingBean.FeelingItemBean item) {
        FeelingItemViewHolder mHolder = (FeelingItemViewHolder) holder;
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
        mHolder.feelingItemLevel.setBackgroundResource(imageId);
        mHolder.feelingItemTitle.setText(item.getTitle());
        mHolder.feelingItemSummary.setText(item.getSummary());
        if(show){
            mHolder.feelingItemDelete.setVisibility(View.GONE);
            mHolder.feelingItemUpdate.setVisibility(View.GONE);
        }else{
            mHolder.feelingItemUpdate.setOnClickListener(v -> {
                if(onClickFeelingListener != null){
                    onClickFeelingListener.onItemUpdate(item.getPosition(),item.getItem_id());
                }
            });
            mHolder.feelingItemDelete.setOnClickListener(v -> {
                if(onClickFeelingListener != null){
                    onClickFeelingListener.onItemDelete(item.getPosition(),item.getItem_id());
                }
            });
        }

    }

    @Override
    void onBindAddHolder(CommonViewHolder holder, int position) {
        FeelingAddViewHolder mHolder = (FeelingAddViewHolder) holder;
        mHolder.feelingAddLinear.setOnClickListener(v -> {
            if(onClickFeelingListener != null){
                onClickFeelingListener.onAdd(position);
            }
        });
    }

    public class FeelingItemViewHolder extends CommonViewHolder{
        @BindView(R.id.feeling_item_level)
        ImageView feelingItemLevel;
        @BindView(R.id.feeling_item_title)
        TextView feelingItemTitle;
        @BindView(R.id.feeling_item_update)
        ImageView feelingItemUpdate;
        @BindView(R.id.feeling_item_delete)
        ImageView feelingItemDelete;
        @BindView(R.id.feeling_item_summary)
        TextView feelingItemSummary;
        private FeelingItemViewHolder(View view){
            super(view);
        }
    }

    public class FeelingAddViewHolder extends CommonViewHolder{
        @BindView(R.id.feeling_add_linear)
        LinearLayout feelingAddLinear;
        public FeelingAddViewHolder(View view){
            super(view);
        }
    }

    private OnClickFeelingListener onClickFeelingListener;

    public void setOnClickFeelingListener(OnClickFeelingListener onClickFeelingListener) {
        this.onClickFeelingListener = onClickFeelingListener;
    }

    public interface OnClickFeelingListener{
        void onAdd(int position);
        void onItemUpdate(int position,int item_id);
        void onItemDelete(int position,int item_id);
    }
}
