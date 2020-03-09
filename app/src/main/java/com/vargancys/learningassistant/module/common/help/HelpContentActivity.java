package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.home.bean.HomeContentBean;
import com.vargancys.learningassistant.module.common.adapter.HelpContentAdapter;
import com.vargancys.learningassistant.module.common.view.HelpContentView;
import com.vargancys.learningassistant.persenter.common.help.HelpContentPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助中心页
 */
public class HelpContentActivity extends BaseActivity implements HelpContentView{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.common_add)
    ImageView commonAdd;
    @BindView(R.id.common_back)
    ImageView commonBack;

    private HelpContentPresenter helpContentPresenter;
    private HelpContentAdapter helpContentAdapter;
    private int RequestCode = 2003;
    private List<HelpContentItem> mBean = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_help_content;
    }

    @Override
    public void initView() {
        helpContentPresenter = new HelpContentPresenter(this);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        helpContentAdapter = new HelpContentAdapter(getContext(),mBean);
        recyclerView.setAdapter(helpContentAdapter);
        initListener();
        helpContentPresenter.getAllBean();
    }

    private void initListener(){
        swipeRefresh.setOnRefreshListener(new HelpContentOnRefreshListener());
        helpContentAdapter.setOnItemClickListener(new HelpContentOnItemClickListener());
        helpContentAdapter.setOnItemLongClickListener(new HelpContentOnItemLongClickListener());
    }

    @Override
    public void showContentBean(List<HelpContentItem> bean) {
        Log.e("helpContentActivity","showContentBean = "+bean.size());
        Log.e("helpContentActivity","showContentBean = "+bean.get(0).getTitle());
        mBean.addAll(bean);
        helpContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(int error, String msg) {
        swipeRefresh.setRefreshing(false);
        fragmentContent.setText(getText(R.string.help_error_text));
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg);
    }

    @Override
    public void hideEmpty() {
        swipeRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        swipeRefresh.setRefreshing(false);
        fragmentContent.setText(getText(R.string.help_empty_text));
        fragmentEmpty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void deleteFinish(int position) {
        ToastUtils.ToastText(getContext(),"删除帮助成功了!");
        helpContentAdapter.notifyItemRemoved(position);
    }

    @Override
    public void deleteError() {
        ToastUtils.ToastText(getContext(),"删除帮助失败了!");
    }

    class HelpContentOnItemLongClickListener implements BaseRecyclerAdapter.OnItemLongClickListener{
        @Override
        public void OnItemLongClick(int position) {
            final HelpContentItem helpContentItem = (HelpContentItem) mBean.get(position);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle(helpContentItem.getTitle());
            alertDialog.setMessage(helpContentItem.getSummary());
            alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    helpContentPresenter.deleteHelpData(helpContentItem.getId());
                    dialog.dismiss();
                }
            });
            alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    class HelpContentOnItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{
        @Override
        public void OnItemClick(int position) {
            HelpContentItem helpContentItem = (HelpContentItem) mBean.get(position);
            HelpSummaryActivity.launch(HelpContentActivity.this,helpContentItem.getId());
        }
    }

    class HelpContentOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            swipeRefresh.setRefreshing(true);
            helpContentPresenter.getAllBean();
        }
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpAddActivity.launch(HelpContentActivity.this,RequestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode&&resultCode == HelpAddActivity.ResultCode&&data != null){
            int addCount = data.getIntExtra(ConstantsUtils.HELP_ADD_COUNT,0);
            if(addCount >0){
                helpContentPresenter.getAllBean();
            }
        }
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, HelpContentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showRefreshView() {
        swipeRefresh.setRefreshing(true);
    }
}
