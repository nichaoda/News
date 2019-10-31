package com.example.news;

import com.example.news.bean.NewsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetNews {
    @GET("index")
    Call<NewsBean> getNews(@Query("type") String type, @Query("key") String key);
}
