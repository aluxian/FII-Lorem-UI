package com.aluxian.loremui.recycler;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aluxian.loremui.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoremImagesAdapter extends RecyclerView.Adapter<LoremImagesAdapter.ViewHolder> {

    private final int mNumItems;

    public LoremImagesAdapter(int numItems) {
        mNumItems = numItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.image.getContext())
                .load("http://lorempixel.com/1024/576/?p=" + position)
                .asBitmap()
                .animate(view -> {
                    view.setAlpha(0);
                    view.animate().alpha(1);
                })
                .into(new BitmapImageViewTarget(holder.image) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        Palette.generateAsync(resource, palette -> {
                            Palette.Swatch swatch = palette.getMutedSwatch();

                            if (swatch == null) swatch = palette.getVibrantSwatch();
                            if (swatch == null) swatch = palette.getDarkMutedSwatch();
                            if (swatch == null) swatch = palette.getDarkVibrantSwatch();
                            if (swatch == null) swatch = palette.getLightMutedSwatch();
                            if (swatch == null) swatch = palette.getLightVibrantSwatch();

                            if (swatch != null) {
                                fadeColor(holder.title.getCurrentTextColor(), swatch.getTitleTextColor(), animation ->
                                        holder.title.setTextColor((Integer) animation.getAnimatedValue()));
                                fadeColor(Color.WHITE, swatch.getRgb(), animation ->
                                        holder.title.setBackgroundColor((Integer) animation.getAnimatedValue()));
                            }
                        });
                    }
                });

        holder.title.setBackgroundColor(Color.WHITE);
        holder.title.setTextColor(holder.title.getContext().getResources().getColor(R.color.material_blue_grey_950));
        holder.title.setText("Lorem Pixel #" + position);
    }

    private void fadeColor(int from, int to, ValueAnimator.AnimatorUpdateListener updateListener) {
        ValueAnimator backgroundColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), from, to);
        backgroundColorAnimation.addUpdateListener(updateListener);
        backgroundColorAnimation.start();
    }

    @Override
    public int getItemCount() {
        return mNumItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.image) ImageView image;
        @InjectView(R.id.title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }

}
