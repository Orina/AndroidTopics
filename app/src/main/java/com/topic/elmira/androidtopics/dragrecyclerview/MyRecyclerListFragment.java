package com.topic.elmira.androidtopics.dragrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyRecyclerListFragment extends Fragment {

    @BindView(R.id.rv_list) RecyclerView rvList;

    public MyRecyclerListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_recycler_view, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        adapter.setData(getItems());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),
                LinearLayoutManager.VERTICAL, false);

        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapter);

        ItemTouchHelper.Callback itemTouchCallback = new MyItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(itemTouchCallback);
        helper.attachToRecyclerView(rvList);
    }

    private List<Item> getItems(){
        String[] titles = getResources().getStringArray(R.array.item_names_2);
        String[] desc = getResources().getStringArray(R.array.item_descriptions);

        List<Item> res = new ArrayList<>();
        for (int i=0; i< titles.length; i++){
            res.add(new Item(titles[i], desc[i]));
        }
        return res;
    }
}
