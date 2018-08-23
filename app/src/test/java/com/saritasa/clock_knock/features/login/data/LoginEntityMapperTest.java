package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;
import com.saritasa.clock_knock.features.login.presentation.LoginMapper;
import com.saritasa.clock_knock.features.login.presentation.Username;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for LoginEntityMapper class
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginEntityMapperTest{

    /**
     * Tests mapping from entity username to domain username
     */
    @Test
    public void mapDomainUsernameFromEntityCorrect(){
        UsernameEntity usernameEntity = new UsernameEntity("test");
        UsernameDomain usernameDomain = LoginEntityMapper.mapUsernameFromEntity(usernameEntity);
        assertEquals("test", usernameDomain.getUsername());
    }

    /**
     * Tests mapping from domain username to entity username
     */
    @Test
    public void mapDomainUsernameToEntityCorrect(){

        UsernameDomain usernameDomain = new UsernameDomain("test");
        UsernameEntity usernameEntity = LoginEntityMapper.mapUsernameToEntity(usernameDomain);
        assertEquals("test", usernameEntity.getKey());
    }
}
