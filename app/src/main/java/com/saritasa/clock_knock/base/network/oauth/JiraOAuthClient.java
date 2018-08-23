package com.saritasa.clock_knock.base.network.oauth;

import com.google.api.client.auth.oauth.OAuthParameters;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import okhttp3.Request;

/**
 * An intreface for providing authentication methods
 */
public interface JiraOAuthClient{

    /**
     * Gets the OAuth temporary token
     *
     * @return Temporary token string
     * @throws NoSuchAlgorithmException No such algorithm
     * @throws InvalidKeySpecException Invalid key
     * @throws IOException I/O error
     */
    String getTemporaryToken() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException;

    /**
     * Gets the Authorization URL based on temporary token
     *
     * @param aTemporaryToken Temporary token string
     * @return Authorization URL string
     */
    String getAuthorizationUrl(String aTemporaryToken);

    /**
     * Gets the OAuth access token by secret token
     *
     * @param aSecret Secret token string
     * @return OAuth access token string
     * @throws NoSuchAlgorithmException No such algorithm
     * @throws InvalidKeySpecException Invalid key
     * @throws IOException I/O error
     */
    String getAccessToken(String aSecret) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException;

    /**
     * Gets the authorization header to put it into each HTTP request
     *
     * @param aRequest Http request from interceptor
     * @param aAccessToken Access token string
     * @param aSecret Secret token string
     * @param aConsumerKey Consumer key string
     * @param aPrivateKey Private key string
     * @return Authorization header to put it into each request
     * @throws GeneralSecurityException Security exception
     */
    String getAuthorizationHeader(Request aRequest, String aAccessToken, String aSecret, String aConsumerKey, String aPrivateKey) throws GeneralSecurityException;
}
