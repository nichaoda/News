package com.example.news.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.news.App;
import com.example.news.R;
import com.example.news.adapter.NewsCollectedAdapter;
import com.example.news.bean.NewsCollectedBean;

import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        TextView textView = findViewById(R.id.collection_is_empty);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        List<NewsCollectedBean> list = App.getDaoSession().getNewsCollectedBeanDao().loadAll();
        if (list.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return;
        }
        recyclerView.setAdapter(new NewsCollectedAdapter(this, list));
    }
}
