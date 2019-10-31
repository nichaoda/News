package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
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
