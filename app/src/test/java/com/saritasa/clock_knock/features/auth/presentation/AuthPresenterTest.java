package com.saritasa.clock_knock.features.auth.presentation;

import com.saritasa.clock_knock.features.auth.domain.AuthInteractorImpl;
import com.saritasa.clock_knock.util.RxSchedulerRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link AuthPresenterImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AuthPresenterTest{

    private AuthPresenterImpl mAuthPresenter;

    @Mock
    private AuthInteractorImpl mAuthInteractor;

    @Mock
    private AuthFragment mAuthFragment;


    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp() {
        mAuthPresenter = new AuthPresenterImpl(mAuthInteractor);
        mAuthPresenter.attachView(mAuthFragment);
    }

    @After
    public void destroy() {
        mAuthPresenter.detachView(mAuthFragment);
    }

    @Test
    public void onAuthAllowedSuccess() {

        String testPage = "page";
        String testToken = "token";

        when(mAuthInteractor.finishAuthentication(testPage)).thenReturn(Single.just(testToken));

        mAuthPresenter.onAuthAllowed(testPage);

        verify(mAuthInteractor).saveAccessToken(testToken);
        verify(mAuthFragment).completeAuthentication();
        verify(mAuthFragment, never()).showError(anyString());

    }

    @Test
    public void onAuthAllowedError() {

        String testPage = "page";
        String testMessage = "message";

        when(mAuthInteractor.finishAuthentication(testPage)).thenReturn(Single.error(new Throwable(testMessage)));

        mAuthPresenter.onAuthAllowed(testPage);

        verify(mAuthInteractor, never()).saveAccessToken(anyString());
        verify(mAuthFragment, never()).completeAuthentication();
        verify(mAuthFragment).showError(testMessage);

    }

    @Test
    public void onAuthDeniedCorrect() {

        mAuthPresenter.onAuthDenied();

        verify(mAuthFragment).goToLogin();
    }

    @Test
    public void getAuthPageSuccess(){
        String testUrl = "testUrl";
        when(mAuthInteractor.getAuthPage()).thenReturn(Single.just(testUrl));

        mAuthPresenter.getAuthPage();

        verify(mAuthFragment).loadPageByUrl(testUrl);
        verify(mAuthFragment, never()).showError(anyString());
    }

    @Test
    public void getAuthPageError(){

        String testMessage = "test";

        when(mAuthInteractor.getAuthPage()).thenReturn(Single.error(new Throwable(testMessage)));

        mAuthPresenter.getAuthPage();

        verify(mAuthFragment, never()).loadPageByUrl(anyString());
        verify(mAuthFragment).showError(testMessage);
    }
}
