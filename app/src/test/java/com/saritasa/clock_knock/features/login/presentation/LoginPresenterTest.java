package com.saritasa.clock_knock.features.login.presentation;

import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenterImpl;
import com.saritasa.clock_knock.features.login.domain.LoginInteractorImpl;
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
 * Test {@link LoginPresenterImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginPresenterTest{

    private LoginPresenterImpl mLoginPresenter;

    @Mock
    private LoginInteractorImpl mLoginInteractor;

    @Mock
    private LoginFragment mLoginFragment;

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp(){
        mLoginPresenter = new LoginPresenterImpl(mLoginInteractor);
        mLoginPresenter.attachView(mLoginFragment);
    }

    @After
    public void destroy() {
        mLoginPresenter.detachView(mLoginFragment);
    }

    @Test
    public void onLoginClickedCorrect(){
        mLoginPresenter.onLoginClicked();

        verify(mLoginFragment).goToAuthFragment();
    }

    @Test
    public void completeAuthorizationSuccess() {
        Username username = new Username("admin");
        when(mLoginInteractor.getUsername()).thenReturn(Single.just(username));

        mLoginPresenter.completeAuthorization();

        verify(mLoginInteractor).saveUsername(username.getUsername());
        verify(mLoginFragment).onAuthorizationComplete();
        verify(mLoginFragment, never()).showError(anyString());
    }

    @Test
    public void completeAuthorizationError() {
        String message = "message";
        when(mLoginInteractor.getUsername()).thenReturn(Single.error(new Throwable(message)));

        mLoginPresenter.completeAuthorization();

        verify(mLoginInteractor, never()).saveUsername(any());
        verify(mLoginFragment, never()).onAuthorizationComplete();
        verify(mLoginFragment).showError(message);
    }

    @Test
    public void isAccessTokenExistCorrect(){
        mLoginPresenter.isAccessTokenExist();

        verify(mLoginInteractor).isAccessTokenExist();
    }
}
