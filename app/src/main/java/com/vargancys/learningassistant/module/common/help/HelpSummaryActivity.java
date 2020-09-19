package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;
import com.vargancys.learningassistant.module.common.adapter.HelpCommendAdapter;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;
import com.vargancys.learningassistant.presenter.common.help.HelpPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助内容页面
 */
public class HelpSummaryActivity extends BaseActivity
        implements HelpSummaryView{
    private String TAG = "HelpSummaryActivity";
    @BindView(R.id.help_number)
    TextView helpNumber;
    @BindView(R.id.help_title)
    TextView helpTitle;
    @BindView(R.id.help_summary)
    TextView helpSummary;
    @BindView(R.id.help_time)
    TextView helpTime;
    @BindView(R.id.help_praise)
    ImageView helpPraise;
    @BindView(R.id.help_praise_number)
    TextView helpPraiseNumber;
    @BindView(R.id.help_poor)
    ImageView helpPoor;
    @BindView(R.id.help_poor_number)
    TextView helpPoorNumber;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.commend_linear)
    LinearLayout commendLinear;
    @BindView(R.id.commend_edit)
    EditText commendEdit;
    @BindView(R.id.commend_number_text)
    TextView commendNumberText;

    private HelpPresenter helpPresenter;
    private HelpCommendAdapter helpCommendAdapter;
    private int Request = 2001;
    private int help_summary_id;
    private int commendNumber;

    private List<HelpCommendItem> helpCommendItems = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_summary;
    }

    @Override
    public void initView() {
        help_summary_id = getIntent().getIntExtra(ConstantsUtils.HELP_SUMMARY_ID,0);
        helpPresenter = new HelpPresenter<HelpSummaryView>(this);
        helpCommendAdapter = new HelpCommendAdapter(getContext(),helpCommendItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(helpCommendAdapter);
        helpPresenter.getSingleHelpData(help_summary_id);
        helpPresenter.getAllCommendData(help_summary_id);
        initListener();
    }

    private void initListener() {
        commendEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                commendEdit.setMinLines(5);
            }else{
                commendEdit.setMinLines(1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request
                &&data !=null){
            switch (data.getIntExtra(ConstantsUtils.HELP_UPDATE_STATE,0)){
                case 0:
                    ToastUtils.ToastText(getContext(), R.string.help_update_not_text);
                    break;
                case 1:
                    helpPresenter.reFreshSummary(help_summary_id);
                    break;
                case 2:
                    ToastUtils.ToastText(getContext(),R.string.help_update_fail_text);
                    break;
            }
        }
    }

    public static void launch(Activity activity, int position) {
        Intent intent = new Intent(activity, HelpSummaryActivity.class);
        intent.putExtra(ConstantsUtils.HELP_SUMMARY_ID, position);
        activity.startActivity(intent);
    }

    private void updateData(HelpContentBean helpContentItem){
        helpNumber.setText(String.valueOf(helpContentItem.getId()));
        helpTitle.setText(helpContentItem.getTitle());
        helpSummary.setText(helpContentItem.getSummary());
        helpTime.setText(helpContentItem.getTime());
        helpPraiseNumber.setText(String.valueOf(helpContentItem.getPraise()));
        helpPoorNumber.setText(String.valueOf(helpContentItem.getPoor()));

    }

    @Override
    public void findFinish(HelpContentBean object) {
        updateData(object);
    }

    @Override
    public void findError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg);
    }

    @Override
    public void findCommendError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"Msg = "+msg);
    }

    @Override
    public void findCommend(List<HelpCommendItem> items) {
        recyclerView.setVisibility(View.VISIBLE);
        commendLinear.setVisibility(View.GONE);
        if(items.size() >0){
            commendNumber = items.size();
            commendNumberText.setText("("+commendNumber+")");
        }else{
            commendNumberText.setText("");
        }
        helpCommendItems.addAll(items);
        helpCommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void findEmpty() {
        recyclerView.setVisibility(View.GONE);
        commendLinear.setVisibility(View.VISIBLE);
    }

    @Override
    public void reFreshSummary(HelpContentBean item) {
        updateData(item);
    }

    @Override
    public void PraiseOrPoor(int state,int number,String msg) {
        switch (state){
            case 0:
                helpPraiseNumber.setText(String.valueOf(number));
                break;
            case 1:
                helpPoorNumber.setText(String.valueOf(number));
                break;
        }
        ToastUtils.ToastText(getContext(),msg+"成功!");
    }

    @Override
    public void saveCommendFinish(HelpCommendItem item) {
        ToastUtils.ToastText(getContext(),R.string.help_update_commend_successful_text);
        helpPresenter.reFreshSummary(help_summary_id);
        commendNumberText.setText(String.valueOf(++commendNumber));
        recyclerView.setVisibility(View.VISIBLE);
        commendLinear.setVisibility(View.GONE);
        helpCommendItems.add(item);
        helpCommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveCommendError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @OnClick({R.id.common_back,R.id.common_update,R.id.help_praise,
            R.id.help_poor,R.id.commend_send})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_update:
                HelpUpdateActivity.launch(HelpSummaryActivity.this,help_summary_id,Request);
                break;
            case R.id.help_praise:
                if(helpPraise.isSelected()){
                    helpPraise.setSelected(false);
                    helpPresenter.addPraiseOrPoor(0,help_summary_id);
                }else{
                    helpPraise.setSelected(true);
                    helpPresenter.subPraiseOrPoor(0,help_summary_id);
                }
                break;
            case R.id.help_poor:
                if(helpPraise.isSelected()){
                    helpPraise.setSelected(false);
                    helpPresenter.addPraiseOrPoor(1,help_summary_id);
                }else{
                    helpPraise.setSelected(true);
                    helpPresenter.subPraiseOrPoor(1,help_summary_id);
                }
                break;
            case R.id.commend_send:
                helpPresenter.saveCommendData(help_summary_id,commendEdit.getText().toString());
                break;
        }
    }
}
