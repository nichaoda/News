package com.example.news.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.news.App;
import com.example.news.R;
import com.example.news.bean.NewsCollectedBean;

public class WebActivity extends AppCompatActivity {
    /**
     * 收藏的新闻
     */
    private NewsCollectedBean mNewsCollected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("WEB_URL");
        String title = intent.getStringExtra("TITLE");
        mNewsCollected = new NewsCollectedBean(null, title, url);
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.collect) {
            try {
                App.getDaoSession().getNewsCollectedBeanDao().insert(mNewsCollected);
                Toast.makeText(this, "收藏成功!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "收藏失败!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
