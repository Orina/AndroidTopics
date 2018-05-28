package com.topic.elmira.androidtopics.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.topic.elmira.androidtopics.R;
import com.topic.elmira.androidtopics.mvp.model.Repository;

public class NewsActivity extends AppCompatActivity {

    private Contract.Presenter mPresenter;
    public static final String NEWS_FRAGMENT_TAG = "newsfragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        NewsFragment fragment = loadOrCreateFragment();

        mPresenter = new NewsPresenter(fragment, Repository.getInstance());
        fragment.setPresenter(mPresenter);
    }

    NewsFragment loadOrCreateFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        NewsFragment fragment = (NewsFragment) fragmentManager.findFragmentByTag(NEWS_FRAGMENT_TAG);

        if (fragment==null){
            fragment = new NewsFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, NEWS_FRAGMENT_TAG)
                    .commit();

        }

        return fragment;
    }
}
