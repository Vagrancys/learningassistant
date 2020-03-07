package com.vargancys.learningassistant.base;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class BaseApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
