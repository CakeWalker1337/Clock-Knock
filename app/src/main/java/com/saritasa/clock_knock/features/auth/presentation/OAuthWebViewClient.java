package com.saritasa.clock_knock.features.auth.presentation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.saritasa.clock_knock.util.Strings;

/**
 * A class for control the WebView actions
 */
public class OAuthWebViewClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(@NonNull WebView aWebView, @NonNull String aUrl)
    {
        aWebView.loadUrl(aUrl);
        return true;
    }

    @Override
    public void onPageFinished(@NonNull WebView aWebView, @NonNull String aUrl) {
        super.onPageFinished(aWebView, aUrl);
        aWebView.loadUrl("javascript:window." + Strings.JS_INTERFACE_KEY + ".printPage(document.getElementsByTagName('html')[0].innerHTML);");
    }
}