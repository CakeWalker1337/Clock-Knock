package com.saritasa.clock_knock.features.auth.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for the Auth feature
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthScope{

}
