package com.saritasa.clock_knock.features.authentication.data;

import com.saritasa.clock_knock.base.data.GlobalRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManagerImpl;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClientImpl;
import com.saritasa.clock_knock.features.authorization.data.AuthRepositoryImpl;
import com.saritasa.clock_knock.util.RxSchedulerRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link AuthRepositoryImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AuthRepositoryTest{

    private AuthRepositoryImpl mAuthRepository;

    @Mock
    private ResourceManagerImpl mResourceManager;

    @Mock
    private JiraOAuthClientImpl mJiraOAuthClient;

    @Mock
    private GlobalRepositoryImpl mGlobalRepository;

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp(){
        mAuthRepository = new AuthRepositoryImpl(mResourceManager, mGlobalRepository, mJiraOAuthClient);
    }

    @Test
    public void getPageUrlCorrect(){
        try{
            String token = "token";
            when(mJiraOAuthClient.getTemporaryToken()).thenReturn(token);

            mAuthRepository.getAuthPageUrl().test();

            verify(mJiraOAuthClient).getTemporaryToken();
            verify(mJiraOAuthClient).getAuthorizationUrl(token);
        } catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void getAccessTokenCorrect(){
        try{
            String verificationToken = "verification";

            mAuthRepository.getAccessToken(verificationToken).test();

            verify(mJiraOAuthClient).getAccessToken(anyString());

        } catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void saveAccessTokenCorrect(){
        String token = "token";

        mAuthRepository.saveAccessToken(token);

        verify(mGlobalRepository).saveAccessToken(token);
    }

    @Test
    public void saveSecretTokenCorrect(){
        String token = "token";

        mAuthRepository.saveSecretToken(token);

        verify(mGlobalRepository).saveSecretToken(token);
    }
}
