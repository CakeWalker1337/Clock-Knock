package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.GlobalRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;
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
 * Test {@link LoginRepositoryImpl}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginRepositoryTest{

    private LoginRepositoryImpl mLoginRepository;

    @Mock
    private ResourceManagerImpl mResourceManager;

    @Mock
    private RestApi mRestApi;

    @Mock
    private GlobalRepositoryImpl mGlobalRepository;

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Before
    public void setUp() {
        mLoginRepository = new LoginRepositoryImpl(mResourceManager, mGlobalRepository, mRestApi);
    }


    @Test
    public void saveUsernameCorrect() {
        String username = "admin";
        mLoginRepository.saveUsername(username);

        verify(mGlobalRepository).saveUsername(username);
    }

    @Test
    public void isAccessTokenExistTrue() {

        String token = "token";

        when(mGlobalRepository.getAccessToken()).thenReturn(token);

        Assert.assertNotEquals(null, mLoginRepository.isAccessTokenExist());

        verify(mGlobalRepository).getAccessToken();
    }

    @Test
    public void isAccessTokenExistFalse() {
        when(mGlobalRepository.getAccessToken()).thenReturn(null);

        Assert.assertEquals(false, mLoginRepository.isAccessTokenExist());

        verify(mGlobalRepository).getAccessToken();
    }
}
