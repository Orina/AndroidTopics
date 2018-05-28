package com.topic.elmira.androidtopics.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;
import com.topic.elmira.androidtopics.mvp.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public static final int REGULAR_TYPE = 0;
    public static final int FAVOURITE_TYPE = 1;

    private List<Article> articles;

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == REGULAR_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_regular_article, parent, false);
            return new NewsViewHolder(view);
        }
        else if (viewType == FAVOURITE_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_article, parent, false);
            return new FavoriteNewsViewHolder(view);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.onBind(article);
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        Article article = articles.get(position);
        return article.isFavorite() ? FAVOURITE_TYPE : REGULAR_TYPE;
    }

    public void changeData(List<Article> articles){
        this.articles = new ArrayList<>(articles);
        notifyDataSetChanged();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle) TextView title;

        @BindView(R.id.tvMessage) TextView message;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Article article){
            title.setText(article.getTitle());
            message.setText(article.getMessage());
        }
    }

    static class FavoriteNewsViewHolder extends NewsViewHolder{

        public FavoriteNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Article article) {
            super.onBind(article);

        }
    }
}
