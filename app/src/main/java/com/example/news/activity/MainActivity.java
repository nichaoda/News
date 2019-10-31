package com.example.news.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.news.fragment.NewsFragment;
import com.example.news.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    /**
     * 存储不同的Fragment
     */
    private List<Fragment> mFragmentList;
    /**
     * 存储Fragment对应的title
     */
    private List<String> mTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setAdapter();
    }

    private void setAdapter() {
        addFragmentAndTitle();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            /**
             *
             * @param position 对应ViewPager中Fragment的序号
             * @return 对应Fragment的title
             */
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void addFragmentAndTitle() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mTitleList.add("头条");
        mFragmentList.add(NewsFragment.newInstance("top"));
        mTitleList.add("社会");
        mFragmentList.add(NewsFragment.newInstance("shehui"));
        mTitleList.add("国内");
        mFragmentList.add(NewsFragment.newInstance("guonei"));
        mTitleList.add("国际");
        mFragmentList.add(NewsFragment.newInstance("guoji"));
        mTitleList.add("娱乐");
        mFragmentList.add(NewsFragment.newInstance("yule"));
        mTitleList.add("体育");
        mFragmentList.add(NewsFragment.newInstance("tiyu"));
        mTitleList.add("军事");
        mFragmentList.add(NewsFragment.newInstance("junshi"));
        mTitleList.add("科技");
        mFragmentList.add(NewsFragment.newInstance("keji"));
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "这是一个新闻App", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                finish();
                return true;
            case R.id.collection:
                startActivity(new Intent(this, CollectionActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
