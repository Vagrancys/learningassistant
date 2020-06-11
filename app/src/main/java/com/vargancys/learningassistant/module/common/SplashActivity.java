package com.vargancys.learningassistant.module.common;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.member.LoginActivity;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/02/29
 * version:1.0
 * 跳转页面
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    @BindView(R.id.splash_image)
    ImageView splashImage;
    @BindView(R.id.splash_countdown)
    TextView splashCountdown;
    @BindView(R.id.splash_version)
    TextView splashVersion;

    private CountDownTimer countDownTimer;
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        countDownTimer = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                splashCountdown.setText("跳过 "+millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                if(CacheUtils.getBoolean(getContext(), ConstantsUtils.GROUP_STATE)){

                    Log.e(TAG,"Cache");
                    if(CacheUtils.getBoolean(getContext(),ConstantsUtils.LOGIN_STATE)){
                        MainActivity.launch(SplashActivity.this);
                    }else{
                        LoginActivity.launch(SplashActivity.this);
                    }
                }else{
                    GroupActivity.launch(SplashActivity.this);
                }
                finish();
                countDownTimer.cancel();
            }
        };

        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),PackageInfo.INSTALL_LOCATION_AUTO);
            splashVersion.setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        countDownTimer.start();
    }

    @OnClick({R.id.splash_image, R.id.splash_countdown})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.splash_image:
                //ToastUtils.ToastText(this,"广告图片点击事件!");
                countDownTimer.cancel();
                break;
            case R.id.splash_countdown:
                //ToastUtils.ToastText(this,"倒计时点击事件!");
                MainActivity.launch(SplashActivity.this);
                countDownTimer.cancel();
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
