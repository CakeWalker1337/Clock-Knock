package com.saritasa.clock_knock.features.authorization.data;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OAuthWebViewClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        String ht = "javascript:window.jsinterface.printPage(document.getElementsByTagName('html')[0].innerHTML);";
        view.loadUrl(ht);
    }
}