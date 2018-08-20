package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthGetAccessToken;

public class JiraOAuthAccessToken extends OAuthGetAccessToken{

    /**
     * @param aAuthorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthAccessToken(String aAuthorizationServerUrl) {
        super(aAuthorizationServerUrl);
        this.usePost = true;
    }

}