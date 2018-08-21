package com.saritasa.clock_knock.features.authorization.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.util.Strings;

import javax.inject.Inject;

public class AuthFragment extends MvpAppCompatFragment implements AuthView{

    @Inject
    public AuthPresenter<AuthFragment> mAuthPresenter;

    private NavigationCallback mNavigationCallback;

    public AuthFragment(){

    }

    @Override
    public void onAttach(Context aContext){
        super.onAttach(aContext);
        App.get(aContext.getApplicationContext())
                .getAppComponent()
                .authComponentBuilder()
                .build()
                .inject(this);

        mAuthPresenter.attachView(this);

        if(aContext instanceof NavigationCallback){
            mNavigationCallback = (NavigationCallback) aContext;
        }
    }

    @Override
    public void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater aInflater, ViewGroup aContainer,
                             Bundle aSavedInstanceState){
        return aInflater.inflate(R.layout.fragment_auth, aContainer, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        WebView webView = view.findViewById(R.id.webWindow);
        setupWebView(webView);

        mAuthPresenter.getAuthPage();
    }

    @Override
    public void loadPageByUrl(final String url){
        WebView webView = getView().findViewById(R.id.webWindow);
        webView.loadUrl(url);
    }

    @Override
    public void goToLogin(){
        mNavigationCallback.goToLogin();
    }

    @Override
    public void completeAuthentication(){
        mNavigationCallback.onAuthenticationComplete();
    }

    @Override
    public void showError(final String aMessage){
        View view = getView();
        if(view != null){
            Snackbar.make(getView(), aMessage, Snackbar.LENGTH_LONG).show();
        }
        mNavigationCallback.goToLogin();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(WebView aWebView){
        aWebView.getSettings().setJavaScriptEnabled(true);
        aWebView.addJavascriptInterface(new JavaScriptInterface(mAuthPresenter), Strings.JS_INTERFACE_KEY);
        aWebView.setWebViewClient(new OAuthWebViewClient());
        aWebView.getSettings().setUserAgentString(Strings.WEB_USER_AGENT);
        CookieManager.getInstance().setAcceptThirdPartyCookies(aWebView, true);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    public interface NavigationCallback{

        void onAuthenticationComplete();

        void goToLogin();
    }
}
