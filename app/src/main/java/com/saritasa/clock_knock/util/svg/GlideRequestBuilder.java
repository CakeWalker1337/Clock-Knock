package com.saritasa.clock_knock.util.svg;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.util.GlideApp;

public class GlideRequestBuilder{

    private static volatile RequestBuilder<PictureDrawable> INSTANCE = null;

    /**
     * Gets instance of date formatter;
     *
     * @return instance of date formatter.
     */
    public static RequestBuilder<PictureDrawable> getInstance(Context aContext){
        RequestBuilder<PictureDrawable> localInstance = INSTANCE;
        if(localInstance == null){
            synchronized(RequestBuilder.class){
                localInstance = INSTANCE;
                if(localInstance == null){
                    INSTANCE = localInstance = GlideApp.with(aContext)
                            .as(PictureDrawable.class)
                            .error(R.drawable.ic_error_outline_24dp)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .listener(new SvgSoftwareLayerSetter());
                }
            }
        }
        return localInstance;
    }
}
