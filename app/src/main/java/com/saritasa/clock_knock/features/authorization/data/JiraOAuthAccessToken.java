package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthGetAccessToken;

public class JiraOAuthAccessToken extends OAuthGetAccessToken{

    /**
     * @param authorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthAccessToken(String authorizationServerUrl) {
        super(authorizationServerUrl);
        this.usePost = true;
    }

}