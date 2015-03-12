package com.aluxian.loremui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aluxian.loremui.recycler.LoremImagesAdapter;
import com.aluxian.loremui.recycler.LoremImagesDecoration;
import com.aluxian.loremui.recycler.QuickReturnScrollListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar) Toolbar mToolbar;
    @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, getLinearLayoutOrientation(), false));
        mRecyclerView.addItemDecoration(new LoremImagesDecoration());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new LoremImagesAdapter(42));

        mToolbar.setTitle(R.string.app_name);
        mToolbar.post(() -> {
            int threshold = mToolbar.getHeight() * 2;
            mRecyclerView.setOnScrollListener(new QuickReturnScrollListener(mToolbar, threshold));
        });
    }

    private int getLinearLayoutOrientation() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
                LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL;
    }

}
