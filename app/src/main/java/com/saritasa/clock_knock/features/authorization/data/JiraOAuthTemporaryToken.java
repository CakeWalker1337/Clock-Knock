package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;

public class JiraOAuthTemporaryToken extends OAuthGetTemporaryToken{

    /**
     * @param authorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthTemporaryToken(String authorizationServerUrl) {
        super(authorizationServerUrl);
        this.usePost = true;
    }

}
