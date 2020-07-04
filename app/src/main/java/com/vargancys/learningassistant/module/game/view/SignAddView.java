package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.db.game.GameContent;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
public interface SignAddView extends BaseGameView{
    void initSignAddDataFinish(GameContent mContent);
    void initSignAddDataError(int error,String message);
    boolean isSignDataEmpty();
    void addSignData();
    void isSignDataEmptyError(int error,String message);
    void saveSignDataFinish();
    void saveSignDataError(int error,String message);
}
