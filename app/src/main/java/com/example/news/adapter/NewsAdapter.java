package com.example.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.activity.WebActivity;
import com.example.news.bean.NewsBean;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HOLDER_ONE = 1;
    private static final int HOLDER_TWO = 2;
    private static final int HOLDER_THREE = 3;

    private Context mContext;
    private List<NewsBean.ResultBean.DataBean> mNewsList;

    public NewsAdapter(Context context, List<NewsBean.ResultBean.DataBean> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HOLDER_ONE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.news_item1, parent, false);
            return new NewsHolder1(view);
        } else if (viewType == HOLDER_TWO) {
            view = LayoutInflater.from(mContext).inflate(R.layout.news_item2, parent, false);
            return new NewsHolder2(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.news_item3, parent, false);
            return new NewsHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsBean.ResultBean.DataBean thisNews = mNewsList.get(position);
        if (holder instanceof NewsHolder1) {
            ((NewsHolder1) holder).bind(thisNews);
        } else if (holder instanceof NewsHolder2) {
            ((NewsHolder2) holder).bind(thisNews);
        } else {
            ((NewsHolder3) holder).bind(thisNews);
        }
        holder.itemView.setOnClickListener((v -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("WEB_URL", thisNews.getUrl());
            intent.putExtra("TITLE", thisNews.getTitle());
            mContext.startActivity(intent);
        }));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        NewsBean.ResultBean.DataBean thisNews = mNewsList.get(position);
        if (thisNews.getThumbnail_pic_s03() == null) {
            if (thisNews.getThumbnail_pic_s02() == null) {
                return HOLDER_ONE;
            } else {
                return HOLDER_TWO;
            }
        } else {
            return HOLDER_THREE;
        }
    }

    class NewsHolder1 extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewAuthor, mTextViewTime;
        private ImageView mImageView;

        public NewsHolder1(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.item1_title);
            mTextViewAuthor = itemView.findViewById(R.id.item1_author);
            mTextViewTime = itemView.findViewById(R.id.item1_time);
            mImageView = itemView.findViewById(R.id.item1_image);
        }

        public void bind(NewsBean.ResultBean.DataBean news) {
            mTextViewTitle.setText(news.getTitle());
            mTextViewAuthor.setText(news.getAuthor_name());
            mTextViewTime.setText(news.getDate());
            Glide.with(mContext).load(news.getThumbnail_pic_s()).into(mImageView);
        }
    }

    class NewsHolder2 extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewAuthor;
        private ImageView mImageView1, mImageView2;

        public NewsHolder2(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.item2_title);
            mTextViewAuthor = itemView.findViewById(R.id.item2_author);
            mImageView1 = itemView.findViewById(R.id.item2_image1);
            mImageView2 = itemView.findViewById(R.id.item2_image2);
        }

        public void bind(NewsBean.ResultBean.DataBean news) {
            mTextViewTitle.setText(news.getTitle());
            mTextViewAuthor.setText(news.getAuthor_name());
            Glide.with(mContext).load(news.getThumbnail_pic_s()).into(mImageView1);
            Glide.with(mContext).load(news.getThumbnail_pic_s02()).into(mImageView2);
        }
    }

    class NewsHolder3 extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewTime;
        private ImageView mImageView1, mImageView2, mImageView3;

        public NewsHolder3(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.item3_title);
            mTextViewTime = itemView.findViewById(R.id.item3_time);
            mImageView1 = itemView.findViewById(R.id.item3_image1);
            mImageView2 = itemView.findViewById(R.id.item3_image2);
            mImageView3 = itemView.findViewById(R.id.item3_image3);
        }

        public void bind(NewsBean.ResultBean.DataBean news) {
            mTextViewTitle.setText(news.getTitle());
            mTextViewTime.setText(news.getDate());
            Glide.with(mContext).load(news.getThumbnail_pic_s()).into(mImageView1);
            Glide.with(mContext).load(news.getThumbnail_pic_s02()).into(mImageView2);
            Glide.with(mContext).load(news.getThumbnail_pic_s03()).into(mImageView3);
        }
    }
}
