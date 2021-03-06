package com.saritasa.clock_knock.features.auth.presentation;

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
     * Handles page body and checks when user allows or denies the OAUth authentication.
     * <p>
     *     Firstly, if page contains the marker ({@value Strings#SEARCH_MARKER}) then this page is Allowed or Denied page.
     *     Next it checks the first quote position and first /p tag position after the marker. If quote position less than paragraph position, this page is the Allow page.
     *     If not, this page is the Deny page.
     * </p>
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