package com.example.news.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.news.bean.NewsCollectedBean;

import com.example.news.greendao.NewsCollectedBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsCollectedBeanDaoConfig;

    private final NewsCollectedBeanDao newsCollectedBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsCollectedBeanDaoConfig = daoConfigMap.get(NewsCollectedBeanDao.class).clone();
        newsCollectedBeanDaoConfig.initIdentityScope(type);

        newsCollectedBeanDao = new NewsCollectedBeanDao(newsCollectedBeanDaoConfig, this);

        registerDao(NewsCollectedBean.class, newsCollectedBeanDao);
    }
    
    public void clear() {
        newsCollectedBeanDaoConfig.clearIdentityScope();
    }

    public NewsCollectedBeanDao getNewsCollectedBeanDao() {
        return newsCollectedBeanDao;
    }

}