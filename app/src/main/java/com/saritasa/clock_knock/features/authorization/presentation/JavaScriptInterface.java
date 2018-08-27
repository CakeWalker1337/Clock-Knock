package com.saritasa.clock_knock.features.authorization.presentation;

import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import com.saritasa.clock_knock.util.Strings;

/**
 * A class to intercept the pages from WebView
 */
public class JavaScriptInterface{

    private AuthPresenter mAuthPresenter;

    /**
     * @param aAuthPresenter Auth presenter object
     */
    public JavaScriptInterface(@NonNull AuthPresenter aAuthPresenter){
        mAuthPresenter = aAuthPresenter;
    }

    /**
     * Handles page body and checks when user allows or denies the OAUth authentication
     *
     * @param aData Page body string
     */
    @JavascriptInterface
    public void printPage(@NonNull String aData){

        int markerIndex = aData.indexOf(Strings.SEARCH_MARKER);

        if(markerIndex != -1){
            markerIndex += Strings.SEARCH_MARKER.length() + 1;
            int quoteIndex = aData.indexOf("\'", markerIndex);
            int paragraphIndex = aData.indexOf("</p>", markerIndex);

            if(quoteIndex < paragraphIndex){
                mAuthPresenter.onAuthAllowed(aData.substring(quoteIndex+1));
            } else{
                mAuthPresenter.onAuthDenied();
            }

        }
    }
}