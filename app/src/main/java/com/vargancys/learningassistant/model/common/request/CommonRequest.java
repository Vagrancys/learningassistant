package com.vargancys.learningassistant.model.common.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.MemberBeanDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.common.MemberBean;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 公共数据请求层
 */
public class CommonRequest {
    private static CommonRequest mRequest;
    private DaoSession mDaoSession;
    private MemberBeanDao mMember;

    private CommonRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mMember = mDaoSession.getMemberBeanDao();
    }

    public static CommonRequest getInstance(){
        if(mRequest == null){
            synchronized (CommonRequest.class){
                if(mRequest == null){
                    mRequest = new CommonRequest();
                }
            }
        }
        return mRequest;
    }

    //判断帐号是否存在
    public boolean isExistName(String name) {
        MemberBean member = mMember.queryBuilder().where(MemberBeanDao.Properties.Title.eq(name)).unique();
        if(member != null){
            return true;
        }
        return false;
    }

    public boolean loginName(String name, String password) {
        MemberBean member = mMember.queryBuilder().where(MemberBeanDao.Properties.Title.eq(name),MemberBeanDao.Properties.Password.eq(password)).unique();
        if(member != null){
            return true;
        }
        return false;
    }

    //保存密码!
    public boolean updatePasswordData(String name) {
        MemberBean member = new MemberBean();
        member.setPassword(name);
        member.setTitle("2222");
        long result = mMember.insert(member);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    //发送验证码
    public boolean loadCodeName() {
        return true;
    }

    //注册用户
    public boolean saveMember(String name, String password) {
        MemberBean mBean = new MemberBean();
        mBean.setTitle(name);
        mBean.setPassword(password);
        long result = mMember.insert(mBean);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
}
