package com.saritasa.clock_knock.features.auth.data;

import com.saritasa.clock_knock.features.session.data.SessionRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClientImpl;
import com.saritasa.clock_knock.util.RxSchedulerRule;

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

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp(){
        mAuthRepository = new AuthRepositoryImpl(mResourceManager, mJiraOAuthClient);
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
}
