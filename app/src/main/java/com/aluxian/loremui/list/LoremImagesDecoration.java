package com.aluxian.loremui.list;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aluxian.loremui.utils.Dp;

public class LoremImagesDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewPosition();
        int margin = Dp.PX_08;

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.left = margin;
            outRect.right = margin;
            outRect.top = position == 0 ? margin : 0;
            outRect.bottom = margin;
        } else {
            outRect.left = position == 0 ? margin : 0;
            outRect.right = margin;
            outRect.top = margin;
            outRect.bottom = margin;
        }
    }

}
