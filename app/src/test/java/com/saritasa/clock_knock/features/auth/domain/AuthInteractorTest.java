package com.saritasa.clock_knock.features.auth.domain;

import com.saritasa.clock_knock.features.auth.data.AuthRepositoryImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.util.RxSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test {@link AuthInteractorImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AuthInteractorTest{

    private AuthInteractorImpl mAuthInteractor;

    @Mock
    private AuthRepositoryImpl mAuthRepository;

    @Mock
    private SessionRepository mSessionRepository;

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp() {
        mAuthInteractor = new AuthInteractorImpl(mAuthRepository, mSessionRepository);
    }

    @Test
    public void getAuthPageCorrect() {

        mAuthInteractor.getAuthPage();

        verify(mAuthRepository).getAuthPageUrl();
    }

    @Test
    public void finishAuthenticationCorrect() {
        String page = "uCTNCq\'. При соответствующем запросе необходимо точно ввести данный текст. Перед закрытием окна браузера следует записать это значение.</p>";

        String verificationToken = "uCTNCq";

        mAuthInteractor.finishAuthentication(page);

        verify(mSessionRepository).saveSecretToken(verificationToken);
        verify(mAuthRepository).getAccessToken(verificationToken);
    }

    @Test
    public void saveAccessTokenCorrect() {

        String token = "token";

        mAuthInteractor.saveAccessToken(token);

        verify(mSessionRepository).saveAccessToken(token);
    }
}
