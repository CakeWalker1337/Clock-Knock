package com.saritasa.clock_knock.base.network.oauth.token;

import android.support.annotation.NonNull;

import com.google.api.client.auth.oauth.OAuthGetAccessToken;

/**
 * A class for providing the requests for getting the access token
 */
public class JiraOAuthAccessToken extends OAuthGetAccessToken{

    /**
     * @param aAuthorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthAccessToken(@NonNull String aAuthorizationServerUrl) {
        super(aAuthorizationServerUrl);
        this.usePost = true;
    }

}