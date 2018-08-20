package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;

public class JiraOAuthTemporaryToken extends OAuthGetTemporaryToken{

    /**
     * @param aAuthorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthTemporaryToken(String aAuthorizationServerUrl) {
        super(aAuthorizationServerUrl);
        this.usePost = true;
    }

}
