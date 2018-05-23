package com.topic.elmira.androidtopics.dragrecyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.topic.elmira.androidtopics.R;

public class MyRecyclerViewActivity extends AppCompatActivity {

    private static final String MY_RECYCLER_LIST_TAG = "myRecyclerListTag";
    private static final String MY_RECYCLER_GRID_TAG = "myRecyclerGridTag";

    private boolean mListFragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createOrLoadRecyclerListFragment();
    }

    private void createOrLoadRecyclerListFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MY_RECYCLER_LIST_TAG);
        if (fragment==null){
            fragment = new MyRecyclerListFragment();
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment, MY_RECYCLER_LIST_TAG)
                .commit();
    }

    private void createOrLoadRecyclerGridFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MY_RECYCLER_GRID_TAG);
        if (fragment==null){
            fragment = new MyRecyclerGridFragment();
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment, MY_RECYCLER_GRID_TAG)
                .commit();
    }

    public void onChangeFragment(View view){
        mListFragment = !mListFragment;
        if (mListFragment){
            createOrLoadRecyclerListFragment();
        }
        else {
            createOrLoadRecyclerGridFragment();
        }
    }
}
