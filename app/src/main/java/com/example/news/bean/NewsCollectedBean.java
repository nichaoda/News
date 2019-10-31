package com.example.news.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NewsCollectedBean {
    @Id(autoincrement = true)
    private Long id;

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * url地址
     */
    @NotNull
    @Unique
    private String url;

    @Generated(hash = 221909544)
    public NewsCollectedBean(Long id, @NotNull String title, @NotNull String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    @Generated(hash = 643538505)
    public NewsCollectedBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
