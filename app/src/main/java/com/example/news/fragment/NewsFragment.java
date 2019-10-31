package com.example.news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.GetNews;
import com.example.news.R;
import com.example.news.adapter.NewsAdapter;
import com.example.news.bean.NewsBean;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {
    private static final String TAG = "NewsFragment";
    /**
     * 从Activity传数据到Fragment中的Bundle中的键
     */
    private static final String KEY = "type";
    /**
     * 聚合数据的AppKey
     */
    private static final String APP_KEY = "058a86ae9acb19d3aebbc6af7ee590af";
    private static final String BASE_URL = "https://v.juhe.cn/toutiao/";

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFAB;

    /**
     * RecyclerView适配器
     */
    private NewsAdapter mNewsAdapter;

    private List<NewsBean.ResultBean.DataBean> mNewsList;

    public static NewsFragment newInstance(String value) {
        Bundle args = new Bundle();
        args.putString(KEY, value);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        initView(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 设置默认的水平分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        mFAB.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle result = getArguments();
        if (result != null) {
            getNewsFromNet(result.getString(KEY));
        }
    }

    private void getNewsFromNet(String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求实例
        GetNews request = retrofit.create(GetNews.class);
        // 封装
        Call<NewsBean> call = request.getNews(type, APP_KEY);
        // 发送请求
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                NewsBean newsBean = response.body();
                mNewsList = newsBean.getResult().getData();
                mNewsAdapter = new NewsAdapter(getActivity(), mNewsList);
                mRecyclerView.setAdapter(mNewsAdapter);
                Log.e(TAG, "onResponse: " + newsBean);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                Log.e(TAG, "网络请求失败: " + t.getMessage());
            }
        });
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mFAB = view.findViewById(R.id.fab);
    }
}
