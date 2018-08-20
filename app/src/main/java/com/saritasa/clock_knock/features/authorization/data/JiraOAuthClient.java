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

    private final JiraOAuthTokenFactory mOAuthGetAccessTokenFactory;
    private final String mAuthorizationUrl;
    private String mTemporaryTokenString;

    public JiraOAuthClient() {
        this.mOAuthGetAccessTokenFactory = new JiraOAuthTokenFactory();
        mAuthorizationUrl = BuildConfig.BASE_URL + "/plugins/servlet/oauth/authorize";
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
        JiraOAuthTemporaryToken temporaryToken = mOAuthGetAccessTokenFactory.getTemporaryToken(Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = temporaryToken.execute();
        mTemporaryTokenString = response.token;
        return response.token;
    }

    public String getAuthorizationUrl() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String token = getTemporaryToken();
        OAuthAuthorizeTemporaryTokenUrl authorizationTokenURL = new OAuthAuthorizeTemporaryTokenUrl(mAuthorizationUrl);
        authorizationTokenURL.temporaryToken = token;
        return authorizationTokenURL.toString();
    }

    /**
     * Gets acces token from JIRA
     *
     * @param aSecret      secret (verification code provided by JIRA after request token authorization)
     * @return access token value
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String getAccessToken(String aSecret) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        JiraOAuthAccessToken oAuthAccessToken = mOAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(mTemporaryTokenString, aSecret, Strings.CONSUMER_KEY, Strings.PRIVATE_KEY);
        OAuthCredentialsResponse response = oAuthAccessToken.execute();
        return response.token;
    }

    /**
     * Creates OAuthParameters used to make authorized request to JIRA
     *
     * @param aTmpToken
     * @param aSecret
     * @param aConsumerKey
     * @param aPrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public OAuthParameters getParameters(String aTmpToken, String aSecret, String aConsumerKey, String aPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JiraOAuthAccessToken oAuthAccessToken = mOAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(aTmpToken, aSecret, aConsumerKey, aPrivateKey);
        oAuthAccessToken.verifier = aSecret;
        return oAuthAccessToken.createParameters();
    }
}
