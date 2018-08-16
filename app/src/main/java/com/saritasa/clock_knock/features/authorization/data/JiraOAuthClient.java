package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.util.Strings;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class JiraOAuthClient {

    private final JiraOAuthTokenFactory oAuthGetAccessTokenFactory;
    private final String authorizationUrl;
    private String temporaryTokenString;

    public JiraOAuthClient() {
        this.oAuthGetAccessTokenFactory = new JiraOAuthTokenFactory();
        authorizationUrl = BuildConfig.BASE_URL + "/plugins/servlet/oauth/authorize";
    }

    /**
     * Gets temporary request token and creates url to authorize it
     *
     * @return request token value
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String getTemporaryToken() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException  {
        JiraOAuthTemporaryToken temporaryToken = oAuthGetAccessTokenFactory.getTemporaryToken(Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = temporaryToken.execute();
        temporaryTokenString = response.token;
        return response.token;
    }

    public String getAuthorizationUrl() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String token = getTemporaryToken();
        OAuthAuthorizeTemporaryTokenUrl authorizationTokenURL = new OAuthAuthorizeTemporaryTokenUrl(authorizationUrl);
        authorizationTokenURL.temporaryToken = token;
        return authorizationTokenURL.toString();
    }

    /**
     * Gets acces token from JIRA
     *
     * @param secret      secret (verification code provided by JIRA after request token authorization)
     * @return access token value
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String getAccessToken(String secret) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        JiraOAuthAccessToken oAuthAccessToken = oAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(temporaryTokenString, secret, Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = oAuthAccessToken.execute();
        return response.token;
    }

    /**
     * Creates OAuthParameters used to make authorized request to JIRA
     *
     * @param tmpToken
     * @param secret
     * @param consumerKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public OAuthParameters getParameters(String tmpToken, String secret, String consumerKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JiraOAuthAccessToken oAuthAccessToken = oAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(tmpToken, secret, consumerKey, privateKey);
        oAuthAccessToken.verifier = secret;
        return oAuthAccessToken.createParameters();
    }
}
