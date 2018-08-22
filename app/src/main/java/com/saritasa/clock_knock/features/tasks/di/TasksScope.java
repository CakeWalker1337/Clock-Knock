package com.saritasa.clock_knock.features.tasks.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for tasks module and tasks component.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TasksScope{
}
