package com.saritasa.clock_knock.base.network.oauth.token;

import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;

/**
 * A class for providing the requests for getting the temporary token
 */
public class JiraOAuthTemporaryToken extends OAuthGetTemporaryToken{

    /**
     * @param aAuthorizationServerUrl encoded authorization server URL
     */
    public JiraOAuthTemporaryToken(String aAuthorizationServerUrl) {
        super(aAuthorizationServerUrl);
        this.usePost = true;
    }

}
