package com.saritasa.clock_knock.util.svg;

import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

/**
 * Listener which updates the {@link ImageView} to be software rendered, because
 * {@link com.caverock.androidsvg.SVG SVG}/{@link android.graphics.Picture Picture} can't render on
 * a hardware backed {@link android.graphics.Canvas Canvas}.
 */
public class SvgSoftwareLayerSetter implements RequestListener<PictureDrawable>{

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @Nullable Target<PictureDrawable> target,
                                boolean isFirstResource){
        ImageView view = ((ImageViewTarget<?>) target).getView();
        view.setLayerType(ImageView.LAYER_TYPE_NONE, null);
        return false;
    }

    @Override
    public boolean onResourceReady(@Nullable PictureDrawable resource, @Nullable Object model,
                                   @Nullable Target<PictureDrawable> target, @Nullable DataSource dataSource, boolean isFirstResource){
        ImageView view = ((ImageViewTarget<?>) target).getView();
        view.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
        return false;
    }
}