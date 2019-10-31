package com.example.news.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.App;
import com.example.news.R;
import com.example.news.activity.CollectionActivity;
import com.example.news.activity.WebActivity;
import com.example.news.bean.NewsCollectedBean;

import java.util.List;

public class NewsCollectedAdapter extends RecyclerView.Adapter<NewsCollectedAdapter.NewsCollectedHolder> {
    private Context mContext;
    private List<NewsCollectedBean> mNewsCollectedBeanList;

    public NewsCollectedAdapter(Context context, List<NewsCollectedBean> newsCollectedBeanList) {
        mContext = context;
        mNewsCollectedBeanList = newsCollectedBeanList;
    }

    @NonNull
    @Override
    public NewsCollectedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCollectedHolder(LayoutInflater.from(mContext).inflate(R.layout.news_collected_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCollectedHolder holder, int position) {
        NewsCollectedBean newsCollected = mNewsCollectedBeanList.get(position);
        holder.bind(newsCollected);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("WEB_URL", newsCollected.getUrl());
            intent.putExtra("TITLE", newsCollected.getTitle());
            mContext.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v -> {
//            App.getDaoSession().getNewsCollectedBeanDao().delete(newsCollected);
            Dialog dialog = new AlertDialog.Builder(mContext)
                    .setMessage("是否取消收藏")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 从数据库中删除
                            App.getDaoSession().getNewsCollectedBeanDao().delete(newsCollected);
                            // RecyclerView数据重新加载
                            if (mContext instanceof Activity) {
                                TextView textView = ((Activity) mContext)
                                        .findViewById(R.id.collection_is_empty);
                                RecyclerView recyclerView = ((Activity) mContext)
                                        .findViewById(R.id.recyclerview_collection);
                                // 查询数据库
                                List<NewsCollectedBean> list = App.getDaoSession().getNewsCollectedBeanDao().loadAll();
                                if (list.isEmpty()) {
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                    return;
                                }
                                // 重新加载
                                recyclerView.setAdapter(new NewsCollectedAdapter(mContext, list));
                            }
                        }
                    })
                    .setNegativeButton("否", null)
                    .create();
            dialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mNewsCollectedBeanList.size();
    }

    class NewsCollectedHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public NewsCollectedHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.news_collected_title);
        }

        public void bind(NewsCollectedBean newsCollected) {
            mTextView.setText(newsCollected.getTitle());
        }
    }
}
