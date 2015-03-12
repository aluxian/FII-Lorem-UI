package com.aluxian.loremui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class QuickReturnScrollListener extends RecyclerView.OnScrollListener {

    private final View mView;
    private final int mScrollThreshold;
    private final int mInitialHeight;

    private boolean mViewVisible;
    private int mScrollY;

    public QuickReturnScrollListener(View view, int scrollThreshold) {
        mView = view;
        mScrollThreshold = scrollThreshold;
        mInitialHeight = view.getHeight();
        mViewVisible = view.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
            if (mScrollY > 0) {
                mScrollY += dy;
            } else {
                mScrollY = dy;
            }
        } else {
            if (mScrollY < 0) {
                mScrollY += dy;
            } else {
                mScrollY = dy;
            }
        }

        if (mScrollY < -mScrollThreshold && !mViewVisible) {
            mScrollY = 0;
            mViewVisible = true;
            mView.animate().translationY(0);
        } else if (mScrollY > mScrollThreshold && mViewVisible) {
            mScrollY = 0;
            mViewVisible = false;
            mView.animate().translationY(-mInitialHeight);
        }
    }

}
