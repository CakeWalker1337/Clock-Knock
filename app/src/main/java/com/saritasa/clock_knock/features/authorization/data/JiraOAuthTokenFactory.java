package com.saritasa.clock_knock.features.authorization.data;

import com.google.api.client.auth.oauth.OAuthRsaSigner;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.saritasa.clock_knock.BuildConfig;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class JiraOAuthTokenFactory {
    protected final String mAccessTokenUrl;
    protected final String mRequestTokenUrl;


    public JiraOAuthTokenFactory() {
        mAccessTokenUrl = BuildConfig.BASE_URL + "/plugins/servlet/oauth/access-token";
        mRequestTokenUrl = BuildConfig.BASE_URL + "/plugins/servlet/oauth/request-token";
    }

    /**
     * Initialize JiraOAuthAccessToken
     * by setting it to use POST method, secret, request token
     * and setting consumer and private keys.
     *
     * @param aTmpToken    request token
     * @param aSecret      secret (verification code provided by JIRA after request token authorization)
     * @param aConsumerKey consumer ey
     * @param aPrivateKey  private key in PKCS8 format
     * @return JiraOAuthAccessToken request
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public JiraOAuthAccessToken getJiraOAuthGetAccessToken(String aTmpToken, String aSecret, String aConsumerKey, String aPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JiraOAuthAccessToken accessToken = new JiraOAuthAccessToken(mAccessTokenUrl);
        accessToken.consumerKey = aConsumerKey;
        accessToken.signer = getOAuthRsaSigner(aPrivateKey);
        accessToken.transport = new ApacheHttpTransport();
        accessToken.verifier = aSecret;
        accessToken.temporaryToken = aTmpToken;
        return accessToken;
    }


    /**
     * Initialize JiraOAuthTemporaryToken
     * by setting it to use POST method, oob (Out of Band) callback
     * and setting consumer and private keys.
     *
     * @param aConsumerKey consumer key
     * @param aPrivateKey  private key in PKCS8 format
     * @return JiraOAuthTemporaryToken request
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public JiraOAuthTemporaryToken getTemporaryToken(String aConsumerKey, String aPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JiraOAuthTemporaryToken oAuthGetTemporaryToken = new JiraOAuthTemporaryToken(mRequestTokenUrl);
        oAuthGetTemporaryToken.consumerKey = aConsumerKey;
        oAuthGetTemporaryToken.signer = getOAuthRsaSigner(aPrivateKey);
        oAuthGetTemporaryToken.transport = new ApacheHttpTransport();
        oAuthGetTemporaryToken.callback = "oob";
        return oAuthGetTemporaryToken;
    }

    /**
     * @param aPrivateKey private key in PKCS8 format
     * @return OAuthRsaSigner
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private OAuthRsaSigner getOAuthRsaSigner(String aPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        OAuthRsaSigner oAuthRsaSigner = new OAuthRsaSigner();
        oAuthRsaSigner.privateKey = getPrivateKey(aPrivateKey);
        System.out.println(new String(oAuthRsaSigner.privateKey.getEncoded()));
        return oAuthRsaSigner;
    }

    /**
     * Creates PrivateKey from string
     *
     * @param aPrivateKey private key in PKCS8 format
     * @return private key
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey getPrivateKey(String aPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateBytes = Base64.decodeBase64(aPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }
}
