package com.topic.elmira.androidtopics.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topic.elmira.androidtopics.R;
import com.topic.elmira.androidtopics.mvp.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class NewsFragment extends Fragment implements Contract.View {

    private Unbinder mUnbinder;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    private NewsAdapter mNewsAdapter;

    private Contract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_list, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadNews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    public void setPresenter(Contract.Presenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void showNews(List<Article> articles) {
        mNewsAdapter.changeData(articles);
    }

    @Override
    public void showFailure() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setupRecyclerView(){
        mNewsAdapter = new NewsAdapter();
        rvNews.setAdapter(mNewsAdapter);

        rvNews.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        rvNews.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));

        rvNews.setHasFixedSize(true);
    }
}
