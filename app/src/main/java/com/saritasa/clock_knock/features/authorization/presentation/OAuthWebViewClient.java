package com.saritasa.clock_knock.features.authorization.presentation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.saritasa.clock_knock.util.Strings;

public class OAuthWebViewClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView aWebView, String aUrl)
    {
        aWebView.loadUrl(aUrl);
        return true;
    }

    @Override
    public void onPageFinished(WebView aWebView, String aUrl) {
        super.onPageFinished(aWebView, aUrl);
        aWebView.loadUrl("javascript:window." + Strings.JS_INTERFACE_KEY + ".printPage(document.getElementsByTagName('html')[0].innerHTML);");
    }
}