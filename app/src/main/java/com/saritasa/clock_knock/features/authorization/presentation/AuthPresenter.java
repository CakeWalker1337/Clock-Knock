package com.saritasa.clock_knock.features.authorization.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.saritasa.clock_knock.features.authorization.data.JiraOAuthClient;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView>{

    private JiraOAuthClient mJiraOAuthClient = new JiraOAuthClient();

    public AuthPresenter(){

    }

    public void onAuthAllowed(String aBody){

        String[] pieces = aBody.split("\'");

        String verificationToken = pieces[0];

        Log.w("Auth", "Verification Token: " + verificationToken);

        new Thread(() -> {
            try{
                String accessToken = mJiraOAuthClient.getAccessToken(verificationToken);
                Log.w("Access", "Access token: " + accessToken);
            } catch(Exception aException){
                aException.printStackTrace();
            }
        }).start();
    }

    public void onAuthDenied(){
        getViewState().goToLogin();
    }

    public void getAuthPage(){
        new Thread(){

            public void run(){
                try{

                    String url = mJiraOAuthClient.getAuthorizationUrl();

                    getViewState().loadPageByUrl(url);

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
