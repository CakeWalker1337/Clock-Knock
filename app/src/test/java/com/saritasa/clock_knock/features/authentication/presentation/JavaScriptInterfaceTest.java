package com.saritasa.clock_knock.features.authentication.presentation;

import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenterImpl;
import com.saritasa.clock_knock.features.authorization.presentation.JavaScriptInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Test {@link JavaScriptInterface}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class JavaScriptInterfaceTest{

    private JavaScriptInterface mJavaScriptInterface;

    @Mock
    private AuthPresenterImpl mAuthPresenter;

    @Before
    public void setUp() {
        mJavaScriptInterface = new JavaScriptInterface(mAuthPresenter);
    }

    @Test
    public void printPageNoMarker() {

        String page = "This is test page body without the marker";

        mJavaScriptInterface.printPage(page);

        verify(mAuthPresenter, never()).onAuthAllowed(page);
        verify(mAuthPresenter, never()).onAuthDenied();
    }

    @Test
    public void printPageDenied() {

        String page = "<p>Вы отказались от доступа к \'Clock-Knock App\'. Теперь это окно браузера можно закрыть.</p> \'";

        mJavaScriptInterface.printPage(page);

        verify(mAuthPresenter).onAuthDenied();
    }

    @Test
    public void printPageAllowed() {

        String page = "<p>Вы успешно авторизовали \'Clock-Knock App\'. Ваш проверочный код: \'ho9yL1\'. При соответствующем запросе необходимо точно ввести данный текст. Перед закрытием окна браузера следует записать это значение.</p> \'";


        String allowedPage =  "ho9yL1\'. При соответствующем запросе необходимо точно ввести данный текст. Перед закрытием окна браузера следует записать это значение.</p> \'";

        mJavaScriptInterface.printPage(page);

        verify(mAuthPresenter).onAuthAllowed(allowedPage);
    }
}
