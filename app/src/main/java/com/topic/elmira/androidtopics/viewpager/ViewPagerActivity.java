package com.topic.elmira.androidtopics.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.topic.elmira.androidtopics.R;

public class ViewPagerActivity extends AppCompatActivity {
    public static final String LOG_TAG = "ViewPagerActivity";

    private ViewPager mViewPager;
    private String[] valuesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = findViewById(R.id.pager);
        valuesList = getResources().getStringArray(R.array.fragmentList);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.d(LOG_TAG, "onPageScrolled(), position: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(LOG_TAG, "onPageSelected(), position: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.d(LOG_TAG, "onPageScrollStateChanged(), state: " + state);
            }
        });
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DemoFragment.getInstance(valuesList[position]);
        }

        @Override
        public int getCount() {
            return valuesList.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page: " + (position+1);
        }
    }
}
