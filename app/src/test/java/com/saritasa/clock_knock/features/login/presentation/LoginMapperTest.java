package com.saritasa.clock_knock.features.login.presentation;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Test {@link LoginMapper}
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginMapperTest{

    /**
     * Tests mapping from domain username to usual username
     */
    @Test
    public void mapUsernameFromDomainCorrect(){
        UsernameDomain usernameDomain = new UsernameDomain("test");
        Username username = LoginMapper.mapUsernameFromDomain(usernameDomain);
        assertEquals("test", username.getUsername());
    }

    /**
     * Tests mapping from usual username to domain username
     */
    @Test
    public void mapUsernameToDomainCorrect(){
        Username username = new Username("test");
        UsernameDomain usernameDomain = LoginMapper.mapUsernameToDomain(username);
        assertEquals("test", usernameDomain.getUsername());
    }
}
