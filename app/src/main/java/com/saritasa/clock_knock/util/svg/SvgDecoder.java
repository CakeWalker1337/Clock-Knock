package com.saritasa.clock_knock.util.svg;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Decodes an SVG internal representation from an {@link InputStream}.
 */
public class SvgDecoder implements ResourceDecoder<InputStream, SVG>{

    @Override
    public boolean handles(@Nullable InputStream source, @Nullable Options options){
        return true;
    }

    @Nullable
    @Override
    public Resource<SVG> decode(@Nullable InputStream source, int width, int height, @Nullable Options options)
            throws IOException{
        try{
            SVG svg = SVG.getFromInputStream(source);
            return new SimpleResource<SVG>(svg);
        } catch(SVGParseException ex){
            throw new IOException("Cannot load SVG from stream", ex);
        }
    }
}