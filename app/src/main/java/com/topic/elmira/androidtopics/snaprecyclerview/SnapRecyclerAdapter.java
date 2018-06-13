package com.topic.elmira.androidtopics.snaprecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/11/18.
 */

public class SnapRecyclerAdapter extends RecyclerView.Adapter<SnapRecyclerAdapter.SnapItemHolder> {

    private String[] mData;

    public SnapRecyclerAdapter( String[]  mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SnapItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_snap_recycler, parent, false);
        return new SnapItemHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SnapItemHolder holder, int position) {
        holder.onBind(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    static class SnapItemHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public SnapItemHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void onBind(String data) {
            textView.setText(data);
        }
    }
}
