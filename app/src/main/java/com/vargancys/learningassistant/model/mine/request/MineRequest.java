package com.vargancys.learningassistant.model.mine.request;

import android.content.Context;

import com.vagrancys.learningassistant.db.ChallengeDataBeanDao;
import com.vagrancys.learningassistant.db.ChallengePartBeanDao;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vagrancys.learningassistant.db.KnowLedgeDataBeanDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.LevelDataBeanDao;
import com.vagrancys.learningassistant.db.LevelPartBeanDao;
import com.vagrancys.learningassistant.db.MineDataBeanDao;
import com.vagrancys.learningassistant.db.MineFeedbackBeanDao;
import com.vagrancys.learningassistant.db.MineLevelPrivilegeBeanDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.mine.ChallengeDataBean;
import com.vargancys.learningassistant.db.mine.ChallengePartBean;
import com.vargancys.learningassistant.db.mine.KnowLedgeDataBean;
import com.vargancys.learningassistant.db.mine.LevelDataBean;
import com.vargancys.learningassistant.db.mine.LevelPartBean;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.db.mine.MineFeedbackBean;
import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.model.mine.bean.ChallengeItemBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.LevelItemBean;
import com.vargancys.learningassistant.model.mine.bean.LevelTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemDetailsBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemTypeDataBean;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.TimeUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
    private OverViewListContentDao mOverViewDao;
    private MineLevelPrivilegeBeanDao mPrivilegeDao;
    private MineFeedbackBeanDao mFeedbackDao;
    private KnowLedgeDataBeanDao mKnowLedgeDao;
    private HomeKnowItemDao mKnowItemDao;
    private ChallengeDataBeanDao mChallengeDao;
    private ChallengePartBeanDao mChallengePartDao;
    private LevelDataBeanDao mLevelDao;
    private LevelPartBeanDao mLevelPartDao;
    private MineRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mMineDataDao = mDaoSession.getMineDataBeanDao();
        mKnowDao = mDaoSession.getHomeKnowItemDao();
        mLadderDataDao = mDaoSession.getLadderDataBeanDao();
        mOverViewDao = mDaoSession.getOverViewListContentDao();
        mPrivilegeDao = mDaoSession.getMineLevelPrivilegeBeanDao();
        mFeedbackDao = mDaoSession.getMineFeedbackBeanDao();
        mKnowLedgeDao = mDaoSession.getKnowLedgeDataBeanDao();
        mKnowItemDao = mDaoSession.getHomeKnowItemDao();
        mChallengeDao = mDaoSession.getChallengeDataBeanDao();
        mChallengePartDao = mDaoSession.getChallengePartBeanDao();
        mLevelDao = mDaoSession.getLevelDataBeanDao();
        mLevelPartDao = mDaoSession.getLevelPartBeanDao();
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
    public KnowLedgeTypeDataBean getKnowLedgeTypeData(Context mContext,long mineId) {
        //1:android 2:java 3:c# 4:php 5:html 6:javascript 7:css
        KnowLedgeTypeDataBean mBean = new KnowLedgeTypeDataBean();
        String[] count = ResourceUtils.getStringArray(mContext, R.array.knowledge_count);
        for (int i = 0;i<count.length;i++){
            KnowLedgeItemBean mItem = new KnowLedgeItemBean();
            KnowLedgeDataBean data = mKnowLedgeDao.queryBuilder().where(KnowLedgeDataBeanDao.Properties.MineId.eq(mineId),
                    KnowLedgeDataBeanDao.Properties.Type.eq(i)).unique();
            mItem.setCount(data.getCount());
            mItem.setType(data.getType());
            mItem.setLevel(data.getLevel());
            mItem.setPrize(data.getPrize());
            mItem.setPeople(data.getPeople());
            mItem.setQuality(data.getQuality());
            mItem.setTime(data.getTime());
            mItem.setTitle(data.getTitle());
            List<HomeKnowItem> mHome = mKnowItemDao.queryBuilder().where(HomeKnowItemDao.Properties.MemberId.eq(mineId),HomeKnowItemDao.Properties.Language.eq(i)).list();
            List<KnowLedgeItemBean.KnowLedgeItem> mLedge = new ArrayList<>();
            for (HomeKnowItem home:mHome){
                KnowLedgeItemBean.KnowLedgeItem mKnow = new KnowLedgeItemBean.KnowLedgeItem();
                mKnow.setCreateClass(home.getCreateClass());
                mKnow.setHave(home.getHave());
                mKnow.setLevel(home.getLevel());
                mKnow.setLook(home.getLook());
                mKnow.setNumber(home.getId().intValue());
                mKnow.setProblem(home.getProblem());
                mKnow.setSummary(home.getSummary());
                mKnow.setTitle(home.getTitle());
                mKnow.setTime(home.getTime());
                mKnow.setType(home.getType());
                mLedge.add(mKnow);
            }
            mItem.setItems(mLedge);
            mBean.getItemBeans().add(mItem);
        }
        return mBean;
    }

    //得到个人发布的所有知识
    public List<HomeKnowItem> getHomeKnowData(int type) {
        QueryBuilder<HomeKnowItem> queryBuilder = mKnowDao.queryBuilder();
        return queryBuilder.where(HomeKnowItemDao.Properties.Language.eq(type)).list();
    }

    //得到个人中心天梯类型数据
    public ChallengeTypeDataBean getChallengeTypeData(Context mContext,long mineId) {
        ChallengeTypeDataBean mBean = new ChallengeTypeDataBean();
        String[] count = ResourceUtils.getStringArray(mContext, R.array.ladder_title);
        for (int i = 0;i<count.length;i++){
            ChallengeItemBean mItem = new ChallengeItemBean();
            ChallengeDataBean data = mChallengeDao.queryBuilder().where(ChallengeDataBeanDao.Properties.MineId.eq(mineId),
                    ChallengeDataBeanDao.Properties.Type.eq(i)).unique();
            mItem.setCount(data.getCount());
            mItem.setType(data.getType());
            mItem.setFail(data.getFail());
            mItem.setDifficulty(data.getDifficulty());
            mItem.setHighest(data.getHighest());
            mItem.setNumber(data.getNumber());
            mItem.setSuccess(data.getSuccess());
            mItem.setTime(data.getTime());
            mItem.setTitle(data.getTitle());
            List<ChallengePartBean> mHome = mChallengePartDao.queryBuilder().where(ChallengePartBeanDao.Properties.MineId.eq(mineId),ChallengePartBeanDao.Properties.Type.eq(i)).list();
            List<ChallengeItemBean.ChallengeItem> mLedge = new ArrayList<>();
            for (ChallengePartBean home:mHome){
                ChallengeItemBean.ChallengeItem mKnow = new ChallengeItemBean.ChallengeItem();
                mKnow.setHighest(home.getHighest());
                mKnow.setSerial(home.getSerial());
                mKnow.setSituation(home.getSituation());
                mKnow.setSummary(home.getSummary());
                mKnow.setTitle(home.getTitle());
                mKnow.setTime(home.getTime());
                mKnow.setId(home.getId());
                mLedge.add(mKnow);
            }
            mItem.setItems(mLedge);
            mBean.getItemBeans().add(mItem);
        }
        return mBean;
    }

    //得到个人中心天梯详情数据
    public LadderDataBean getChallengeDetailsData(long challengeId) {
        return mLadderDataDao.load(challengeId);
    }

    //得到个人中心天梯各项数据
    public List<LadderDataBean> getChallengeItemData(int type) {
        return mLadderDataDao.queryBuilder().where(LadderDataBeanDao.Properties.Type.eq(type)).list();
    }

    public List<OverViewListContent> getSystemTypeData(long mineId) {
        return mOverViewDao.loadAll();
    }

    //得到个人中心等级详细数据
    public LevelItemBean.LevelItem getLevelDetailsData(long levelId) {
        LevelPartBean mBean = mLevelPartDao.load(levelId);
        LevelItemBean.LevelItem mItem = new LevelItemBean.LevelItem();
        mItem.setLevel(mBean.getLevel());
        mItem.setTitle(mBean.getTitle());
        mItem.setTime(mBean.getTime());
        mItem.setExperience(mBean.getExperience());
        mItem.setNext_experience(mBean.getNext_experience());
        mItem.setPrivilege(mBean.getPrivilege());
        mItem.setRank(mBean.getRank());
        return mItem;
    }

    //得到个人中心等级权力数据
    public List<MineLevelPrivilegeBean> getLevelPrivilegeData() {
        return mPrivilegeDao.loadAll();
    }

    //得到个人中心问题数据
    public ProblemTypeDataBean getProblemTypeData(long mineId) {
        return null;
    }

    //得到个人中心问题详情数据
    public ProblemDetailsBean getProblemDetailsData(long detailsId) {
        //TODO 处理个人中心问题详情数据
        return null;
    }

    //保存反馈的数据
    public boolean saveFeedbackData(String edit) {
        MineFeedbackBean mBean = new MineFeedbackBean();
        mBean.setCommon(edit);
        mBean.setTime(TimeUtils.getLongTime());
        long result = mFeedbackDao.insert(mBean);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    //得到问题各项数据
    public List<KnowLedgeItemBean.KnowLedgeItem> getProblemItemData(long problemId,int type) {
        List<HomeKnowItem> mHome = mKnowItemDao.queryBuilder().where(HomeKnowItemDao.Properties.MemberId.eq(problemId),HomeKnowItemDao.Properties.Language.eq(type)).list();
        List<KnowLedgeItemBean.KnowLedgeItem> mLedge = new ArrayList<>();
        for (HomeKnowItem home:mHome){
            KnowLedgeItemBean.KnowLedgeItem mKnow = new KnowLedgeItemBean.KnowLedgeItem();
            mKnow.setCreateClass(home.getCreateClass());
            mKnow.setHave(home.getHave());
            mKnow.setLevel(home.getLevel());
            mKnow.setLook(home.getLook());
            mKnow.setNumber(home.getId().intValue());
            mKnow.setProblem(home.getProblem());
            mKnow.setSummary(home.getSummary());
            mKnow.setTitle(home.getTitle());
            mKnow.setTime(home.getTime());
            mKnow.setType(home.getType());
            mLedge.add(mKnow);
        }
        return mLedge;
    }

    //得到等级的各个等级数据
    public LevelTypeDataBean getLevelTypeData(Context mContext,long levelId) {
        LevelTypeDataBean mBean = new LevelTypeDataBean();
        String[] count = ResourceUtils.getStringArray(mContext, R.array.level_count);
        for (int i = 0;i<count.length;i++){
            LevelItemBean mItem = new LevelItemBean();
            LevelDataBean data = mLevelDao.queryBuilder().where(LevelDataBeanDao.Properties.MineId.eq(levelId),
                    LevelDataBeanDao.Properties.Type.eq(i)).unique();
            mItem.setExperience(data.getExperience());
            mItem.setNext_experience(data.getNext_experience());
            mItem.setNews(data.getNews());
            mItem.setRank(data.getRank());
            mItem.setNext(data.getNext());
            mItem.setTitle(data.getTitle());
            List<LevelPartBean> mHome = mLevelPartDao.queryBuilder().where(LevelPartBeanDao.Properties.MineId.eq(levelId),
                    LevelPartBeanDao.Properties.Type.eq(i)).list();
            List<LevelItemBean.LevelItem> mLedge = new ArrayList<>();
            for (LevelPartBean home:mHome){
                LevelItemBean.LevelItem mKnow = new LevelItemBean.LevelItem();
                mKnow.setLevel(home.getLevel());
                mKnow.setTitle(home.getTitle());
                mKnow.setTime(home.getTime());
                mKnow.setExperience(home.getExperience());
                mKnow.setNext_experience(home.getNext_experience());
                mKnow.setPrivilege(home.getPrivilege());
                mKnow.setRank(home.getRank());
                mLedge.add(mKnow);
            }
            mItem.setItems(mLedge);
            mBean.getItemBeans().add(mItem);
        }
        return mBean;
    }
}
