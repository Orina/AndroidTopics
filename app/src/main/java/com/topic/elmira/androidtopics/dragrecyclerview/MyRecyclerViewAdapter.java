package com.topic.elmira.androidtopics.dragrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Elmira on 3/14/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyItemViewHolder>
implements MyItemTouchHelperAdapter{

    private List<Item> mItems;

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new MyItemViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return mItems==null ? 0: mItems.size();
    }

    public void setData(List<Item> newItems){
        if (newItems==null) newItems = Collections.emptyList();
        mItems= new ArrayList<>(newItems);
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemMove(int fromPos, int toPos) {
        Collections.swap(mItems, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onItemDismiss(int pos) {
        mItems.remove(pos);
        notifyItemRemoved(pos);
    }
}
