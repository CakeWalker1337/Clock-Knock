package com.saritasa.clock_knock.features.authorization.presentation;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.saritasa.clock_knock.util.Strings;

public class JavaScriptInterface{

    private AuthPresenter mAuthPresenter;

    public JavaScriptInterface(AuthPresenter aAuthPresenter){
        mAuthPresenter = aAuthPresenter;
    }

    @JavascriptInterface
    public void printPage(String aData){

        int markerIndex = aData.indexOf(Strings.SEARCH_MARKER);


        if(markerIndex != -1){
            markerIndex += Strings.SEARCH_MARKER.length() + 1;
            int quoteIndex = aData.indexOf("\'", markerIndex);
            int paragraphIndex = aData.indexOf("</p>", markerIndex);

            if(quoteIndex < paragraphIndex){
                mAuthPresenter.onAuthAllowed(aData.substring(quoteIndex));
            } else{
                mAuthPresenter.onAuthDenied();
            }
            //mAuthPresenter.onAuthAllowed(aData);

        }
    }
}