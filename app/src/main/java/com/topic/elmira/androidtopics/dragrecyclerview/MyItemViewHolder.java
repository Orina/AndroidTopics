package com.topic.elmira.androidtopics.dragrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.topic.elmira.androidtopics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Elmira on 3/14/2018.
 */

public class MyItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_title_id) TextView titleText;
    @BindView(R.id.text_content_id) TextView contentText;
    @BindView(R.id.image_view_id) ImageView imageView;

    public MyItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Item item){
        titleText.setText(item.getTitle());
        contentText.setText(item.getText());
    }
}
