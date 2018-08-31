package com.saritasa.clock_knock.base.network.oauth;

import android.support.annotation.NonNull;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.base.network.oauth.token.JiraOAuthAccessToken;
import com.saritasa.clock_knock.base.network.oauth.token.JiraOAuthTemporaryToken;
import com.saritasa.clock_knock.base.network.oauth.token.JiraOAuthTokenFactory;
import com.saritasa.clock_knock.util.Strings;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import okhttp3.Request;

public class JiraOAuthClientImpl implements JiraOAuthClient{

    private final JiraOAuthTokenFactory mOAuthGetAccessTokenFactory;
    private final String mAuthorizationUrl;
    private String mTemporaryTokenString;

    public JiraOAuthClientImpl(){
        this.mOAuthGetAccessTokenFactory = new JiraOAuthTokenFactory();
        mAuthorizationUrl = BuildConfig.BASE_URL + "/plugins/servlet/oauth/authorize";
    }

    @NonNull
    @Override
    public String getTemporaryToken() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
        JiraOAuthTemporaryToken temporaryToken = mOAuthGetAccessTokenFactory.getTemporaryToken(Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = temporaryToken.execute();
        mTemporaryTokenString = response.token;
        return response.token;
    }

    @NonNull
    @Override
    public String getAuthorizationUrl(@NonNull String aTemporaryToken){
        OAuthAuthorizeTemporaryTokenUrl authorizationTokenURL = new OAuthAuthorizeTemporaryTokenUrl(mAuthorizationUrl);
        authorizationTokenURL.temporaryToken = aTemporaryToken;
        return authorizationTokenURL.toString();
    }

    @NonNull
    @Override
    public String getAccessToken(@NonNull String aSecret) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException{
        JiraOAuthAccessToken oAuthAccessToken = mOAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(mTemporaryTokenString, aSecret, Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = oAuthAccessToken.execute();
        return response.token;
    }

    @NonNull
    @Override
    public String getAuthorizationHeader(@NonNull Request aRequest,
                                         @NonNull String aAccessToken,
                                         @NonNull String aSecret,
                                         @NonNull String aConsumerKey,
                                         @NonNull String aPrivateKey) throws GeneralSecurityException{
        JiraOAuthAccessToken oAuthAccessToken = mOAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(aAccessToken, aSecret, aConsumerKey, aPrivateKey);
        oAuthAccessToken.verifier = aSecret;
        OAuthParameters oAuthParameters = oAuthAccessToken.createParameters();
        oAuthParameters.computeNonce();
        oAuthParameters.computeTimestamp();
        oAuthParameters.computeSignature(aRequest.method(), new GenericUrl(aRequest.url().url()));
        return oAuthParameters.getAuthorizationHeader();
    }
}
