package com.saritasa.clock_knock.features.worklog.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for worklog component with all included modules.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WorklogScope{

}
