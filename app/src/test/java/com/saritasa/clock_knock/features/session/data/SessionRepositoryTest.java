package com.saritasa.clock_knock.features.session.data;

import com.saritasa.clock_knock.base.data.PreferenceManagerImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test class for SessionRepositoryImpl
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SessionRepositoryTest{

    private SessionRepositoryImpl mGlobalRepository;

    @Mock
    private PreferenceManagerImpl mPreferenceManager;

    @Before
    public void setUp(){
        mGlobalRepository = new SessionRepositoryImpl(mPreferenceManager);
    }

    @Test
    public void saveAccessTokenCorrect(){

        String token = "token";
        mGlobalRepository.saveAccessToken(token);

        verify(mPreferenceManager).saveAccessToken(token);
    }

    @Test
    public void getAccessTokenCorrect(){
        mGlobalRepository.getAccessToken();

        verify(mPreferenceManager).getAccessToken();
    }

    @Test
    public void saveUsernameCorrect(){
        String username = "admin";
        mGlobalRepository.saveUsername(username);

        verify(mPreferenceManager).saveUsername(username);
    }

    @Test
    public void getUsernameCorrect(){

        mGlobalRepository.getUsername();

        verify(mPreferenceManager).getUsername();
    }

    @Test
    public void clearAllDataCorrect(){
        mGlobalRepository.clearAllData();

        verify(mPreferenceManager).clearAllData();
    }

    @Test
    public void saveSecretTokenCorrect(){
        String token = "token";
        mGlobalRepository.saveSecretToken(token);

        verify(mPreferenceManager).saveSecretToken(token);
    }

    @Test
    public void getSecretTokenCorrect(){

        mGlobalRepository.getSecretToken();

        verify(mPreferenceManager).getSecretToken();
    }

}
