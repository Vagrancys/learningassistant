package com.vargancys.learningassistant.model.overview.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.home.request.KnowUpdateRequest;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewRequest {
    private static OverViewRequest mRequest;
    private OverViewListItemDao mListItemDao;
    private DaoSession mDaoSession;
    private OverViewListContentDao mListContentDao;
    private GameSubjectContentDao mSubjectContentDao;
    private GameContentDao mGameContentDao;
    private OverViewListItemDao mItemDao;
    private OverViewRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
        mSubjectContentDao = mDaoSession.getGameSubjectContentDao();
        mGameContentDao = mDaoSession.getGameContentDao();
        mItemDao = mDaoSession.getOverViewListItemDao();
    }

    public static OverViewRequest getInstance(){
        if(mRequest == null){
            synchronized (OverViewRequest.class){
                if(mRequest == null){
                    mRequest = new OverViewRequest();
                }
            }
        }
        return mRequest;
    }

    public List<OverViewListItem> getOverViewListData(long selectId) {
        return mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.ContentId.eq(selectId)).list();
    }

    public List<OverViewListContent> getAllContentData() {
        return mListContentDao.loadAll();
    }

    public long saveOverViewContent(OverViewListContent mContent) {
        long result = mListContentDao.insert(mContent);
        GameContent gameContent = new GameContent();
        gameContent.setTitle(mContent.getTitle());
        gameContent.setSubject(0);
        gameContent.setSubject_current(0);
        gameContent.setGame_title(mContent.getTitle());
        gameContent.setScore(mContent.getGrade());
        gameContent.setOverviewId(result);
        gameContent.setError(0);
        gameContent.setDifficulty(mContent.getLevel());
        gameContent.setError_current(0);
        gameContent.setScore_current(0);
        mGameContentDao.insert(gameContent);
       return result;
    }

    public boolean saveOverViewItem(long parent,List<OverViewListItem> mItems) {
        for (OverViewListItem mItem :mItems){
            mItem.setContentId(parent);
            long result =mListItemDao.insert(mItem);
            GameSubjectContent mContent = new GameSubjectContent();
            mContent.setTitle(mItem.getTitle());
            mContent.setError(0);
            mContent.setKnowId(result);
            mContent.setLast_time("--");
            mContent.setAnswer(0);
            mContent.setProblem(0);
            mSubjectContentDao.insert(mContent);
        }
        return true;
    }

    public OverViewListContent getContentData(long selectId) {
        return mListContentDao.load(selectId);
    }

    //获取知识体系数据
    public List<OverViewListContent> queryOverViewData(String query) {
        return mListContentDao.queryBuilder().where(OverViewListContentDao.Properties.Title.like(query)).list();
    }

    //得到知识体系大厅的数据
    public OverViewHallBean getOverViewHallData() {
        //TODO 知识大厅数据
        return null;
    }

    //选择知识体系大厅数据
    public boolean selectHallData(long hallId) {
        //TODO 选择知识大厅数据
        return true;
    }

    //根据质量来排序知识体系大厅数据
    public List<OverViewHallRankBean> getHallQualityData() {
        List<OverViewListContent> mContent = mListContentDao.queryBuilder().orderDesc(OverViewListContentDao.Properties.Grade).limit(10).list();
        List<OverViewHallRankBean> mBean = new ArrayList<>();
        for(OverViewListContent mOver : mContent){
            OverViewHallRankBean bean = new OverViewHallRankBean();
            bean.setTitle(mOver.getTitle());
            bean.setHot(mOver.getPeople());
            bean.setId(mOver.getId());
            bean.setQuality(mOver.getGrade());
            bean.setSummary(mOver.getSummary());
            bean.setSystem(mOver.getNumber());
            bean.setTime(mOver.getTime());
            mBean.add(bean);
        }
        return mBean;
    }

    //添加关注到个人体系中
    public boolean insertOverViewData(long overviewId) {
        //TODO 没处理好
        return true;
    }


    //根据热度来排序知识体系大厅数据
    public List<OverViewHallRankBean> getHallHotData() {
        List<OverViewListContent> mContent = mListContentDao.queryBuilder().orderDesc(OverViewListContentDao.Properties.People).limit(10).list();
        List<OverViewHallRankBean> mBean = new ArrayList<>();
        for(OverViewListContent mOver : mContent){
            OverViewHallRankBean bean = new OverViewHallRankBean();
            bean.setTitle(mOver.getTitle());
            bean.setHot(mOver.getPeople());
            bean.setId(mOver.getId());
            bean.setQuality(mOver.getGrade());
            bean.setSummary(mOver.getSummary());
            bean.setSystem(mOver.getNumber());
            bean.setTime(mOver.getTime());
            mBean.add(bean);
        }
        return mBean;
    }

    //根据系统来排序知识体系大厅数据
    public List<OverViewHallRankBean> getHallSystemData() {
        List<OverViewListContent> mContent = mListContentDao.queryBuilder().orderDesc(OverViewListContentDao.Properties.Layer).limit(10).list();
        List<OverViewHallRankBean> mBean = new ArrayList<>();
        for(OverViewListContent mOver : mContent){
            OverViewHallRankBean bean = new OverViewHallRankBean();
            bean.setTitle(mOver.getTitle());
            bean.setHot(mOver.getPeople());
            bean.setId(mOver.getId());
            bean.setQuality(mOver.getGrade());
            bean.setSummary(mOver.getSummary());
            bean.setSystem(mOver.getNumber());
            bean.setTime(mOver.getTime());
            mBean.add(bean);
        }
        return mBean;
    }

    //得到个人数据
    public List<OverViewListItem> getOverViewData(long createId) {
        return mItemDao.queryBuilder().where(OverViewListItemDao.Properties.Create.eq(createId)).list();
    }

    //添加关注到个人体系中
    public boolean insertOverViewCreateData(long overviewId) {
        //TODO 没处理好
        return true;
    }

    //删除知识体系
    public boolean deleteOverViewCreateData(Long id) {
        mListItemDao.deleteByKey(id);
        return true;
    }

    //获取知识体系数据
    public List<KnowListBean> getOverViewUpdateData(long overViewId) {
        List<KnowListBean> mItems = new ArrayList<>();
        List<OverViewListItem> mBean =mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.ContentId.eq(overViewId)).list();
        for (OverViewListItem item:mBean){
            KnowListBean mItem = new KnowListBean(item.getSortId(),item.getParentId(),item.getTitle());
            mItem.setMasterLevel(item.getMasterLevel());
            mItem.setScore(item.getScore());
            mItem.setStudy(item.getStudy());
            mItem.setKnowId(item.getId());
            mItem.setLevel(item.getLevel());
            mItem.setTitle(item.getTitle());
            mItems.add(mItem);
        }
        return mItems;
    }
}
