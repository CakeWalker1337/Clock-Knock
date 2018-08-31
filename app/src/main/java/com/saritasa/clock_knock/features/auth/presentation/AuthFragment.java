package com.saritasa.clock_knock.features.auth.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;
import com.saritasa.clock_knock.util.Strings;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The Auth Fragment class
 */
public class AuthFragment extends MvpAppCompatFragment implements AuthView{

    @BindView(R.id.webWindow)
    public WebView mWebView;

    @BindView(R.id.coordinator)
    public CoordinatorLayout mCoordinatorLayout;

    @Inject
    public AuthPresenter mAuthPresenter;

    private NavigationListener mNavigationListener;
    private Unbinder mUnbinder;

    public AuthFragment(){
    }

    @Override
    public void onAttach(Context aContext){
        super.onAttach(aContext);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater aInflater, @Nullable ViewGroup aContainer,
                             Bundle aSavedInstanceState){
        return aInflater.inflate(R.layout.fragment_auth, aContainer, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState){

        if(getActivity() != null){

            App.get(getActivity())
                    .getAppComponent()
                    .authComponentBuilder()
                    .build()
                    .inject(this);

            mAuthPresenter.attachView(this);

            if(getActivity() instanceof NavigationListener){
                mNavigationListener = (NavigationListener) getActivity();
            }

        }

        if(getView() != null){

            if(mWebView != null){
                setupWebView(mWebView);
            }

            mAuthPresenter.getAuthPage();
        }

        super.onActivityCreated(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDetach(){
        mAuthPresenter.detachView(this);
        mUnbinder.unbind();
        super.onDetach();
    }

    @Override
    public void goToLogin(){
        mNavigationListener.goToLogin();
    }

    @Override
    public void completeAuthentication(){
        mNavigationListener.onAuthenticationComplete();
    }

    @Override
    public void loadPageByUrl(@NonNull final String url){
        Log.d("AuthFragment", "URL = " + url);
        mWebView.loadUrl(url);
    }

    @Override
    public void showError(@NonNull final String aMessage){
        View view = getView();
        if(view != null){
            Snackbar.make(mCoordinatorLayout, aMessage, Snackbar.LENGTH_LONG).show();
        }
        mNavigationListener.goToLogin();
    }

    /**
     * Sets up the WebView
     *
     * @param aWebView WebView object
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(@NonNull WebView aWebView){
        WebSettings settings = aWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        aWebView.addJavascriptInterface(new JavaScriptInterface(mAuthPresenter), Strings.JS_INTERFACE_KEY);
        aWebView.setWebViewClient(new OAuthWebViewClient());
        settings.setUserAgentString(Strings.WEB_USER_AGENT);
        CookieManager.getInstance().setAcceptThirdPartyCookies(aWebView, true);

    }
}
