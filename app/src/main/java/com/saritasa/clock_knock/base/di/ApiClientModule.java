package com.saritasa.clock_knock.base.di;

import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClient;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClientImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * A module for the API client providing
 */
@Module(includes = AppModule.class)
public class ApiClientModule{

    /**
     * Provides the OkHttpClient class through Dagger
     *
     * @param aSessionRepository Global repository object
     * @param aJiraOAuthClient Jira OAuth client object
     * @return New OkHttp client for using it into Retrofit Api builder
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(SessionRepository aSessionRepository, JiraOAuthClient aJiraOAuthClient){

        // An interceptor for authorized requests
        Interceptor headerInterceptor = aChain -> {
            Request original = aChain.request();

            // Get all necessary data from repository
            String accessToken = aSessionRepository.getAccessToken();
            String secretToken = aSessionRepository.getSecretToken();
            String consumerKey = aSessionRepository.getConsumerKey();
            String privateKey = aSessionRepository.getPrivateKey();

            if(accessToken != null && secretToken != null && consumerKey != null && privateKey != null){
                Request.Builder newRequest = original.newBuilder();
                try{
                    // If all is ok, add special OAuth header by the following sample:
                    // Authorization: OAuth oauth_consumer_key="", oauth_nonce="", oauth_signature="", oauth_signature_method="",
                    // oauth_timestamp="", oauth_token="", oauth_verifier=""

                    String authHeader = aJiraOAuthClient.getAuthorizationHeader(original, accessToken, secretToken, consumerKey, privateKey);

                    newRequest = newRequest.header("Accept", "application/json")
                            .header("Authorization", authHeader)
                            .method(original.method(), original.body());

                    return aChain.proceed(newRequest.build());
                } catch(Exception aException){
                    Timber.e(aException);
                }
            }
            return aChain.proceed(original);
        };

        // Add logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor retryInterceptor = aChain -> {

            Request request = aChain.request();

            // try the request
            Response response = aChain.proceed(request);

            int tryCount = 0;
            while(!response.isSuccessful() && tryCount < aSessionRepository.getRetryRequestCount()){

                Timber.d("Request is not successful - " + tryCount);

                tryCount++;

                // retry the request
                response = aChain.proceed(request);
            }

            // otherwise just pass the original response on
            return response;
        };

        // Build client with interceptors
        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(aSessionRepository.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(aSessionRepository.getNetworkTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(aSessionRepository.getNetworkTimeout(), TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(retryInterceptor);

        return mClientBuilder.build();
    }

    /**
     * Provides the Jira OAuth client object
     *
     * @return new Jira OAuth client object
     */
    @Provides
    @Singleton
    public JiraOAuthClient provideJiraOAuthClient(){
        return new JiraOAuthClientImpl();
    }

}
