package com.topic.elmira.androidtopics.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/11/18.
 */

public class DemoFragment extends Fragment {
    public static final String ARG_VALUE = "arg_value";

    private TextView mTextView;
    private String value;

    public static DemoFragment getInstance(String value) {
        DemoFragment fragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_VALUE, value);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            value = bundle.getString(ARG_VALUE, "default");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_demo_pager, container, false);
        mTextView = root.findViewById(R.id.textView);
        mTextView.setText(value);
        return root;
    }
}