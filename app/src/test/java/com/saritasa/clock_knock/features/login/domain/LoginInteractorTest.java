package com.saritasa.clock_knock.features.login.domain;

import com.saritasa.clock_knock.features.auth.domain.AuthInteractorImpl;
import com.saritasa.clock_knock.features.login.data.LoginRepositoryImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.session.data.SessionRepositoryImpl;
import com.saritasa.clock_knock.util.RxSchedulerRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginInteractorImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginInteractorTest{

    private LoginInteractorImpl mLoginInteractor;

    @Mock
    private LoginRepositoryImpl mLoginRepository;

    @Mock
    private SessionRepositoryImpl mSessionRepository;

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp() {
        mLoginInteractor = new LoginInteractorImpl(mLoginRepository, mSessionRepository);
    }

    @Test
    public void saveUsernameCorrect() {
        String username = "admin";
        mLoginInteractor.saveUsername(username);

        verify(mSessionRepository).saveUsername(username);
    }

    @Test
    public void isAccessTokenExistCorrect() {

        mLoginInteractor.isAccessTokenExist();

        verify(mSessionRepository).getAccessToken();
    }
}
