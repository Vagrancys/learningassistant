package com.vargancys.learningassistant.model.mine.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.MineDataBeanDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心数据请求类
 */
public class MineRequest {
    private static MineRequest mRequest;
    private MineDataBeanDao mMineDataDao;
    private DaoSession mDaoSession;
    private HomeKnowItemDao mKnowDao;
    private LadderDataBeanDao mLadderDataDao;
    private MineRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mMineDataDao = mDaoSession.getMineDataBeanDao();
        mKnowDao = mDaoSession.getHomeKnowItemDao();
        mLadderDataDao = mDaoSession.getLadderDataBeanDao();
    }
    public static MineRequest getInstance(){
        if(mRequest == null){
            synchronized (MineRequest.class){
                if(mRequest == null){
                    mRequest = new MineRequest();
                }
            }
        }
        return mRequest;
    }


    //得到个人中心的数据
    public MineDataBean getMineData(long mineId) {
        return mMineDataDao.load(mineId);
    }

    //得到个人中心知识用户数据
    public MineDataBean getKnowLedgeData(long mineId) {
        return mMineDataDao.load(mineId);
    }

    //得到个人中心知识类型数据
    public KnowLedgeTypeDataBean getKnowLedgeTypeData(long mineId) {
        //TODO 知识类型数据
        return null;
    }

    //得到个人发布的所有知识
    public List<HomeKnowItem> getHomeKnowData(int type) {
        QueryBuilder<HomeKnowItem> queryBuilder = mKnowDao.queryBuilder();
        return queryBuilder.where(HomeKnowItemDao.Properties.Language.eq(type)).list();
    }

    //得到个人中心天梯类型数据
    public ChallengeTypeDataBean getChallengeTypeData(long mineId) {
        //TODO 天梯类型数据
        return null;
    }

    //得到个人中心天梯详情数据
    public LadderDataBean getChallengeDetailsData(long challengeId) {
        return mLadderDataDao.load(challengeId);
    }

    //得到个人中心天梯各项数据
    public List<LadderDataBean> getChallengeItemData(int type) {
        return mLadderDataDao.loadAll();
    }
}
