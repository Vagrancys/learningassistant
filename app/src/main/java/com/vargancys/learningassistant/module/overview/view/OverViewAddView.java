package com.vargancys.learningassistant.module.overview.view;

/**
 * @author Vagrancy
 * @date 2020/4/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加视图层
 */
public interface OverViewAddView extends BaseOverView{
    void saveDataFinish();

    void saveDataError(int error, String message);

    void TidyAllData();
}
